FROM jboss/keycloak:4.8.3.Final

ENV KEYCLOAK_VERSION 4.8.3.Final

ARG KEYCLOAK_DIST=https://downloads.jboss.org/keycloak/$KEYCLOAK_VERSION/keycloak-$KEYCLOAK_VERSION.tar.gz



COPY beercloak-ear/target/beercloak-1.0-SNAPSHOT.ear /opt/jboss/keycloak/standalone/deployments/

COPY standalone.xml /opt/jboss/keycloak/standalone/configuration/

EXPOSE 8080

ENTRYPOINT [ "/opt/jboss/tools/docker-entrypoint.sh" ]

CMD ["-b", "0.0.0.0"]