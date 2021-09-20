package kz.codesmith.epay.pkb.connector.model.kdn;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApplicationReport implements Serializable {
    @JacksonXmlProperty(namespace = "ns2", localName = "RequestId")
    private String requestId;

    @JacksonXmlProperty(namespace = "ns2", localName = "dateApplication")
    private String dateApplication;

    @JacksonXmlProperty(namespace = "ns2", localName = "IIN")
    private String iin;

    @JacksonXmlProperty(namespace = "ns2", localName = "kdn_score")
    private Double kdnScore;

    @JacksonXmlProperty(namespace = "ns2", localName = "debt")
    private Double debt;

    @JacksonXmlProperty(namespace = "ns2", localName = "income")
    private Double income;

    @JacksonXmlProperty(namespace = "ns2", localName = "incomesResultCrtrV2")
    private IncomesResultCrtrV2 incomesResultCrtrV2;

}
