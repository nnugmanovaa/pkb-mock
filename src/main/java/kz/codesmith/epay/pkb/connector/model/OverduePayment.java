package kz.codesmith.epay.pkb.connector.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class OverduePayment implements Serializable {
    private LocalDate date;
    private int overdueDays;
    private String fine;
    private String penalty;
}
