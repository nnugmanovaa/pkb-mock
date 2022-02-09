package kz.codesmith.epay.pkb.connector.service;

import java.io.IOException;
import java.util.HashSet;
import kz.codesmith.epay.pkb.connector.component.CreditReportClient;
import kz.codesmith.epay.pkb.connector.component.KdnClient;
import kz.codesmith.epay.pkb.connector.model.kdn.KdnRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportClientService {
  public static final String REPORT_RAW_CACHE_NAME = "pkb-report-raw";
  public static final String KDN_RAW_CACHE_NAME = "pkb-kdn-raw";

  private final CreditReportClient reportClient;
  private final KdnClient kdnClient;

  @Cacheable(
      value = REPORT_RAW_CACHE_NAME,
      key = "{#iin, #creditReportId}",
      unless = "#result == null"
  )
  @Retryable(value = IOException.class, maxAttempts = 2, backoff = @Backoff(delay = 3_000))
  public String getCreditReportRaw(String iin, String creditReportId) {
    return reportClient.getReport(iin, creditReportId);
  }

  @Cacheable(
      value = KDN_RAW_CACHE_NAME,
      key = "{#kdnRequest.iin}",
      unless = "#result == null"
  )
  @Retryable(value = IOException.class, maxAttempts = 2, backoff = @Backoff(delay = 3_000))
  public String getKdnRaw(KdnRequest kdnRequest) {
    return kdnClient.getKdnRaw(kdnRequest);
  }
}
