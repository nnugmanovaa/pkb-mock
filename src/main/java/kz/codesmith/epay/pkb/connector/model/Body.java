package kz.codesmith.epay.pkb.connector.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Body {
    @JacksonXmlProperty(localName = "GetReportResponse")
    private GetReportResponse reportResponse;
}
