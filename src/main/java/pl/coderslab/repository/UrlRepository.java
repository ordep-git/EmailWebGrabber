package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.app.entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
}
