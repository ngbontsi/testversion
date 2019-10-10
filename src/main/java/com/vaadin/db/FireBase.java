package com.vaadin.db;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;

public class FireBase {

    private static FirebaseApp app;


    private FireBase() {
        throw new IllegalStateException("Utility class");
    }


    public static void initialise() throws Exception {

        if (app != null) {
            return;
        }


        FileInputStream serviceAccount =
                new FileInputStream("com/vaadin/db/serviceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://ngfarms-cd0d9.firebaseio.com")
                .build();

//        FirebaseApp.initializeApp(options);



//       options FileInputStream serviceAccount =
//                new FileInputStream(new File(String.valueOf(FireBase.class.getClassLoader().getResourceAsStream("ngfarms-cd0d9-firebase-adminsdk-rkyef-e295d9c734.json"))));
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl("https://ngfarms-cd0d9.firebaseio.com")
//                .build();

        app = FirebaseApp.initializeApp(options);

        maybeGenerateData();

    }

    /**
     * Creates some initial data if the database is empty
     */
    private static void maybeGenerateData() {
        getDb().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // Wait for initial data before deciding to create or not
//                CustomerService.maybeCreateInitialData(snapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public static DatabaseReference getDb() {
        return FirebaseDatabase.getInstance(app).getReference();
    }

}
