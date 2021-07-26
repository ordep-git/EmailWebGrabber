package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.url.Email;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
