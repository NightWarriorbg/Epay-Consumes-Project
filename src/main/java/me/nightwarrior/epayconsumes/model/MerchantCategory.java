package me.nightwarrior.epayconsumes.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class MerchantCategory {

    private Integer categoryId;
    private final String categoryName;
    private final String categoryType;
    private final String categoryDescription;
    private final Integer listOrder;

}
