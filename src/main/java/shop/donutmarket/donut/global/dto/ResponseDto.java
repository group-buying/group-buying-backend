package shop.donutmarket.donut.global.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseDto<T> {
    private Integer status;
    private String msg; // 제목
    private T data; // 상세내용

    public ResponseDto() {
        this.status = 200;
        this.msg = "성공";
    }

    public ResponseDto<?> data(T data) {
        this.data = data;
        return this;
    }

    public ResponseDto<?> fail(HttpStatus status, String msg, T data) {
        this.status = status.value();
        this.msg = msg;
        this.data = data;
        return this;
    }
}
