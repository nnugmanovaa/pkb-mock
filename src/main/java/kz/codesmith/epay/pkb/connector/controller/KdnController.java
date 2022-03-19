package kz.codesmith.epay.pkb.connector.controller;

import kz.codesmith.epay.pkb.connector.model.kdn.KdnRequest;
import kz.codesmith.epay.pkb.connector.parser.XmlParser;
import kz.codesmith.epay.pkb.connector.service.KdnService;
import kz.codesmith.epay.pkb.connector.service.ReportClientService;
import kz.codesmith.logger.Logged;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Logged
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(KdnController.PATH)
public class KdnController {
    public static final String PATH = "/pkb";

    private final KdnService kdnService;
    private final XmlParser xmlParser;

    @GetMapping("/extended/{iin}/raw")
    public ResponseEntity<String> getExtendedReportRaw(@PathVariable("iin") String iin) {
        return ResponseEntity.ok(xmlParser.parseExtendedToString(iin));
    }

    @GetMapping("/kdn/{iin}/raw")
    public ResponseEntity<String> getKdnRaw(@PathVariable("iin") String iin) {
        return ResponseEntity.ok(xmlParser.parseToString(iin));
    }
}
