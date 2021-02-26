package me.nightwarrior.epayconsumes.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String transactionId;
    @JsonFormat(pattern="yyyyMMdd")
    private final String dt;
    private final Double amount;
    private final String merchantId;
    private final String subscrNumber;
}
