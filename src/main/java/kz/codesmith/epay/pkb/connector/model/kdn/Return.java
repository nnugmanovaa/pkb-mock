package kz.codesmith.epay.pkb.connector.model.kdn;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Return implements Serializable {
    @JacksonXmlProperty(namespace = "ns2", localName = "applicationReport")
    private ApplicationReport applicationReport;

    @JacksonXmlProperty(namespace = "ns2", localName = "errorCode")
    private String errorCode;

    @JacksonXmlProperty(namespace = "ns2", localName = "errorMessage")
    private String errorMessage;

    @JacksonXmlProperty(namespace = "ns2", localName = "ReportDate")
    private String reportDate;

    @JacksonXmlProperty(namespace = "ns2", localName = "Id")
    private String id;
}
