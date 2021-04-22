package kz.codesmith.epay.pkb.connector.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WithdrawnApplications implements Serializable {
    @JacksonXmlProperty(isAttribute = true)
    private String stitle;

    @JacksonXmlProperty(localName = "PaymentsCalendar")
    private PaymentsCalendar paymentsCalendar;

    @JacksonXmlProperty(localName = "Contract")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Contract> contracts;
}
