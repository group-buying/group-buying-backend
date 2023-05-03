package shop.donutmarket.donut.global.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseDTO<T> {
    private Integer status; // Exception시에 상태코드
    private String msg; // Exception시에 제목
    private T data; // Exception시에 상세 내용

    public ResponseDTO() {
        this.status = HttpStatus.OK.value();
        this.msg = "성공";
    }

    public ResponseDTO(T data){
        this.status = HttpStatus.OK.value();
        this.msg = "성공";
        this.data = data; // 응답할 데이터 바디
    }

    public ResponseDTO<?> fail(HttpStatus status, String msg, T data) {
        this.status = status.value();
        this.msg = msg;
        this.data = data;
        return this;
    }
}
