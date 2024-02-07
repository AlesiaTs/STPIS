package com.example.alesiaproj;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
@Component
public class FirebaseInitialization {
    public FirebaseInitialization() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/authKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp.initializeApp(options);

    }
}
