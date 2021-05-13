FROM amazoncorretto:11.0.9

WORKDIR /app

COPY build/libs/pkb-connector-0.0.2.jar pkb-connector.jar

EXPOSE 8080

COPY elastic-apm-agent-1.13.0.jar elastic-apm-agent.jar

ENTRYPOINT ["java"]

CMD [ "-XX:+UnlockExperimentalVMOptions", \
      "-javaagent:/app/elastic-apm-agent.jar", "-Delastic.apm.service_name=pkb-connector", \
      "-Delastic.apm.server_urls=http://apm-server:8200", \
      "-Delastic.apm.application_packages=kz.codesmith.epay.pkb", \
      "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/pkb-connector.jar" ]
