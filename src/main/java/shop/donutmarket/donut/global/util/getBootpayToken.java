package shop.donutmarket.donut.global.util;

import kr.co.bootpay.Bootpay;

import java.util.HashMap;

public class getBootpayToken {

    public static void goGetToken(Bootpay bootpay) {
        try {
            HashMap<String, Object> res = bootpay.getAccessToken();
            if(res.get("error_code") == null) { //success
                System.out.println("goGetToken success: " + res);
            } else {
                System.out.println("goGetToken false: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
