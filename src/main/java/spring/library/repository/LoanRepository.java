package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.library.domain.Loan;
import spring.library.domain.Member;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findLoanByMemberMemberIdAndIsReturnedIsFalse(Long memberMemberId);

    List<Loan> findLoanByMemberMemberId(Long member_memberId);

    Loan findLoanByBookHistory_IdAndMemberMemberId(Long bookHistory_Id, Long memberMemberId);

    Loan findLoanByBookHistory_IdAndMember_MemberIdAndIsReturned(Long bookHistory_Id, Long member_MemberId, boolean isReturned);
}
