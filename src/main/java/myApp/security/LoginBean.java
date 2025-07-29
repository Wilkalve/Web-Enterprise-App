package myApp.security;

import cst8218.fokou.slidergame.controller.AppUserJpaController;
import cst8218.fokou.slidergame.entity.AppUser;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.transaction.UserTransaction;
import java.util.HashMap;

@Named("loginBean")
@RequestScoped
public class LoginBean {

    private String userid;
    private String password;

  @Inject
private Pbkdf2PasswordHash hasher;

    @PostConstruct
    public void init() {
        if (hasher == null) {
            hasher = CDI.current().select(Pbkdf2PasswordHash.class).get();
            hasher.initialize(new HashMap<>());
        }
    }

    @PersistenceUnit(unitName = "my_persistence_unit")
    private EntityManagerFactory emf;

    @Resource
    private UserTransaction utx;

    public String login() {
        try {
            System.out.println("Login attempt: userid=" + userid);
            System.out.println("EntityManagerFactory: " + (emf == null ? "null" : "ready"));
            System.out.println("UserTransaction: " + (utx == null ? "null" : "ready"));

            AppUserJpaController controller = new AppUserJpaController(utx, emf);
            AppUser user = controller.findUserByUserid(userid);

            if (user != null) {
                String storedHash = user.getPasswordHash();
                boolean valid = hasher.verify(password.toCharArray(), storedHash);

                System.out.println("User found: " + user.getUserid());
                System.out.println("Stored hash: " + storedHash);
                System.out.println("Password valid: " + valid);

                if (valid) {
                    FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getSessionMap()
                        .put("loggedUser", user);

                    switch (user.getGroupname()) {
                        case "Admin":
                            return "/faces/slider/Create.xhtml?faces-redirect=true";
                        case "WEBGroup":
                            return "/faces/slider/Create.xhtml?faces-redirect=true";
                        case "RESTFullGroup":
                            return "/faces/api/index.xhtml?faces-redirect=true";
                        default:
                            return "/faces/index.xhtml?faces-redirect=true";
                    }
                }
            }

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", "Invalid credentials"));

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login error", e.getMessage()));
        }

        return null;
    }

    // Getters and setters
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
