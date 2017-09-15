package models;

import play.data.validation.URL;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by aazzou on 10/21/14.
 */
@Entity
@Table(name="carousel_imgs")
public class CarouselImg extends Model {
    @NotNull
    public String name;
    @NotNull
    @URL
    public String url;
    @NotNull
    public boolean active;

    public CarouselImg() {
    }
    public CarouselImg(String name, String url,boolean active) {
        this.name = name;
        this.url = url;
        this.active = active;
    }
}
