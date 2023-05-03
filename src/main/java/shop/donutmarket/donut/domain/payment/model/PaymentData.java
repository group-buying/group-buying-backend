package shop.donutmarket.donut.domain.payment.model;

import java.sql.Timestamp;

import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.global.util.JpaConverterJson;

@Embeddable
@Getter
@NoArgsConstructor
public class PaymentData {
    private String receiptId;
    private String orderId;
    private Long price;
    private Long taxFree;
    private Long cancelledPrice;
    private Long cancelledTaxFree;
    private String orderName;
    private String companyName;
    private String gatewayUrl;
    @Convert(converter = JpaConverterJson.class)
    private Object metadata;
    private String sandbox;
    private String pg;
    private String method;
    private String methodSymbol;
    private String methodOrigin;
    private String methodOriginSymbol;
    private String currency;
    private String receiptUrl;
    private Timestamp purchasedAt;
    private Timestamp cancelledAt;
    private Timestamp requestedAt;
    private String escrowStatusLocale;
    private String escrowStatus;
    private String statusLocale;
    private Long status;
    @Convert(converter = JpaConverterJson.class)
    private Object cardData;
    @Convert(converter = JpaConverterJson.class)
    private Object phoneData;
    @Convert(converter = JpaConverterJson.class)
    private Object bankData;
    @Convert(converter = JpaConverterJson.class)
    private Object vbankData;
}