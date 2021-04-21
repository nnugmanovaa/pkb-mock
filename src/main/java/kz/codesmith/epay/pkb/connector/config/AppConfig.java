package kz.codesmith.epay.pkb.connector.config;

import kz.codesmith.epay.pkb.connector.parser.StandardReportResultParser;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final ConnectorProperties props;

    @Bean
    public CloseableHttpClient getHttpClient() {
        var timeout = (int)props.getConnectionTimeout().toMillis();
        var reqConfig = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout).build();

        return HttpClients.custom()
                .setDefaultRequestConfig(reqConfig)
                .build();
    }

    @Bean
    public StandardReportResultParser standardReportResultParser() {
        return new StandardReportResultParser();
    }
}
