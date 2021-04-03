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
public class Due {

    private Integer dueId;
    private final Integer userId;
    private final Integer merchantId;
    private final String merchantRefNo;
    private final Integer subscriptionId;
    private final Date dueDate;
    private final Integer amount;
    private final Integer totalAmount;
    private final Integer fee;
    private final String shortDesc;
    private final String longDesc;
    private final String secondId;
    private final String dueStatus;
}
