package shop.donutmarket.donut.global.exception;

import java.util.Map;

import lombok.Getter;

@Getter
public class MyValidException extends RuntimeException {
    private Map<String, String> errorMap;

    public MyValidException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }
}
