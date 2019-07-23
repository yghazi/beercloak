package beercloak.events;

import beercloak.providers.BeerEntityProviderFactory;
import org.jboss.logging.Logger;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.admin.OperationType;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.jpa.JpaUserProvider;
import org.keycloak.models.jpa.entities.UserAttributeEntity;
import org.keycloak.models.jpa.entities.UserEntity;
import org.keycloak.services.resources.admin.UserResource;
import org.keycloak.services.resources.admin.UsersResource;
import org.keycloak.storage.UserStorageManager;
import org.keycloak.storage.UserStorageProvider;

import java.util.Map;
import java.util.Set;
import java.util.UUID;


public class BeerEventListenerProvider implements EventListenerProvider {

    private static final Logger LOG = Logger.getLogger(BeerEventListenerProvider.class);

    private Set<EventType> excludedEvents;
    private Set<OperationType> excludedAdminOperations;

    public BeerEventListenerProvider(Set<EventType> excludedEvents, Set<OperationType> excludedAdminOpearations) {
        this.excludedEvents = excludedEvents;
        this.excludedAdminOperations = excludedAdminOpearations;
    }

    @Override
    public void onEvent(Event event) {
        // Ignore excluded events
        LOG.info("EVENT: " + toString(event));

        // if event is REGISTER, then add id to the user because DAMN IT
        if (event.getType().compareTo(EventType.REGISTER) == 0) {
//            KeyCloak
//            JpaUserProvider p = new JpaUserProvider();


//            UsersResource usersResource = keycloak.realm(REALM).users();
//
//            UserResource userResource = new UsersResource(event.getRealmId(), null, null);
//                    usersResource.get("08afb701-fae5-40b4-8895-e387ba1902fb");

            UserAttributeEntity u = new UserAttributeEntity();
            UserEntity en = new UserEntity();
            en.setId(event.getUserId());
            en.setRealmId(event.getRealmId());
            u.setId(UUID.randomUUID().toString());
            u.setName("userid");
            u.setValue("string");
            u.setUser(en);
//
//           KeycloakSession s = new KeycloakSession();
//            UserStorageManager p = new UserStorageManager();
//           u.setId();
           event.getUserId();
        }
    }

    @Override
    public void onEvent(AdminEvent event, boolean includeRepresentation) {
        // Ignore excluded operations
        if (excludedAdminOperations != null && excludedAdminOperations.contains(event.getOperationType())) {
            return;
        } else {
            LOG.info("EVENT: " + toString(event));
            System.out.println("EVENT: " + toString(event));
        }
    }

    private String toString(Event event) {
        StringBuilder sb = new StringBuilder();

        sb.append("type=");
        sb.append(event.getType());
        sb.append(", realmId=");
        sb.append(event.getRealmId());
        sb.append(", clientId=");
        sb.append(event.getClientId());
        sb.append(", userId=");
        sb.append(event.getUserId());
        sb.append(", ipAddress=");
        sb.append(event.getIpAddress());

        if (event.getError() != null) {
            sb.append(", error=");
            sb.append(event.getError());
        }

        if (event.getDetails() != null) {
            for (Map.Entry<String, String> e : event.getDetails().entrySet()) {
                sb.append(", ");
                sb.append(e.getKey());
                if (e.getValue() == null || e.getValue().indexOf(' ') == -1) {
                    sb.append("=");
                    sb.append(e.getValue());
                } else {
                    sb.append("='");
                    sb.append(e.getValue());
                    sb.append("'");
                }
            }
        }

        return sb.toString();
    }

    private String toString(AdminEvent adminEvent) {
        StringBuilder sb = new StringBuilder();

        sb.append("operationType=");
        sb.append(adminEvent.getOperationType());
        sb.append(", realmId=");
        sb.append(adminEvent.getAuthDetails().getRealmId());
        sb.append(", clientId=");
        sb.append(adminEvent.getAuthDetails().getClientId());
        sb.append(", userId=");
        sb.append(adminEvent.getAuthDetails().getUserId());
        sb.append(", ipAddress=");
        sb.append(adminEvent.getAuthDetails().getIpAddress());
        sb.append(", resourcePath=");
        sb.append(adminEvent.getResourcePath());

        if (adminEvent.getError() != null) {
            sb.append(", error=");
            sb.append(adminEvent.getError());
        }

        return sb.toString();
    }

    @Override
    public void close() {
    }

}
