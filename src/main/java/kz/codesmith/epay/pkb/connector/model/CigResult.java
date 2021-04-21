package kz.codesmith.epay.pkb.connector.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class CigResult {
    @JacksonXmlProperty(localName = "DateTime")
    private String dateTime;
    @JacksonXmlProperty(localName = "Result")
    private Result result;
}
