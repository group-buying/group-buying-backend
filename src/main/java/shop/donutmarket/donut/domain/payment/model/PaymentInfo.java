package shop.donutmarket.donut.domain.payment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String event;
    private String receiptId;
    private String orderId;
    private Long price;
    private Long taxFree;
    private Long cancelledPrice;
    private Long cancelledTaxFree;
    private String orderName;
    private String companyName;
    private String gatewayUrl;
    private String metadata;
    private boolean sandbox;
    private String pg;
    private String method;
    private String methodSymbol;
    private String methodOrigin;
    private String methodOriginSymbol;
    private String currency;
    private String receiptUrl;
    @CreationTimestamp
    private LocalDateTime purchasedAt;
    @CreationTimestamp
    private LocalDateTime cancelledAt;
    @CreationTimestamp
    private LocalDateTime requestedAt;
    private String escrowStatusLocale;
    private String escrowStatus;
    private String statusLocale;
    private Long status;
    private Long cardApproveNo;
    private Long cardNo;
    private Long cardQuota;
    private String cardCompanyCode;
    private String cardCompany;
    private String cardReceiptUrl;
    private Long authNo;
    private Long phone;
    private Long bankCode;
    private String bankName;
    private Long cashReceiptNo;
    private Long vBankCode;
    private String vBankName;
    private Long vBankAccount;
    private String vBankUsername;
    private String senderName;
    @CreationTimestamp
    private LocalDateTime vBankExpiredAt;
    private String vBankCashReceiptNo;
}
