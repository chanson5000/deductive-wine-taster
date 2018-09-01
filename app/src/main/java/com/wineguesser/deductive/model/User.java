package com.wineguesser.deductive.model;

import com.google.firebase.database.Exclude;

import java.util.List;

public class User {

    @Exclude
    private String uid;
    @Exclude
    private String name;
    @Exclude
    private String email;
    @Exclude
    private String photoUrl;
    @Exclude
    private boolean verificationEmailSent;
    @Exclude
    private List<String> conclusionRecordId;
}
