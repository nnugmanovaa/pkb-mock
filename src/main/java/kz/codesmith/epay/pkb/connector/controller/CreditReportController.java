package kz.codesmith.epay.pkb.connector.controller;

import kz.codesmith.epay.pkb.connector.model.CigResult;
import kz.codesmith.epay.pkb.connector.model.OverduePayment;
import kz.codesmith.epay.pkb.connector.model.PkbReportsDto;
import kz.codesmith.epay.pkb.connector.model.PkbReportsRequest;
import kz.codesmith.epay.pkb.connector.model.kdn.KdnRequest;
import kz.codesmith.epay.pkb.connector.service.CreditReportService;
import kz.codesmith.epay.pkb.connector.service.ReportClientService;
import kz.codesmith.logger.Logged;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Logged
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(CreditReportController.PATH)
public class CreditReportController {
    public static final String PATH = "/credit-report";

    private final CreditReportService reportService;
    private final ReportClientService reportClientService;

    @GetMapping(value = "/{iin}/{reportId}/raw", produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> getCreditReportRaw(
            @PathVariable String iin,
            @PathVariable String reportId
    ) {
        log.info("GET raw report {}, for {}", reportId, iin);
        var report = reportClientService.getCreditReportRaw(iin, reportId);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{iin}/{reportId}/parsed")
    public ResponseEntity<CigResult> getCreditReportParsed(
            @PathVariable String iin,
            @PathVariable String reportId
    ) {
        log.info("GET parsed report {}, for {}", reportId, iin);
        var report = reportService.getCreditReportParsed(iin, reportId);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{iin}/{reportId}/overdues")
    public ResponseEntity<List<OverduePayment>> getOverduePayments(
            @PathVariable String iin,
            @PathVariable String reportId,
            @RequestParam Long period,
            @RequestParam Long amount
    ) {
        var report = reportService.getOverduePayments(iin, reportId, period, amount);
        log.info("OverduePayments report response for {}: {}", iin, report);
        return ResponseEntity.ok(report);
    }

    @PostMapping("/all")
    public ResponseEntity<PkbReportsDto> getPkbReports(@RequestBody PkbReportsRequest request) {
        PkbReportsDto reportsDto = new PkbReportsDto();
        reportsDto.setFullReport(reportClientService
            .getCreditReportRaw(request.getIin(), "6"));
        reportsDto.setStandardReport(reportClientService
            .getCreditReportRaw(request.getIin(), "4"));
        reportsDto.setIncomeReport(reportClientService
            .getKdnRaw(request.getKdnRequest()));
        return ResponseEntity.ok(reportsDto);
    }
}
