package kz.codesmith.epay.pkb.connector.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.Serializable;

@Data
@JacksonXmlRootElement(namespace = "S",localName = "Envelope")
public class Envelope implements Serializable {
    @JacksonXmlProperty(namespace = "S", localName = "Body")
    private Body body;
}
