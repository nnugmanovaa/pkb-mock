package kz.codesmith.epay.pkb.connector.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import kz.codesmith.epay.pkb.connector.model.kdn.KdnReqResponse;
import lombok.Data;

import java.io.Serializable;

@Data
public class Body implements Serializable {
    @JacksonXmlProperty(localName = "GetReportResponse")
    private GetReportResponse reportResponse;

    @JacksonXmlProperty(namespace = "ns2", localName = "kdnReqResponse")
    private KdnReqResponse kdnReqResponse;
}
