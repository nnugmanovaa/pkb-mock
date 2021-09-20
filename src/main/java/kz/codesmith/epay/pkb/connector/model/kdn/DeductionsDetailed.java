package kz.codesmith.epay.pkb.connector.model.kdn;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeductionsDetailed implements Serializable {
    @JacksonXmlProperty(localName = "bin")
    private String bin;

    @JacksonXmlProperty(localName = "date")
    private String date;

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "amount")
    private Double amount;
}
