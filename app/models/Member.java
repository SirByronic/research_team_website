package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


import play.data.validation.Email;
import play.data.validation.Required;
import play.data.validation.URL;
import play.db.jpa.*;
/**
 * Created by aazzou on 10/11/14.
 */
@Entity
@Table(name="members")
public class Member extends Model {
    @NotNull
    @Required
    public String fullName;
    @NotNull
    @Required
    public String institution;
    @Lob
    public String presentation;
    @NotNull
    @Email
    @Required
    public String email;
    public String telephone;
    public String fax;
    @URL
    public String url;
    @NotNull
    @Required
    public Date joinDate;
    @URL
    public String photoURL;
    public String photoID;

    // Member Category
    @NotNull
    @ManyToOne
    public MemberCategory category;

    @ManyToMany(mappedBy = "members")
    public List<Publication> publications = new ArrayList<Publication>();

    @ManyToMany(mappedBy = "members")
    public List<Project> projects = new ArrayList<Project>();


    public Member() {};
    public Member(String fullName,
                  String institution,
                  String presentation,
                  String email,
                  String telephone,
                  String fax,
                  String url,
                  Date joinDate,
                  String photoURL,
                  String photoID,
                  MemberCategory category) {
        this.fullName = fullName;
        this.institution = institution;
        this.presentation = presentation;
        this.email = email;
        this.telephone = telephone;
        this.fax = fax;
        this.url = url;
        this.joinDate = joinDate;
        this.photoID = photoID;
        this.photoURL = photoURL;
        this.category = category;
    }
}
