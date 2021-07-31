package pl.coderslab.app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
    private String email;

//    @NotEmpty
    @ManyToMany(mappedBy = "emails", cascade = CascadeType.REMOVE)
    private List<Url> urls = new ArrayList<>();

    @ManyToMany(mappedBy = "emails", cascade = CascadeType.REMOVE)
    private List<Keywords> keywords = new ArrayList<>();

    public void addUrl(Url url) {
        urls.add(url);
    }

    public void addKeyword(Keywords keyword) {
        keywords.add(keyword);
    }

    public Email() {
    }
    public Email(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
