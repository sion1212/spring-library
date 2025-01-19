package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.library.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
