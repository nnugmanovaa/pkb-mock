package kz.codesmith.epay.pkb.connector.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Root {
    @JacksonXmlProperty(localName = "Title")
    private Title title;

    @JacksonXmlProperty(localName = "ExistingContracts")
    private ExistingContracts existingContracts;

    @JacksonXmlProperty(localName = "TerminatedContracts")
    private TerminatedContracts terminatedContracts;

    @JacksonXmlProperty(localName = "WithdrawnApplications")
    private WithdrawnApplications withdrawnApplications;
}
