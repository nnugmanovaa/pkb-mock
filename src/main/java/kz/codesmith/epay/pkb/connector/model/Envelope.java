package kz.codesmith.epay.pkb.connector.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(namespace = "S",localName = "Envelope")
public class Envelope {
    @JacksonXmlProperty(namespace = "S", localName = "Body")
    private Body body;
}
