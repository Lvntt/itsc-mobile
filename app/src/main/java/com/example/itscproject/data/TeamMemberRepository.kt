package com.example.itscproject.data

import android.graphics.Bitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

object TeamMemberRepository {
    private val externalScope: CoroutineScope = CoroutineScope(SupervisorJob()+Dispatchers.IO)

    private val membersMutex = Mutex()

    private var membersList: List<TeamMember> = emptyList()
    private var memberPhotoMap: MutableMap<String, Bitmap> = mutableMapOf()

    suspend fun getMembers(refresh: Boolean): Result<List<TeamMember>> {
        return if (refresh || membersList.isEmpty()) {
            withContext(externalScope.coroutineContext) {
                val result = try{
                    val response = TeamMemberDataSource.getTeamMembers()
                    if(response!=null){
                        Result.success(response)
                    }
                    else{
                        Result.failure(Exception("Null response"))
                    }
                } catch (e: Exception){
                    Result.failure(e)
                }
                result.also { res ->
                    membersMutex.withLock {
                        if (res.isSuccess) {
                            membersList = res.getOrDefault(membersList)
                        }
                    }
                }
            }
        } else {
            Result.success(membersMutex.withLock { this.membersList })
        }
    }

    suspend fun getPhoto(id: String, refresh: Boolean): Result<Bitmap> {
        return if (refresh || memberPhotoMap[id] == null) {
            withContext(externalScope.coroutineContext) {
                val result = try{
                    val response = TeamMemberDataSource.getPhoto(id)
                    if(response!=null){
                        Result.success(response)
                    }
                    else{
                        Result.failure(Exception("Null response"))
                    }
                } catch (e: Exception){
                    Result.failure(e)
                }
                result.also { res ->
                    membersMutex.withLock {
                        if (res.isSuccess) {
                            memberPhotoMap[id] = res.getOrNull()!!
                        }
                    }
                }
            }
        } else {
            Result.success(membersMutex.withLock { this.memberPhotoMap[id]!! })
        }
    }
}