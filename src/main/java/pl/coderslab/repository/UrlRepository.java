package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.url.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
}
