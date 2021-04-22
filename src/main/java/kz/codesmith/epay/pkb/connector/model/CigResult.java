package kz.codesmith.epay.pkb.connector.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CigResult implements Serializable {
    @JacksonXmlProperty(localName = "DateTime")
    private String dateTime;
    @JacksonXmlProperty(localName = "Result")
    private Result result;
}
