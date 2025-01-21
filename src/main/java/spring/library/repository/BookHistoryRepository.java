package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.library.domain.BookHistory;

public interface BookHistoryRepository extends JpaRepository<BookHistory, Long> {
}
