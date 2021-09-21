package kz.codesmith.epay.pkb.connector.controller;

import kz.codesmith.epay.pkb.connector.model.kdn.KdnRequest;
import kz.codesmith.epay.pkb.connector.service.KdnService;
import kz.codesmith.logger.Logged;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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
    public static final String PATH = "/kdn";

    private final KdnService kdnService;

    @PutMapping
    public ResponseEntity<?> getKdn(
            @RequestBody KdnRequest kdnRequest
    ) {
        var resp = kdnService.getKdn(kdnRequest);
        return ResponseEntity.ok(resp);
    }
}
