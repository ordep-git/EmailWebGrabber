package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.url.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {
//    Email findByEmail();
}
