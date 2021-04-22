package kz.codesmith.epay.pkb.connector.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Payment implements Serializable {
    @JacksonXmlProperty(localName = "overdue", isAttribute = true)
    private String overdue;

    @JacksonXmlProperty(localName = "value", isAttribute = true)
    private String overdueDays;

    @JacksonXmlProperty(localName = "number", isAttribute = true)
    private int month;

    @JacksonXmlProperty(localName = "fine", isAttribute = true)
    private String fine;

    @JacksonXmlProperty(localName = "penalty", isAttribute = true)
    private String penalty;
}
