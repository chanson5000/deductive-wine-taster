package com.wineguesser.deductive.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.wineguesser.deductive.BR;


public class UserProfileViewModel extends ViewModel implements Observable {
    private PropertyChangeRegistry callbacks = new PropertyChangeRegistry();

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

    @Bindable
    public MutableLiveData<String> getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.setValue(userName);
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public MutableLiveData<String> getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName.setValue(displayName);
        notifyPropertyChanged(BR.displayName);
    }

    @Bindable
    public MutableLiveData<String> getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress.setValue(emailAddress);
        notifyPropertyChanged(BR.emailAddress);
    }

    @Bindable
    public MutableLiveData<String> getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl.setValue(photoUrl);
        notifyPropertyChanged(BR.photoUrl);
    }

    @Bindable
    public MutableLiveData<String> getConfirmEmailAddress() {
        return confirmEmailAddress;
    }

    public void setConfirmEmailAddress(String confirmEmailAddress) {
        this.confirmEmailAddress.setValue(confirmEmailAddress);
        notifyPropertyChanged(BR.confirmEmailAddress);
    }

    @Bindable
    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password.setValue(password);
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public MutableLiveData<String> getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword.setValue(confirmPassword);
        notifyPropertyChanged(BR.confirmPassword);
    }

    @Bindable
    public MutableLiveData<String> getErrorDisplayName() {
        return errorDisplayName;
    }

    public void setErrorDisplayName(String errorDisplayName) {
        this.errorDisplayName.setValue(errorDisplayName);
        notifyPropertyChanged(BR.errorDisplayName);
    }

    @Bindable
    public MutableLiveData<String> getErrorEmailAddress() {
        return errorEmailAddress;
    }

    public void setErrorEmailAddress(String errorEmailAddress) {
        this.errorEmailAddress.setValue(errorEmailAddress);
        notifyPropertyChanged(BR.errorEmailAddress);
    }

    @Bindable
    public MutableLiveData<String> getErrorConfirmEmailAddress() {
        return errorConfirmEmailAddress;
    }

    public void setErrorConfirmEmailAddress(String errorConfirmEmailAddress) {
        this.errorConfirmEmailAddress.setValue(errorConfirmEmailAddress);
        notifyPropertyChanged(BR.errorConfirmEmailAddress);
    }

    @Bindable
    public MutableLiveData<String> getErrorPhotoUrl() {
        return errorPhotoUrl;
    }

    public void setErrorPhotoUrl(String errorPhotoUrl) {
        this.errorPhotoUrl.setValue(errorPhotoUrl);
        notifyPropertyChanged(BR.errorPhotoUrl);
    }

    @Bindable
    public MutableLiveData<String> getErrorPassword() {
        return errorPassword;
    }

    public void setErrorPassword(String errorPassword) {
        this.errorPassword.setValue(errorPassword);
        notifyPropertyChanged(BR.errorPassword);
    }

    @Bindable
    public MutableLiveData<String> getErrorConfirmPassword() {
        return errorConfirmPassword;
    }

    public void setErrorConfirmPassword(String errorConfirmPassword) {
        this.errorConfirmPassword.setValue(errorConfirmPassword);
        notifyPropertyChanged(BR.errorConfirmPassword);
    }

    public PropertyChangeRegistry getCallbacks() {
        return callbacks;
    }

    public void setCallbacks(PropertyChangeRegistry callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void addOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    /**
     * Notifies observers that all properties of this instance have changed.
     */
    void notifyChange() {
        callbacks.notifyCallbacks(this, 0, null);
    }

    /**
     * Notifies observers that a specific property has changed. The getter for the
     * property that changes should be marked with the @Bindable annotation to
     * generate a field in the BR class to be used as the fieldId parameter.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    private void notifyPropertyChanged(int fieldId) {
        callbacks.notifyCallbacks(this, fieldId, null);
    }
}
