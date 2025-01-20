package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.controller.request.MemberIdRequest;
import spring.library.domain.Book;
import spring.library.domain.Loan;
import spring.library.domain.Member;
import spring.library.dto.LoanDto;
import spring.library.repository.BookRepository;
import spring.library.repository.LoanRepository;
import spring.library.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


// TODO : 책 빌릴 때 이미 빌린 상황의 예외상황 처리해줘야함
@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public LoanDto loanBook(Long bookId, MemberIdRequest memberIdRequest) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("null"));
        Member member = memberRepository.findById(memberIdRequest.getMemberId()).orElseThrow(() -> new IllegalArgumentException("null"));

        if(isOverMaximum(member)){
            throw new IllegalArgumentException("you can not loan over maximum");
        }
        if(isLoanedBook(book)){
            throw new IllegalArgumentException("this book is already loaned");
        }

        book.setStatus("대출중");
        Loan loan = Loan.from(book, member);
        return LoanDto.from(loanRepository.save(loan));
    }

    public boolean isOverMaximum(Member member){
        Long memberId = member.getMemberId();
        String feature = member.getFeature();
        boolean result = false;

        int size = loanRepository.findLoanByMemberMemberId(memberId).size();
        if(feature.equals("학생")){
            if(size >= 10){
                result = true;
            }
        }
        else if(feature.equals("교직원")){
            if(size >= 20){
                result = true;
            }
        }
        else {
            if(size >= 100){
                result = true;
            }
        }
        return result;
    }

    public boolean isLoanedBook(Book book) {
        boolean result = false;
        String status = book.getStatus();

        if(status.equals("대출중")){
            result = true;
        }
        return result;
    }

    public List<LoanDto> loanedBookList(Long memberId) {
        List<Loan> loanList = loanRepository.findLoanByMemberMemberIdAndIsReturnedIsFalse(memberId);
        return loanList.stream().map(LoanDto::from).toList();
    }

    public List<LoanDto> loanHistory(Long memberId) {
        List<Loan> loanList = loanRepository.findLoanByMemberMemberId(memberId);
        return loanList.stream().map(LoanDto::from).toList();
    }

    public void returnBook(Long bookId, MemberIdRequest memberIdRequest) {
        Loan loan = loanRepository.findLoanByBook_IdAndMember_MemberId(bookId,memberIdRequest.getMemberId());
        loan.setIsReturned(true);
        loan.getBook().setStatus("대출가능");
        loanRepository.save(loan);
    }

    // TODO: 대출한 도서 대상임
    public void renewal(Long bookId, MemberIdRequest memberIdRequest) {
        Loan loan = loanRepository.findLoanByBook_IdAndMember_MemberIdAndIsReturned(bookId,memberIdRequest.getMemberId(),false);
        int renewalCount = loan.getRenewalCount();
        LocalDate dueDate = loan.getDueDate();

        if(renewalCount == 1){
            if(Objects.equals(dueDate, LocalDate.now())){
                loan.setRenewalCount(0);
                loan.setDueDate(loan.getDueDate().plusDays(5));
            }
            else{
                throw new IllegalArgumentException("you can not loan renewal");
            }
        }
        else{
            throw new IllegalArgumentException("you already renewed");
        }

        loanRepository.save(loan);
    }
}
