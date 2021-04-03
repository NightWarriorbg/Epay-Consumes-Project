package me.nightwarrior.epayconsumes.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Payment {

    @Id
    private final Integer paymentId;
    private final Integer userId;
    private final Integer merchantId;
    private final String merchantRefNo;
    private final String subscriptionId;
    private final Integer dueId;
    private final Date paymentDate;
    private final Integer amount;
    private final Integer totalAmount;
    private final Integer fee;
    private final String shortDesc;
    private final String longDesc;
    private final String paymentMethod;
    private final String paymentAccount;
    private final String transactionId;
    private final String dt;
    private final String epayStatus;
    private final String epayErrCode;
    private final String epayErrDesc;
    private final String accountingStatus;
    private final String accountingReference;

}
