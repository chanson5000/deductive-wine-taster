package com.wineguesser.deductive.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

public class UserProfileForm extends BaseObservable {

    public UserProfileForm() {

    }

    private String userName;
    private String displayName;
    private String emailAddress;
    private String photoUrl;
    private String confirmEmailAddress;
    private String password;
    private String confirmPassword;

    private String errorDisplayName;
    private String errorEmailAddress;
    private String errorConfirmEmailAddress;
    private String errorPhotoUrl;
    private String errorPassword;
    private String errorConfirmPassword;

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        notifyPropertyChanged(BR.displayName);
    }

    @Bindable
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        notifyPropertyChanged(BR.emailAddress);
    }

    @Bindable
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        notifyPropertyChanged(BR.photoUrl);
    }

    @Bindable
    public String getConfirmEmailAddress() {
        return confirmEmailAddress;
    }

    public void setConfirmEmailAddress(String confirmEmailAddress) {
        this.confirmEmailAddress = confirmEmailAddress;
        notifyPropertyChanged(BR.confirmEmailAddress);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        notifyPropertyChanged(BR.confirmPassword);
    }

    @Bindable
    public String getErrorDisplayName() {
        return errorDisplayName;
    }

    public void setErrorDisplayName(String errorDisplayName) {
        this.errorDisplayName = errorDisplayName;
        notifyPropertyChanged(BR.errorDisplayName);
    }

    @Bindable
    public String getErrorEmailAddress() {
        return errorEmailAddress;
    }

    public void setErrorEmailAddress(String errorEmailAddress) {
        this.errorEmailAddress = errorEmailAddress;
        notifyPropertyChanged(BR.errorEmailAddress);
    }

    @Bindable
    public String getErrorConfirmEmailAddress() {
        return errorConfirmEmailAddress;
    }

    public void setErrorConfirmEmailAddress(String errorConfirmEmailAddress) {
        this.errorConfirmEmailAddress = errorConfirmEmailAddress;
        notifyPropertyChanged(BR.errorConfirmEmailAddress);
    }

    @Bindable
    public String getErrorPhotoUrl() {
        return errorPhotoUrl;
    }

    public void setErrorPhotoUrl(String errorPhotoUrl) {
        this.errorPhotoUrl = errorPhotoUrl;
        notifyPropertyChanged(BR.errorPhotoUrl);
    }

    @Bindable
    public String getErrorPassword() {
        return errorPassword;
    }

    public void setErrorPassword(String errorPassword) {
        this.errorPassword = errorPassword;
        notifyPropertyChanged(BR.errorPassword);
    }

    @Bindable
    public String getErrorConfirmPassword() {
        return errorConfirmPassword;
    }

    public void setErrorConfirmPassword(String errorConfirmPassword) {
        this.errorConfirmPassword = errorConfirmPassword;
        notifyPropertyChanged(BR.errorConfirmPassword);
    }
}
