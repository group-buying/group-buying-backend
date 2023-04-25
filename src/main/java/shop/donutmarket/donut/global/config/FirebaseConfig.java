package shop.donutmarket.donut.global.config;

import java.io.FileInputStream;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init(){
        try{
            // json 파일의 인증 정보 가져오기
            FileInputStream serviceAccount = 
            	new FileInputStream("src/main/resources/firebase-adminsdk.json");

            // GoogleCredentials 생성
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
                    
            // firebase 초기화
            FirebaseApp.initializeApp(options);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}