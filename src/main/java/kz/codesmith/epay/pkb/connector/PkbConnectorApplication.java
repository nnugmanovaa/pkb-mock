package kz.codesmith.epay.pkb.connector;

import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {"kz.codesmith.epay.pkb", "kz.codesmith.logger"}
)
public class PkbConnectorApplication {

	public static void main(String[] args) {
		ElasticApmAttacher.attach();
		SpringApplication.run(PkbConnectorApplication.class, args);
	}

}
