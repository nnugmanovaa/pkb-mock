package kz.codesmith.epay.pkb.connector.config;

import kz.codesmith.epay.pkb.connector.service.CreditReportService;
import kz.codesmith.epay.pkb.connector.service.KdnService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {
    private final ConnectorProperties props;

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> {
            Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
            configurationMap.put(
                    CreditReportService.CACHE_NAME,
                    RedisCacheConfiguration.defaultCacheConfig().entryTtl(props.getCreditReportCacheTtl())
            );
            configurationMap.put(
                    KdnService.CACHE_NAME,
                    RedisCacheConfiguration.defaultCacheConfig().entryTtl(props.getKdnCacheTtl())
            );

            builder.withInitialCacheConfigurations(configurationMap);
        };
    }
}
