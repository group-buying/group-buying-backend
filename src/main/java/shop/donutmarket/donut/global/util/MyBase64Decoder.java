package shop.donutmarket.donut.global.util;

import shop.donutmarket.donut.global.exception.Exception400;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class MyBase64Decoder {
    private static void checkExist(String base64Image) {
        if (base64Image.isEmpty()) {
            throw new Exception400("사진 파일이 전송되지 않았습니다");
        }
    }

    private static byte[] decode(String base64Image) {
        String[] parts = base64Image.split(",");
        // base64Data : data:image/png;base64, 없앤 base64 String 값
        String base64Data = parts[1];
        // 이진 데이터 디코딩
        byte[] decodedData = Base64.getDecoder().decode(base64Data);
        return decodedData;
    }

    private static String checkImage(byte[] decodedData) throws IOException {
        String mimeType = null;
        mimeType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(decodedData));

        if (!mimeType.startsWith("image/")) {
            throw new Exception400("사진 파일만 업로드가 가능합니다");
        }
        return mimeType;
    }

    public static String saveImage(String base64Image) throws IOException {
        checkExist(base64Image);
        byte[] decodedData = decode(base64Image);
        String mimeType = checkImage(decodedData);
        String staticFolder = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";
        UUID uuid = UUID.randomUUID();
        String filePath = "\\images\\" + uuid + "_" + System.currentTimeMillis() + "."
                + mimeType.split("/")[1];

        // filePath :
        // \images\ uuid값_시간.프로필사진.png
        Path imageFilePath = Paths.get(staticFolder + "\\" + filePath);
        Files.write(imageFilePath, decodedData);

        return imageFilePath.toString();
    }

    public static String decodeBase64(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }

}