package jp.co.smartsolar.smartedge.database.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * The persistent class for the account database table.
 *
 */
@Entity
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
@NamedQuery(name = "Account.deleteAll", query = "DELETE FROM Account a")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer           id;

    private String            password;

    @Column(name = "user_id")
    private String            userId;

    public Account() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}