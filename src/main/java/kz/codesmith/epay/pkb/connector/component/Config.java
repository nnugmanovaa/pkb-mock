package kz.codesmith.epay.pkb.connector.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Config {
  public static final String PROPERTY_SERVICES_SOURCE_PATH = "servicesSourcePath";
  public final ResourceLoader resourceLoader;

  @Autowired
  public Config(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }
}
