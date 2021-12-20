FROM amazoncorretto:11.0.9

WORKDIR /app

COPY build/libs/pkb-connector-0.0.2.jar pkb-connector.jar

COPY src/main/resources/secure.1cb.kz.crt ca-cert-secure.1cb.kz.crt
RUN $JAVA_HOME/bin/keytool -import -noprompt -trustcacerts -alias secure.1cb.kz -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -file ca-cert-secure.1cb.kz.crt

COPY src/main/resources/secure1.1cb.kz.crt ca-cert-secure1.1cb.kz.crt
RUN $JAVA_HOME/bin/keytool -import -noprompt -trustcacerts -alias secure1.1cb.kz -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -file ca-cert-secure1.1cb.kz.crt
EXPOSE 8080

ENTRYPOINT ["java"]

CMD [ "-XX:+UnlockExperimentalVMOptions", \
      "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/pkb-connector.jar" ]
