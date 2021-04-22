package kz.codesmith.epay.pkb.connector.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetReportResponse implements Serializable {
    @JacksonXmlProperty(localName = "GetReportResult")
    private GetReportResult reportResult;
}
