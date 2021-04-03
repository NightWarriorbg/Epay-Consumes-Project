package me.nightwarrior.epayconsumes.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Registration {

    private final String automaticPayment;
    private final String customerAddress;
    private final String customerName;
    private final String merchantId;
    private final String merchantRefNo;
    private final String payAccount;
    private final String payFrom;
    private final String retryDays;
    private final String shortDesc;
    private final String subscriptionAlias;
    private final String subscriptionId;
}
