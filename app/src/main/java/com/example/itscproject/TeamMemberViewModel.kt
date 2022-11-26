package com.example.itscproject

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itscproject.data.TeamMember
import com.example.itscproject.data.TeamMemberRepository
import kotlinx.coroutines.launch

class TeamMemberViewModel: ViewModel() {
    private val _teamMember = MutableLiveData<TeamMember>()
    private val _memberPhoto = MutableLiveData<Bitmap>()
    private val _getMemberSuccess = MutableLiveData<Boolean>().apply {
        value = true
    }
    val teamMember: LiveData<TeamMember> = _teamMember
    val memberPhoto: LiveData<Bitmap> = _memberPhoto
    val getMemberSuccess: LiveData<Boolean> = _getMemberSuccess

    fun setID(id: String?){
        if(id!= null) {
            viewModelScope.launch {
                val result = TeamMemberRepository.getCachedMember(id)
                if(result.isSuccess){
                    _teamMember.value = result.getOrNull()
                    val photoResult = TeamMemberRepository.getPhoto(id,false)
                    if(photoResult.isSuccess){
                        _memberPhoto.value = photoResult.getOrNull()
                    }
                }
                else {
                    _getMemberSuccess.value = false
                }
            }
        }
        else {
            _getMemberSuccess.value = false
        }
    }
}