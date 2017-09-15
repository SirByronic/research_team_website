package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by aazzou on 10/23/14.
 */
@Entity
@Table(name="about_informations")
public class About extends Model {
    @NotNull
    @Required
    @Lob
    public String hometxt;
    @NotNull
    @Required
    @Lob
    public String overview;
    @NotNull
    @Required
    @Lob
    public String highlights;
    @NotNull
    @Required
    @Lob
    public String projectsAndResearch;
    @NotNull
    @Required
    @Lob
    public String cooperation;


    public About() {
    }

    public About(String hometxt,String overview, String highlights, String projectsAndResearch, String cooperation) {
        this.hometxt = hometxt;
        this.overview = overview;
        this.highlights = highlights;
        this.projectsAndResearch = projectsAndResearch;
        this.cooperation = cooperation;
    }
}
