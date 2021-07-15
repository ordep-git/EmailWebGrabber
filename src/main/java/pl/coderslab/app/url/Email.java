package pl.coderslab.app.url;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "emails")
public class Email {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull
        private String email;

        public Email(){
        }

        public Email(Long id, @NotNull String email) {
                this.id = id;
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
