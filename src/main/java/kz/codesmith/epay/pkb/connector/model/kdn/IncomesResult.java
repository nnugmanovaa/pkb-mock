package kz.codesmith.epay.pkb.connector.model.kdn;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class IncomesResult implements Serializable {
    @JacksonXmlProperty(localName = "responseData")
    private ResponseData responseData;
}
