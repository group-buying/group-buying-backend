package shop.donutmarket.donut.global.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.donutmarket.donut.global.dto.ResponseDto;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class MyExceptionAdvice {
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> ex(Exception e) {
//        ResponseDto<?> responseDTO = new ResponseDto<>().fail(500, "오류", HttpStatus.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> badRequest(Exception400 e){
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> unAuthorized(Exception401 e){
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> forbidden(Exception403 e){
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> notFound(Exception404 e){
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> serverError(Exception500 e){
        return new ResponseEntity<>(e.body(), e.status());
    }
}
