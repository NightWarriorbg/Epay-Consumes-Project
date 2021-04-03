package me.nightwarrior.epayconsumes.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class MerchantMockup {

    private final Integer merchantId;
    private final String fullName;
    private final String fullNameEn;
    private final String webName;
    private final String subscrNumberType;
    private final Boolean active;
    private final Integer category;
    private final String categoryName;
    private final Integer merchantType;
    private final Boolean merchantPartial;
    private final Boolean merchantInvoice;
    private final String merchantShortDesc;
    private final String merchantLongDesc;
    private final String subscrNumberRe;
    private final String subscrNumberReHelp;
}
