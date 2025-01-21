package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.library.domain.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findLoanByMemberMemberIdAndIsReturnedIsFalse(Long memberMemberId);

    List<Loan> findLoanByMemberMemberId(Long member_memberId);

    Loan findLoanByBook_IdAndMember_MemberId(Long bookId, Long memberMemberId);

    Loan findLoanByBook_IdAndMember_MemberIdAndIsReturned(Long bookId, Long memberMemberId, Boolean isReturned);
}
