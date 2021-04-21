package kz.codesmith.epay.pkb.connector.controller;

import kz.codesmith.epay.pkb.connector.model.CigResult;
import kz.codesmith.epay.pkb.connector.model.OverduePayment;
import kz.codesmith.epay.pkb.connector.service.CreditReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(CreditReportController.PATH)
public class CreditReportController {
    public static final String PATH = "/credit-report";

    private final CreditReportService reportService;

    @GetMapping(value = "/{iin}/{reportId}/raw", produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> getCreditReportRaw(
            @PathVariable String iin,
            @PathVariable String reportId
    ) {
        var report = reportService.getCreditReportRaw(iin, reportId);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{iin}/{reportId}/parsed")
    public ResponseEntity<CigResult> getCreditReportParsed(
            @PathVariable String iin,
            @PathVariable String reportId
    ) {
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
        return ResponseEntity.ok(report);
    }
}
