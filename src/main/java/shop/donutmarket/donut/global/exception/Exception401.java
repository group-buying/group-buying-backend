package shop.donutmarket.donut.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import shop.donutmarket.donut.global.dto.ResponseDto;

// 인증 안됨
@Getter
public class Exception401 extends RuntimeException{
    public Exception401(String message) {
        super(message);
    }

    public ResponseDto<?> body(){
        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.fail(HttpStatus.UNAUTHORIZED, "unAuthorized", getMessage());
        return responseDto;
    }

    public HttpStatus status(){
        return HttpStatus.UNAUTHORIZED;
    }
}
