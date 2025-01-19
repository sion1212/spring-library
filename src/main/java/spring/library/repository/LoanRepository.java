package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.library.domain.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
