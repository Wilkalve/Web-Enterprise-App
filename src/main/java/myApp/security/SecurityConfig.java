package myApp.security;

import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.PasswordHash;

/**
 * Security configuration class using annotations for Jakarta Security.
 * Registers roles and defines database-backed identity store.
 * 
 * @author wilfr
 */

@DatabaseIdentityStoreDefinition(
    dataSourceLookup = "jdbc/YourDataSource", 
    callerQuery = "SELECT password FROM appuser WHERE username = ?",
    groupsQuery = "SELECT groupname FROM user_groups WHERE username = ?",
    hashAlgorithm = PasswordHash.class, 
    priority = 10
)
@DeclareRoles({ "Admin", "WEBGroup", "RESTFullGroup" }) 
@ApplicationScoped
public class SecurityConfig {
   
}
