package kz.codesmith.epay.pkb.connector.service;

import kz.codesmith.epay.pkb.connector.component.CreditReportClient;
import kz.codesmith.epay.pkb.connector.exception.UnsupportedReportException;
import kz.codesmith.epay.pkb.connector.model.CigResult;
import kz.codesmith.epay.pkb.connector.model.OverduePayment;
import kz.codesmith.epay.pkb.connector.parser.StandardReportResultParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditReportService {
    public static final String CACHE_NAME = "pkb-credit-rep";
    public static final String OVERDUES_CACHE_NAME = "pkb-overdue";

    private final CreditReportClient reportClient;
    private final StandardReportResultParser standardReportResultParser;

    public String getCreditReportRaw(String iin, String creditReportId) {
        return reportClient.getReport(iin, creditReportId);
    }

    @SneakyThrows
    @Cacheable(
            value = CACHE_NAME,
            key = "{#iin, #creditReportId}",
            unless = "#result == null || #result.result == null"
    )
    public CigResult getCreditReportParsed(String iin, String creditReportId) {
        var report = getCreditReportRaw(iin, creditReportId);
        if ("6".equals(creditReportId)) {
            return standardReportResultParser.parse(report);
        } else {
            throw new UnsupportedReportException("Report id " + creditReportId + " is unsupported");
        }
    }

    @Cacheable(OVERDUES_CACHE_NAME)
    public List<OverduePayment> getOverduePayments(String iin, String creditReportId, Long period, Long overdueDays) {
        var report = getCreditReportParsed(iin, creditReportId);
        if ("6".equals(creditReportId)) {
            var overdues = standardReportResultParser.getAllOverduePayments(report);
            if (period != null) {
                return standardReportResultParser.overdueInLastPeriod(period, overdueDays, overdues);
            } else {
                return overdues;
            }
        } else {
            throw new UnsupportedReportException("Report id " + creditReportId + " is unsupported");
        }
    }
}
