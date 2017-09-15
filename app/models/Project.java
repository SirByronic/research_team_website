package models;

import org.joda.time.DateTime;
import play.data.validation.Required;
import play.data.validation.URL;
import play.db.jpa.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aazzou on 10/11/14.
 */
@Entity
@Table(name="projects")
public class Project extends Model {
    @NotNull
    @Required
    public String name;
    @NotNull
    @Lob
    @Required
    public String description;
    public String organisation;
    public String partnership;
    @URL
    public String imgURL;
    public String imgID;
    public Date startDate;
    public Date finishDate;

    @ManyToOne
    public ResearchArea researchArea;


    @ManyToMany(cascade = CascadeType.ALL)
    public List<Member> members = new ArrayList<Member>();


    public Project() {}

    public Project(String name,
                   String description,
                   String organisation,
                   String partnership,
                   Date startDate,
                   Date finishDate,
                   String imgURL,
                   String imgID,
                   ResearchArea researchArea) {
        this.name = name;
        this.description = description;
        this.organisation = organisation;
        this.partnership = partnership;
        this.imgURL = imgURL;
        this.imgID = imgID;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.researchArea = researchArea;
    }
}
