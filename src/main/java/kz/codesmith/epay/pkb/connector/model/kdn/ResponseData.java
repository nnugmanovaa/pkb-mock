package kz.codesmith.epay.pkb.connector.model.kdn;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseData implements Serializable {
    @JacksonXmlProperty(localName = "data")
    private kz.codesmith.epay.pkb.connector.model.kdn.Data data;
}
