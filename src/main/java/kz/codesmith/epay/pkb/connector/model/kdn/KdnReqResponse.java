package kz.codesmith.epay.pkb.connector.model.kdn;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class KdnReqResponse implements Serializable {
    @JacksonXmlProperty(namespace = "ns2", localName = "return")
    private Return aReturn;
}
