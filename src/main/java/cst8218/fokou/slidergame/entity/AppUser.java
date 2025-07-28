/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.fokou.slidergame.entity;

import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import java.io.Serializable;
import java.util.HashMap;


/**
 *
 * @author wilfr
 */
@Entity
@Table(name = "appuser")
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userid;

    @Column(nullable = false)
    private String password; // hashed password

    @Column(nullable = false)
    private String groupname;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    // Hide actual password content
    public String getPassword() {
        return ""; 
    }

    // Setter handles hashing
    public void setPassword(String rawPassword) {
        if (rawPassword != null && !rawPassword.isEmpty()) {
            var instance = CDI.current().select(Pbkdf2PasswordHash.class);
            var hasher = instance.get();
            hasher.initialize(new HashMap<>());
            this.password = hasher.generate(rawPassword.toCharArray());
        }
    }

    public String getPasswordHash() {
        return password;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @Override
    public String toString() {
        return "AppUser[ id=" + id + " ]";
    }
}
