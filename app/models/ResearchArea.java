package models;

import play.data.validation.Required;
import play.data.validation.URL;
import play.db.jpa.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by aazzou on 10/11/14.
 */
@Entity
@Table(name="research_areas")
public class ResearchArea extends Model {
    @NotNull
    @Required
    public String name;
    @NotNull
    @Lob
    @Required
    public String description;
    @URL
    public String imgURL;
    public String imgID;

    public ResearchArea() {}
    public ResearchArea(String name,
                        String description,
                        String imgURL,
                        String imgID) {
        this.name = name;
        this.description = description;
        this.imgURL = imgURL;
        this.imgID = imgID;
    }
}
