package kz.codesmith.epay.pkb.connector.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CigResultError implements Serializable {
    @JacksonXmlProperty(localName = "Errmessage")
    private ErrorMessage errorMessage;
}
