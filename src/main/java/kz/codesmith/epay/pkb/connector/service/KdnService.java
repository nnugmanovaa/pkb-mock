package kz.codesmith.epay.pkb.connector.service;

import kz.codesmith.epay.pkb.connector.component.KdnClient;
import kz.codesmith.epay.pkb.connector.model.kdn.ApplicationReport;
import kz.codesmith.epay.pkb.connector.model.kdn.KdnRequest;
import kz.codesmith.epay.pkb.connector.parser.KdnParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class KdnService {
    public static final String CACHE_NAME = "pkb-kdn";

    private final KdnClient kdnClient;
    private final KdnParser kdnParser;

    @Retryable(value = IOException.class, maxAttempts = 2, backoff = @Backoff(delay = 3_000))
    public String getKdnRaw(KdnRequest request) {
        return kdnClient.getKdnRaw(request);
    }


    @Cacheable(
            value = CACHE_NAME,
            key = "{#kdnRequest.iin}",
            unless = "#result == null"
    )
    public ApplicationReport getKdn(KdnRequest kdnRequest) {
        var raw = getKdnRaw(kdnRequest);
        return kdnParser.parse(raw);
    }
}
