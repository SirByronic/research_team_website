package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aazzou on 10/16/14.
 */
@Entity
@Table(name="sections")
public class Section extends Model{
    @NotNull
    @Required
    public String name;
    public String description;

    @ManyToMany(mappedBy = "sections")
    public List<New> news = new ArrayList<New>();

    public Section() {
    }
    public Section(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
