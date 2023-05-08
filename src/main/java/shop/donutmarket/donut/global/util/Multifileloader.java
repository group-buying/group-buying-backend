package shop.donutmarket.donut.global.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.global.aws.FileLoad;

@Configuration
@RequiredArgsConstructor
public class Multifileloader {

    private FileLoad fileLoad;
    
    public String profile(MultipartFile file, Long userId) throws IOException {
        // 로컬 파일에 저장
        String fileName = file.getOriginalFilename();
        String staticFolder = System.getProperty("user.dir") + "/src/main/resources/static/";
        Path localFilePath = Paths.get(staticFolder, fileName);
        Files.write(localFilePath, file.getBytes());
        
        // S3에 업로드
        fileLoad.uploadFile("User"+Long.toString(userId)+"profile" , localFilePath.toString());

        // 로컬 파일 삭제
        Files.delete(localFilePath);

        String filelink = fileLoad.downloadObject("User"+Long.toString(userId)+"profile");
        
        return filelink;
    }

    public String thumbnail(MultipartFile file, String title) throws IOException {
        // 로컬 파일에 저장
        String fileName = file.getOriginalFilename();
        String staticFolder = System.getProperty("user.dir") + "/src/main/resources/static/";
        Path localFilePath = Paths.get(staticFolder, fileName);
        Files.write(localFilePath, file.getBytes());
        
        // S3에 업로드
        fileLoad.uploadFile(title+"boardImg" , localFilePath.toString());

        // 로컬 파일 삭제
        Files.delete(localFilePath);

        String filelink = fileLoad.downloadObject(title+"boardImg");

        return filelink;
    }
}
