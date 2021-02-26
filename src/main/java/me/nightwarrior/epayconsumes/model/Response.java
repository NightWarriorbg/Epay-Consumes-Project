package me.nightwarrior.epayconsumes.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat()
    @NonNull
    private  EpayResponseStatus status;

    @JsonFormat(pattern="yyyyMMdd")
    private final Date validto;
    private final Integer amount;
    private final Integer fee;
    private final Integer total;
    private final String shortDesc;
    private final String longDesc;
    private final String secondId;

    @Setter
    private  String errorCode;
    @Setter
    private  String errorDes;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum EpayResponseStatus {

        SUCCESS("00"),
        NO_PENDING_DUE("62"),
        MERCHANT_REF_NOT_FOUND("14"),
        INVALID_CODE("58"),
        CARD_LOCKED("75"),
        TEMPORARILY_UNAVL("80"),
        GENERAL_ERROR("96"),
        INVALID_DEPOSIT_AMOUNT("13"),
        INVALID_FORMAT("805"),
        DUE_IS_BLOCKED("70"),
        REPEATED_MSG("94"),
        OLD_DUE_PENDING("49"),
        PERIOD_UNAVL("50"),
        NO_INFO_FOR_MERCHANT_REF_NO("52");

        private String status;

        private EpayResponseStatus(String status) {
            this.status = status;
        }

        @JsonValue
        public String getStatus() {
            return status;
        }


    }

}