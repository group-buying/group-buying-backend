package shop.donutmarket.donut.global.exception;

import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import shop.donutmarket.donut.global.dto.ResponseDTO;

import java.io.IOException;

@RequiredArgsConstructor
@RestControllerAdvice
public class MyExceptionAdvice {
    // badRequest
    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> badRequest(Exception400 e){
        Sentry.captureException(e);
        return new ResponseEntity<>(e.body(), e.status());
    }

    // unAuthorized
    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> unAuthorized(Exception401 e){
        Sentry.captureException(e);
        return new ResponseEntity<>(e.body(), e.status());
    }

    // forbidden
    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> forbidden(Exception403 e){
        Sentry.captureException(e);
        return new ResponseEntity<>(e.body(), e.status());
    }

    // notFound
    // 자원을 못 찾은 경우
    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> notFound(Exception404 e){
        Sentry.captureException(e);
        return new ResponseEntity<>(e.body(), e.status());
    }

    // notFound
    // 주소를 못 찾은 경우
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> notFound(NoHandlerFoundException e){
        Sentry.captureException(e);
        ResponseDTO<String> responseDto = new ResponseDTO<>();
        responseDto.fail(HttpStatus.NOT_FOUND, "notFound", e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    // ServerError
    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> serverError(Exception500 e){
        Sentry.captureException(e);
        return new ResponseEntity<>(e.body(), e.status());
    }

    // ioError
    // 외부 통신 에러
    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> ioError(IOException e){
        Sentry.captureException(e);
        ResponseDTO<String> responseDto = new ResponseDTO<>();
        responseDto.fail(HttpStatus.FORBIDDEN, "IO Error", e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
    }

    // 유효성 에러
    @ExceptionHandler(MyValidException.class)
    public ResponseEntity<?> validationApiException(MyValidException e) {

        return new ResponseEntity<>(new ResponseDTO<>().fail(HttpStatus.BAD_REQUEST, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    // 나머지 모든 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> serverError(Exception e){
        Sentry.captureException(e);
        ResponseDTO<String> responseDto = new ResponseDTO<>();
        responseDto.fail(HttpStatus.INTERNAL_SERVER_ERROR, "unknownServerError", e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
