/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myApp.security;
import cst8218.fokou.slidergame.entity.AppUser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author wilfr
 */
// This class is a link between the RESTful API and the existing user authentication system. 
//Its job is to centralize and encapsulate all the logic for user lookup and credential validation

@ApplicationScoped
public class UserService {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Inject
    private Pbkdf2PasswordHash hasher;

    public AppUser findAndValidateUser(String username, String password) {
        try {
            AppUser user = em.createQuery("SELECT u FROM AppUser u WHERE u.userid = :username", AppUser.class)
                             .setParameter("username", username)
                             .getSingleResult();

            if (user != null && hasher.verify(password.toCharArray(), user.getPasswordHash())) {
                return user;
            }
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        return null;
    }
}