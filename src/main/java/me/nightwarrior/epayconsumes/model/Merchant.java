package me.nightwarrior.epayconsumes.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Merchant {

    private final Integer merchantId;
    private final Integer categoryId;
    private final String merchantName;
    private final String merchantDescription;
    private final Integer listOrder;

}