package com.wineguesser.deductive.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserProfileViewModel : ViewModel() {
    val userName = MutableLiveData<String?>()
    val displayName = MutableLiveData<String?>()
    val emailAddress = MutableLiveData<String?>()
    val photoUrl = MutableLiveData<String?>()
    val confirmEmailAddress = MutableLiveData<String?>()
    val password = MutableLiveData<String?>()
    val confirmPassword = MutableLiveData<String?>()

    val errorDisplayName = MutableLiveData<String?>()
    val errorEmailAddress = MutableLiveData<String?>()
    val errorConfirmEmailAddress = MutableLiveData<String?>()
    val errorPhotoUrl = MutableLiveData<String?>()
    val errorPassword = MutableLiveData<String?>()
    val errorConfirmPassword = MutableLiveData<String?>()

    fun setUserName(userName: String?) {
        this.userName.value = userName
    }

    fun setDisplayName(displayName: String?) {
        this.displayName.value = displayName
    }

    fun setEmailAddress(emailAddress: String?) {
        this.emailAddress.value = emailAddress
    }

    fun setPhotoUrl(photoUrl: String?) {
        this.photoUrl.value = photoUrl
    }

    fun setConfirmEmailAddress(confirmEmailAddress: String?) {
        this.confirmEmailAddress.value = confirmEmailAddress
    }

    fun setPassword(password: String?) {
        this.password.value = password
    }

    fun setConfirmPassword(confirmPassword: String?) {
        this.confirmPassword.value = confirmPassword
    }

    fun setErrorDisplayName(errorDisplayName: String?) {
        this.errorDisplayName.value = errorDisplayName
    }

    fun setErrorEmailAddress(errorEmailAddress: String?) {
        this.errorEmailAddress.value = errorEmailAddress
    }

    fun setErrorConfirmEmailAddress(errorConfirmEmailAddress: String?) {
        this.errorConfirmEmailAddress.value = errorConfirmEmailAddress
    }

    fun setErrorPhotoUrl(errorPhotoUrl: String?) {
        this.errorPhotoUrl.value = errorPhotoUrl
    }

    fun setErrorPassword(errorPassword: String?) {
        this.errorPassword.value = errorPassword
    }

    fun setErrorConfirmPassword(errorConfirmPassword: String?) {
        this.errorConfirmPassword.value = errorConfirmPassword
    }
}
