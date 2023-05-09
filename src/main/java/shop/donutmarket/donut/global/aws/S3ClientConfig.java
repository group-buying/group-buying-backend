package shop.donutmarket.donut.global.aws;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3ClientConfig {

    private String accessKey = System.getenv("S3_ACCESS_KEY");
    private String secretKey = System.getenv("S3_SECRET_KEY");
    private String region = "ap-northeast-2";

    @Bean
    AmazonS3 amazonS3Client() {
        
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }
}
