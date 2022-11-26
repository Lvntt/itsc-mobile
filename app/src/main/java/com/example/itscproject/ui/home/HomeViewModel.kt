package com.example.itscproject.ui.home

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itscproject.data.TeamMember
import com.example.itscproject.data.TeamMemberRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _teamMembers = MutableLiveData<List<TeamMember>>().apply {
        value = emptyList()
    }
    private val _getIsSuccessful = MutableLiveData<Boolean>().apply {
        value = true
    }
    private val _memberPhotos = MutableLiveData<MutableMap<String, Bitmap>>().apply {
        value = mutableMapOf()
    }
    private val _memberPhotosImmutable = MutableLiveData<Map<String,Bitmap>>().apply {
        value = _memberPhotos.value
    }
    val teamMembers: LiveData<List<TeamMember>> = _teamMembers
    val getIsSuccessful: LiveData<Boolean> = _getIsSuccessful
    val memberPhotos: LiveData<Map<String, Bitmap>> = _memberPhotosImmutable

    init {
        getData(false)
    }

    fun getData(refresh: Boolean){
        viewModelScope.launch {
            val result = TeamMemberRepository.getMembers(refresh)
            if(result.isSuccess){
                _teamMembers.value = result.getOrDefault(emptyList())
                for(member in result.getOrDefault(emptyList())){
                    val photoResult = TeamMemberRepository.getPhoto(member.id!!,refresh)
                    if(photoResult.isSuccess){
                        _memberPhotos.value?.put(member.id!!,photoResult.getOrNull()!!)
                        _memberPhotos.value = _memberPhotos.value
                        _memberPhotosImmutable.value = _memberPhotosImmutable.value
                    }
                }
            }
            else{
                _getIsSuccessful.value = false
            }
        }
    }
}