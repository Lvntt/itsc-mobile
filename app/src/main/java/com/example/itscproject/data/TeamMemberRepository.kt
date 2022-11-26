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

    private val membersMap: MutableMap<String,TeamMember> = mutableMapOf()
    private val memberPhotoMap: MutableMap<String, Bitmap> = mutableMapOf()

    suspend fun getMembers(refresh: Boolean): Result<List<TeamMember>> {
        return if (refresh || membersMap.isEmpty()) {
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
                            val newData = res.getOrDefault(emptyList())
                            membersMap.clear()
                            for(teamMember in newData){
                                teamMember.id?.let { membersMap.put(it,teamMember) }
                            }
                        }
                    }
                }
            }
        } else {
            Result.success(membersMutex.withLock { ArrayList(this.membersMap.values) })
        }
    }

    fun getCachedMember(id: String): Result<TeamMember>{
        return if(membersMap[id] != null){
            Result.success(membersMap[id]!!)
        } else {
            Result.failure(Exception("Member not found"))
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