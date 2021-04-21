package kz.codesmith.epay.pkb.connector.component;

import kz.codesmith.epay.pkb.connector.config.ConnectorProperties;
import kz.codesmith.epay.pkb.connector.exception.PkbReportRequestFailed;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreditReportClient {
    private final ConnectorProperties props;
    private final CloseableHttpClient httpClient;

    public static final String REQUEST_TEMPLATE = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
            "    xmlns:ws=\"http://ws.creditinfo.com/\">\n" +
            "    <soapenv:Header>\n" +
            "        <ws:CigWsHeader>\n" +
            "            <ws:Culture>ru-RU</ws:Culture>\n" +
            "            <ws:Password>%s</ws:Password>\n" +
            "            <ws:UserName>%s</ws:UserName>\n" +
            "        </ws:CigWsHeader>\n" +
            "    </soapenv:Header>\n" +
            "    <soapenv:Body>\n" +
            "        <ws:GetReport>\n" +
            "            <ws:doc>\n" +
            "                <keyValue >\n" +
            "                    <reportImportCode>%s</reportImportCode>\n" +
            "                    <idNumber>%s</idNumber>\n" +
            "                    <idNumberType>14</idNumberType>\n" +
            "                    <idNumberCode>Entity.Identification.Type.Iin</idNumberCode>\n" +
            "                    <ConsentConfirmed>1</ConsentConfirmed>>\n" +
            "                </keyValue>\n" +
            "            </ws:doc>\n" +
            "        </ws:GetReport>\n" +
            "    </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    @SneakyThrows
    public String getReport(String iin, String reportId) {
        var post = new HttpPost(props.getUrl());
        var soapRequestBody = String.format(REQUEST_TEMPLATE, props.getPassword(), props.getUsername(), reportId, iin);
        var report = "";

        post.addHeader("Content-Type", "text/xml;charset=UTF-8");

        var entity = new StringEntity(soapRequestBody);
        post.setEntity(entity);

        try (
                CloseableHttpResponse response = httpClient.execute(post)
        ) {
            var respStatusCode = response.getStatusLine().getStatusCode();
            if(respStatusCode == 200) {
                report = EntityUtils.toString(response.getEntity());
            } else {
                throw new PkbReportRequestFailed("Failed with status code " + respStatusCode);
            }
        } catch (IOException e) {
            log.error("Can't get report due IOException");
        }
        return report;
    }
}
