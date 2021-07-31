package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.app.entity.Email;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

//    @Query("select distinct e from Email e")
//    List<Email> findDistinctByEmail(List<Email> email);

    @Query(value = "SELECT DISTINCT emails.id, emails.email, urls.url FROM emails, emails_urls, urls WHERE emails.id = emails_urls.id_email AND emails_urls.id_url = urls.id", nativeQuery = true)
    List<Email> findAllByEmails();
}
