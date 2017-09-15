package models;

import play.db.jpa.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;



/**
 * Created by aazzou on 10/14/14.
 */
@Entity
@Table(name="events")
public class Event extends Model {
   @NotNull
   public String title;
   @Lob
   public String summary;
   @Lob
   public String content;
   public Date date;


    public Event() {
    }

    public Event(String title, String summary, String content, Date date) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.date = date;
    }

}
