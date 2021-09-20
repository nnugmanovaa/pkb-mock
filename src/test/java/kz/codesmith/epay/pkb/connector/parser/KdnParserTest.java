package kz.codesmith.epay.pkb.connector.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class KdnParserTest {

    @Test
    void parse() throws IOException {
        Path filePath = Paths.get("src","test","resources", "KdnResponse.soap.xml");
        var content = Files.readString(filePath);

        var parser = new KdnParser();
        var applicationReport = parser.parse(content);

        assertEquals(0.12, applicationReport.getKdnScore());
        assertEquals(23874.17, applicationReport.getDebt());
        assertEquals(196859.67, applicationReport.getIncome());

        var respData = applicationReport.getIncomesResultCrtrV2().getResult().getResponseData().getData();
        assertEquals("FOUND", respData.getResponseCode());
        assertNotNull(respData.getDeductionsDetailed());
        assertNotEquals(0, respData.getDeductionsDetailed().size());
        assertEquals(6, respData.getDeductionsDetailed().size());
    }
}