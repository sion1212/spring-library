package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.library.domain.Application;
import spring.library.domain.Member;

import java.time.LocalDate;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Application findApplicationByTitleAndAuthorAndRequestDate(String title, String author, LocalDate requestDate);

    List<Application> findApplicationByMemberMemberId(Long member_memberId);
}
