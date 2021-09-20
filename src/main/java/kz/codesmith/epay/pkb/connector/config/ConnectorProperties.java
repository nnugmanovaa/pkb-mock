package kz.codesmith.epay.pkb.connector.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Data
@Configuration
@NoArgsConstructor
@ConfigurationProperties("app.pkb")
public class ConnectorProperties {
    private String url;
    private String kdnUrl;
    private String username;
    private String password;
    private Duration connectionTimeout;
    private Duration defaultKeepAlive;
    private int connectionPoolSize;
    private Duration creditReportCacheTtl;
}
