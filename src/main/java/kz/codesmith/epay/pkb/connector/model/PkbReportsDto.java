package kz.codesmith.epay.pkb.connector.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PkbReportsDto {
  private String fullReport;
  private String standardReport;
  private String incomeReport;
}
