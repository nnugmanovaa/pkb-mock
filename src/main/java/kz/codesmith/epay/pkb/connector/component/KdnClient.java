package kz.codesmith.epay.pkb.connector.component;

import kz.codesmith.epay.pkb.connector.config.ConnectorProperties;
import kz.codesmith.epay.pkb.connector.exception.PkbReportRequestFailed;
import kz.codesmith.epay.pkb.connector.model.kdn.KdnRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@RequiredArgsConstructor
public class KdnClient {
    private final ConnectorProperties props;
    private final CloseableHttpClient httpClient;

    public static final String REQUEST_TEMPLATE = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
            "    <soap:Header>\n" +
            "        <ns2:CigWsHeader xmlns:ns7=\"http://bip.bee.kz/SyncChannel/v10/Interfaces\"\n" +
            "            xmlns:ns6=\"http://web.incomes.service.fcb.com/\"\n" +
            "            xmlns:ns5=\"http://bip.bee.kz/SyncChannel/v10/Types\"\n" +
            "            xmlns:ns4=\"http://tempuri.org/\"\n" +
            "            xmlns:ns3=\"http://www.w3.org/2000/09/xmldsig#\"\n" +
            "            xmlns:ns2=\"http://kdn2.ws.creditinfo.com/\">\n" +
            "            <ns2:Culture>RU</ns2:Culture>\n" +
            "            <ns2:Password>%s</ns2:Password>\n" +
            "            <ns2:UserName>%s</ns2:UserName>\n" +
            "        </ns2:CigWsHeader>\n" +
            "    </soap:Header>\n" +
            "    <soap:Body>\n" +
            "        <ns2:StorekdnReqV2 xmlns:ns2=\"http://kdn2.ws.creditinfo.com/\"\n" +
            "            xmlns:ns3=\"http://www.w3.org/2000/09/xmldsig#\"\n" +
            "            xmlns:ns4=\"http://tempuri.org/\"\n" +
            "            xmlns:ns5=\"http://bip.bee.kz/SyncChannel/v10/Types\"\n" +
            "            xmlns:ns6=\"http://web.incomes.service.fcb.com/\"\n" +
            "            xmlns:ns7=\"http://bip.bee.kz/SyncChannel/v10/Interfaces\">\n" +
            "            <ns2:applicationReq>\n" +
            "                <ns2:IIN>%s</ns2:IIN>\n" +
            "                <ns2:Lastname>%s</ns2:Lastname>\n" +
            "                <ns2:Firstname>%s</ns2:Firstname>\n" +
            "                <ns2:Middlename>%s</ns2:Middlename>\n" +
            "                <ns2:Birthdate>%s</ns2:Birthdate>\n" +
            "                <ns2:consentConfirmed>1</ns2:consentConfirmed>\n" +
            "            </ns2:applicationReq>\n" +
            "        </ns2:StorekdnReqV2>\n" +
            "    </soap:Body>\n" +
            "</soap:Envelope>";

    @SneakyThrows
    public String getKdnRaw(KdnRequest request) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var birthday = request.getBirthdate().format(formatter);
        var post = new HttpPost(props.getKdnUrl());
        var soapRequestBody = String.format(
                REQUEST_TEMPLATE,
                props.getPassword(),
                props.getUsername(),
                request.getIin(),
                request.getLastname(),
                request.getFirstname(),
                request.getMiddlename(),
                birthday
        );

        post.addHeader("Content-Type", "text/xml;charset=UTF-8");

        var entity = new StringEntity(soapRequestBody);
        post.setEntity(entity);

        var kdnResp = "";

        try (
                CloseableHttpResponse response = httpClient.execute(post)
        ) {
            var respStatusCode = response.getStatusLine().getStatusCode();
            if(respStatusCode == 200) {
                kdnResp = EntityUtils.toString(response.getEntity());
                EntityUtils.consume(response.getEntity());
            } else {
                throw new PkbReportRequestFailed("Failed with status code " + respStatusCode);
            }
        } catch (IOException e) {
            log.error("Can't get KDN due IOException\nPOST request body:\n{}", soapRequestBody);
            throw e;
        }
        return kdnResp;
    }
}
