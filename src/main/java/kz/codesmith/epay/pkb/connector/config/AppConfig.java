package kz.codesmith.epay.pkb.connector.config;

import kz.codesmith.epay.pkb.connector.parser.KdnParser;
import kz.codesmith.epay.pkb.connector.parser.StandardReportResultParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;


@Configuration
@EnableRetry
@RequiredArgsConstructor
public class AppConfig {
    private final ConnectorProperties props;

    @Bean
    @SneakyThrows
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

        var trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }

                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };
        var sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

        return HttpClients.custom()
                .setConnectionManager(connManager)
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .setSSLContext(sslContext)
                .setDefaultRequestConfig(reqConfig)
                .build();
    }

    @Bean
    public StandardReportResultParser standardReportResultParser() {
        return new StandardReportResultParser();
    }

    @Bean
    public KdnParser kdnParser() {
        return new KdnParser();
    }
}
