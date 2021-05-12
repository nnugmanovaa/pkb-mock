package kz.codesmith.epay.pkb.connector.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetReportResult implements Serializable {
    @JacksonXmlProperty(localName = "CigResult")
    private CigResult cigResult;

    @JacksonXmlProperty(localName = "CigResultError")
    private CigResultError cigResultError;
}
