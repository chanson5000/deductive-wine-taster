package com.wineguesser.deductive.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class UserProfileViewModel extends ViewModel {
    private MutableLiveData<String> userName = new MutableLiveData<>();
    private MutableLiveData<String> displayName = new MutableLiveData<>();
    private MutableLiveData<String> emailAddress = new MutableLiveData<>();
    private MutableLiveData<String> photoUrl = new MutableLiveData<>();
    private MutableLiveData<String> confirmEmailAddress = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();
    private MutableLiveData<String> confirmPassword = new MutableLiveData<>();

    private MutableLiveData<String> errorDisplayName = new MutableLiveData<>();
    private MutableLiveData<String> errorEmailAddress = new MutableLiveData<>();
    private MutableLiveData<String> errorConfirmEmailAddress = new MutableLiveData<>();
    private MutableLiveData<String> errorPhotoUrl = new MutableLiveData<>();
    private MutableLiveData<String> errorPassword = new MutableLiveData<>();
    private MutableLiveData<String> errorConfirmPassword = new MutableLiveData<>();

    public UserProfileViewModel() {

    }

    public MutableLiveData<String> getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.setValue(userName);
    }

    public MutableLiveData<String> getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName.setValue(displayName);
    }

    public MutableLiveData<String> getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress.setValue(emailAddress);
    }

    public MutableLiveData<String> getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl.setValue(photoUrl);
    }

    public MutableLiveData<String> getConfirmEmailAddress() {
        return confirmEmailAddress;
    }

    public void setConfirmEmailAddress(String confirmEmailAddress) {
        this.confirmEmailAddress.setValue(confirmEmailAddress);
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password.setValue(password);
    }

    public MutableLiveData<String> getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword.setValue(confirmPassword);
    }

    public MutableLiveData<String> getErrorDisplayName() {
        return errorDisplayName;
    }

    public void setErrorDisplayName(String errorDisplayName) {
        this.errorDisplayName.setValue(errorDisplayName);
    }

    public MutableLiveData<String> getErrorEmailAddress() {
        return errorEmailAddress;
    }

    public void setErrorEmailAddress(String errorEmailAddress) {
        this.errorEmailAddress.setValue(errorEmailAddress);
    }

    public MutableLiveData<String> getErrorConfirmEmailAddress() {
        return errorConfirmEmailAddress;
    }

    public void setErrorConfirmEmailAddress(String errorConfirmEmailAddress) {
        this.errorConfirmEmailAddress.setValue(errorConfirmEmailAddress);
    }

    public MutableLiveData<String> getErrorPhotoUrl() {
        return errorPhotoUrl;
    }

    public void setErrorPhotoUrl(String errorPhotoUrl) {
        this.errorPhotoUrl.setValue(errorPhotoUrl);
    }

    public MutableLiveData<String> getErrorPassword() {
        return errorPassword;
    }

    public void setErrorPassword(String errorPassword) {
        this.errorPassword.setValue(errorPassword);
    }

    public MutableLiveData<String> getErrorConfirmPassword() {
        return errorConfirmPassword;
    }

    public void setErrorConfirmPassword(String errorConfirmPassword) {
        this.errorConfirmPassword.setValue(errorConfirmPassword);
    }
}
