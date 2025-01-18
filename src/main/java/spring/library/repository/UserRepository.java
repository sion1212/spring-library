package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.library.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
