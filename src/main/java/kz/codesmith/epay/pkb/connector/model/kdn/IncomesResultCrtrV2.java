package kz.codesmith.epay.pkb.connector.model.kdn;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class IncomesResultCrtrV2 implements Serializable {
    @JacksonXmlProperty(localName = "fcbStatus")
    private String fcbStatus;

    @JacksonXmlProperty(localName = "fcbMessage")
    private String fcbMessage;

    @JacksonXmlProperty(localName = "result")
    private IncomesResult result;
}
