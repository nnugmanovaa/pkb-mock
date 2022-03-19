package kz.codesmith.epay.pkb.connector.parser;

import kz.codesmith.epay.pkb.connector.component.Config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class XmlParser {
  private final Config config;

  public String parseToString(String iin) {
    String path = "classpath:reports/" + iin + "kdn.xml";
    try (InputStream inputStream = config.getResourceLoader()
        .getResource(path).getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
            StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
    ) {

      String content = bufferedReader.lines().collect(Collectors.joining("\n"));
      return content;
    } catch (Exception e) {
      log.error("Gateway response processing error", e);
      return "error";
    }
  }

  public String parseExtendedToString(String iin) {
    String path = "classpath:reports/" + iin + "extended.xml";
    try (InputStream inputStream = config.getResourceLoader()
        .getResource(path).getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
            StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
    ) {

      String content = bufferedReader.lines().collect(Collectors.joining("\n"));
      return content;
    } catch (Exception e) {
      log.error("Gateway response processing error", e);
      return "error";
    }
  }
}
