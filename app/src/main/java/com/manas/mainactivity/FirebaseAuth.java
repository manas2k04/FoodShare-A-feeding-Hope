package com.manas.mainactivity;

public class FirebaseAuth {
    // Make sure to import the correct FirebaseAuth class
    private static com.google.firebase.auth.FirebaseAuth instance;

    private FirebaseAuth() {
        // private constructor to prevent instantiation
    }

    public static com.google.firebase.auth.FirebaseAuth getInstance() {
        if (instance == null) {
            instance = com.google.firebase.auth.FirebaseAuth.getInstance();
        }
        return instance;
    }
}
