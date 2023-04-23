package shop.donutmarket.donut.domain.payment.controller;

import kr.co.bootpay.Bootpay;
import kr.co.bootpay.model.request.Cancel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@RestController
@RequiredArgsConstructor
@RequestMapping("/bootpay")
public class BootpayController {
    static Bootpay bootpay = new Bootpay(
            "6441fb21755e27001be57d92",
            "4am5qAtenMx0arruzbExuuVi0vaxz84P1IgiVx/J9Vc=");

    // 결제 취소 기능
    @PostMapping("/receiptCancel/{id}")
    public ResponseEntity<?> receiptCancel(@PathVariable String id) {
        Cancel cancel = new Cancel();
        cancel.receiptId = id;
        cancel.cancelUsername = "관리자";
        cancel.cancelMessage = "결제 취소";

        try {
            HashMap<String, Object> res = bootpay.receiptCancel(cancel);
            if(res.get("error_code") == null) { //success
                return ResponseEntity.ok().body(res+"결제 취소 성공");
            } else { // fail
                return ResponseEntity.internalServerError().body(res+"결제 취소 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("결제 취소 실패");
    }
}
