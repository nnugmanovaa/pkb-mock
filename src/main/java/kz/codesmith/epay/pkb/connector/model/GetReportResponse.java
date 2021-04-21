package kz.codesmith.epay.pkb.connector.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class GetReportResponse {
    @JacksonXmlProperty(localName = "GetReportResult")
    private GetReportResult reportResult;
}
