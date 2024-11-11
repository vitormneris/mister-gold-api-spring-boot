package com.mistergold.mistergold.adapters.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;

public class FirebaseInit {
    private static final String CREDENTIALS_PATH = "run-multiplataforma-firebase-adminsdk-wjjq1-9d6b461f8b.json";
    public static final String BUCKET_NAME = "run-multiplataforma.appspot.com";
    private static final String PROJECT_ID = "834671810002";

    public static Storage storage() throws IOException {
        ClassPathResource serviceAccount = new ClassPathResource(CREDENTIALS_PATH);
        return StorageOptions.newBuilder().
                setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream())).
                setProjectId(PROJECT_ID).build().getService();
    }
}
