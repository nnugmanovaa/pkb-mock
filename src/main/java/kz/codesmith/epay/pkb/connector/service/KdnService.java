package kz.codesmith.epay.pkb.connector.service;

import kz.codesmith.epay.pkb.connector.model.kdn.ApplicationReport;
import kz.codesmith.epay.pkb.connector.model.kdn.KdnRequest;
import kz.codesmith.epay.pkb.connector.parser.KdnParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class KdnService {
    private final KdnParser kdnParser;
    private final ReportClientService reportClientService;

    public static final String KDN_CACHE_NAME = "pkb-kdn";

    @Cacheable(
        value = KDN_CACHE_NAME,
        key = "{#kdnRequest.iin}",
        unless = "#result == null"
    )
    public ApplicationReport getKdn(KdnRequest kdnRequest) {
        var raw = reportClientService.getKdnRaw(kdnRequest);
        return kdnParser.parse(raw);
    }
}
