package shop.donutmarket.donut.util;

import org.junit.jupiter.api.Test;

public class jwtTest {

    @Test
    public void jwt() {
        String secret = System.getenv("HS512_SECRET");
        System.out.println("테스트: "+secret);
    }
}
