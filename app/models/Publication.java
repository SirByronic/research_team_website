package models;

import play.data.validation.Required;
import play.data.validation.URL;
import play.db.jpa.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aazzou on 10/11/14.
 */
@Entity
@Table(name="publications")
public class Publication extends Model {
    @NotNull
    @Required
    public String title;
    @NotNull
    @Required
    public Date datePublication;
    @NotNull
    @Required
    public String journal;
    public String journalSpecificTitle;
    @NotNull
    @Required
    public String pagesRange;
    @URL
    public String url;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Member> members = new ArrayList<Member>();

    public Publication() {}
    public Publication(String title,
                       Date datePublication,
                       String journal,
                       String journalSpecificTitle,
                       String pagesRange,
                       String url) {
        this.title = title;
        this.datePublication = datePublication;
        this.journal = journal;
        this.journalSpecificTitle = journalSpecificTitle;
        this.pagesRange = pagesRange;
        this.url = url;
    }
}
