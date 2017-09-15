package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by aazzou on 10/11/14.
 */
@Entity
@Table(name="member_categories")
public class MemberCategory extends Model {
    @NotNull
    @Required(message = "validation.required")
    public String name;
    public String description;

    public MemberCategory() {}
    public MemberCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
