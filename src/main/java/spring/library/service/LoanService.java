package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.controller.request.MemberIdRequest;
import spring.library.domain.Book;
import spring.library.domain.Loan;
import spring.library.domain.Member;
import spring.library.dto.BookDto;
import spring.library.dto.LoanDto;
import spring.library.repository.BookRepository;
import spring.library.repository.LoanRepository;
import spring.library.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public LoanDto loanBook(Long bookId, MemberIdRequest memberIdRequest) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("null"));
        Member member = memberRepository.findById(memberIdRequest.getMemberId()).orElseThrow(() -> new IllegalArgumentException("null"));
        Loan loan = Loan.from(book, member);
        return LoanDto.from(loanRepository.save(loan));
    }

    public List<LoanDto> LoanedBookList(Long memberId) {
        List<Loan> loanList = loanRepository.findLoanByMemberMemberIdAndIsReturnedIsFalse(memberId);
        return loanList.stream().map(LoanDto::from).toList();
    }
}
