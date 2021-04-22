package kz.codesmith.epay.pkb.connector.config;

import kz.codesmith.epay.pkb.connector.parser.StandardReportResultParser;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final ConnectorProperties props;

    @Bean
    public CloseableHttpClient getHttpClient() {
        var keepAliveMillis = (int)props.getDefaultKeepAlive().toMillis();
        var poolSize = props.getConnectionPoolSize();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(poolSize);
        connManager.setDefaultMaxPerRoute(poolSize);
        connManager.setValidateAfterInactivity(keepAliveMillis);

        var timeout = (int)props.getConnectionTimeout().toMillis();
        var reqConfig = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout).build();

        return HttpClients.custom()
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(reqConfig)
                .build();
    }

    @Bean
    public StandardReportResultParser standardReportResultParser() {
        return new StandardReportResultParser();
    }
}
