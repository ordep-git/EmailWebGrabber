package pl.coderslab.app.url;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

    //    @NotEmpty
    @ManyToMany(mappedBy = "emails")
    private List<Url> urls = new ArrayList<>();


    public Email() {
    }

    public Email(String email) {
        this.email = email;
    }

//    public Email(Email email) {
//    }

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
