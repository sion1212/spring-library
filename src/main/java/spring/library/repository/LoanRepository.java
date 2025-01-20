package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.library.domain.Loan;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
//    List<Loan> findByMember_MemberIdAndIsReturnedIsFalse(Long memberMemberId, Boolean isReturned);
    List<Loan> findLoanByMemberMemberIdAndIsReturnedIsFalse(Long memberMemberId);

    List<Loan> findLoanByMemberMemberId(Long member_memberId);
}
