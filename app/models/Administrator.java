package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by aazzou on 10/22/14.
 */
@Entity
@Table(name="administrators")
public class Administrator extends Model {
    @NotNull
    public String username;
    @NotNull
    public String password;

    public Administrator(){}
    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
