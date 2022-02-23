package kz.codesmith.epay.pkb.connector.model;

import kz.codesmith.epay.pkb.connector.model.kdn.KdnRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PkbReportsRequest {
  private String iin;
  private KdnRequest kdnRequest;
}
