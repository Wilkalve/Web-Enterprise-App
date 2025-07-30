package myApp.security;

import cst8218.fokou.slidergame.entity.AppUser;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;
import java.util.Base64;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// This class use a basic authentication implementation to restric the RESTFUll API access
// and only allow the RESTFullGroup and Admin to access the Restfull api
@Provider
@Priority(Priorities.AUTHENTICATION)
public class BasicAuthFilter implements ContainerRequestFilter {

    @Inject
    private UserService userService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            abortWithUnauthorized(requestContext);
            return;
        }

        String encoded = authHeader.substring("Basic ".length()).trim();
        String decoded = new String(Base64.getDecoder().decode(encoded));
        String[] parts = decoded.split(":", 2);

        if (parts.length != 2) {
            abortWithUnauthorized(requestContext);
            return;
        }

        String username = parts[0];
        String password = parts[1];

        AppUser user = userService.findAndValidateUser(username, password);

        if (user == null || !isAllowedRole(user.getGroupname())) {
            abortWithUnauthorized(requestContext);
            return;
        }

        String role = user.getGroupname();

        requestContext.setSecurityContext(new SecurityContext() {
            @Override public Principal getUserPrincipal() { return () -> username; }
            @Override public boolean isUserInRole(String r) { return r.equals(role); }
            @Override public boolean isSecure() { return false; } // Adjust if using HTTPS
            @Override public String getAuthenticationScheme() { return "BASIC"; }
        });
    }

    private boolean isAllowedRole(String role) {
        return "Admin".equals(role) || "RESTFullGroup".equals(role);
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
            .header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"ApplicationRealm\"")
            .build());
    }
}