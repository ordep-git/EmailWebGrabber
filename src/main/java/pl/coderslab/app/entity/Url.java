package pl.coderslab.app.entity;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
    @URL(message = "Invalid  URL")
    private String url;

    @NotNull
    @ManyToMany
    @JoinTable(name = "emails_urls",
            joinColumns = @JoinColumn(name = "id_url"),
            inverseJoinColumns = @JoinColumn(name = "id_email"))
    private List<Email> emails = new ArrayList<>();



    public Url() {
    }

    public Url(String url, List<Email> emails) {
        this.url = url;
        this.emails = emails;
    }

    //    public Url(String url) {
//        this.url = url;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }
}
