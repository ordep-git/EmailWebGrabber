package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.app.entity.Keywords;

@Repository
public interface KeywordsRepository extends JpaRepository<Keywords, Long> {
}
