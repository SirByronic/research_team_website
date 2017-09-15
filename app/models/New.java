package models;

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
@Table(name="news")
public class New extends Model {
    @NotNull
    @Required
    public String title;
    @NotNull
    @Lob
    @Required
    public String summary;
    @NotNull
    @Lob
    @Required
    public String content;
    @URL
    public String imgURL;
    public String imgID;
    public Date datePublication;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Section> sections = new ArrayList<Section>();

    public New() {}

    public New(String title,
               String summary,
               String content,
               String imgURL,
               String imgID) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.imgID = imgID;
        this.imgURL = imgURL;
    }
}
