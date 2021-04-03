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
public class Subscription {

    @Id
    private Integer subscriptionId;
    private final Integer userId;
    private final String alias;
    private final String merchantRefNumber;
    private final String merchantId;
    private final String customerName;
    private final String address;
    private final String shortDescription;
    private final String paymentMethod;
    private final String paymentAccount;
    private final String automaticPayment;
    private final Integer automaticMaxAmount;
    private final String automaticMaxDaysRetry;
    private final Date insertDate;
    private final Date lastUpdateDate;
    private final Integer cachedDueAmount;
    private final Date cachedDueCheckedDate;
    private final String cachedDueServiceResponse;
    private final String cachedDueErrorCode;
    private final String cachedDueErrorDescription;
    private final Integer cachedDueId;

}
