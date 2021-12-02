package kz.codesmith.epay.pkb.connector.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import kz.codesmith.epay.pkb.connector.exception.KdnRequestFailed;
import kz.codesmith.epay.pkb.connector.exception.PkbReportRequestFailed;
import kz.codesmith.epay.pkb.connector.model.Envelope;
import kz.codesmith.epay.pkb.connector.model.kdn.ApplicationReport;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class KdnParser {
    public ApplicationReport parse(String kdnRawResp) {
        var xmlMapper = new XmlMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            var parsedValue = xmlMapper.readValue(kdnRawResp, Envelope.class);
            var retrn = parsedValue.getBody().getKdnReqResponse().getAReturn();
            if (Objects.nonNull(retrn.getErrorCode())) {
                if ("4".equals(retrn.getErrorCode())) {
                  ApplicationReport applicationReport = parsedValue
                      .getBody()
                      .getKdnReqResponse()
                      .getAReturn()
                      .getApplicationReport();

                  applicationReport.setErrorCode(retrn.getErrorCode());
                  applicationReport.setErrorMessage(retrn.getErrorMessage());
                  return applicationReport;
                } else if (!"0".equals(retrn.getErrorCode())) {
                    var errCode = retrn.getErrorCode();
                    var errMsg = retrn.getErrorMessage();

                    log.warn(errCode + " - " + errMsg);
                    throw new KdnRequestFailed(errCode + " - " + errMsg);
                }
            }

            return parsedValue.getBody().getKdnReqResponse().getAReturn().getApplicationReport();
        } catch (Exception e) {
            log.error("Failed to parse PKB response: " + kdnRawResp);
            throw new PkbReportRequestFailed("Failed to parse PKB KDN response");
        }
    }
}
