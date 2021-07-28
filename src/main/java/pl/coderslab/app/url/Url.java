package pl.coderslab.app.url;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String url;

    //    @NotNull
    @ManyToMany
    @JoinTable(name = "emails_urls",
            joinColumns = @JoinColumn(name = "id_url"),
            inverseJoinColumns = @JoinColumn(name = "id_email"))
    private List<Email> emails = new ArrayList<>();

    public Url() {
    }

    public Url(String url) {
        this.url = url;
    }

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
