package kz.codesmith.epay.pkb.connector.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorMessage implements Serializable {
    @JacksonXmlProperty(isAttribute = true)
    private String code;

    @JacksonXmlText
    private String message;
}
