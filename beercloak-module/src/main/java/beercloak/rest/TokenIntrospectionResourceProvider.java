package beercloak.rest;

import beercloak.events.BeerEventListenerProvider;
import org.jboss.logging.Logger;
import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Produces;

public class TokenIntrospectionResourceProvider implements RealmResourceProvider {

    private KeycloakSession session;

    public TokenIntrospectionResourceProvider(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public Object getResource() {
        return this;
    }

    @POST
    @Produces("text/plain; charset=utf-8")
    public String get() {
        String name = session.getContext().getRealm().getDisplayName();
        if (name == null) {
            name = session.getContext().getRealm().getName();
        }
        return "Hello " + name;
    }

    @Override
    public void close() {
    }

}