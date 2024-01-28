package com.hotsse.vhere;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;

@SpringBootApplication
public class VHereApplication {

	public static void main(String[] args) {
		SpringApplication.run(VHereApplication.class, args);

		try {
			FileInputStream serviceAccount =
					new FileInputStream("/Users/heyho.se/storages/vhere/firebase/serviceAccountKey.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();

			FirebaseApp.initializeApp(options);

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
