package spring.library.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.domain.Book;
import spring.library.domain.Member;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class LoanDto {
    private Long id;
    private Member member;
    private Book book;
    private LocalDateTime dueDate;
    private LocalDateTime loanDate;
    private int renewalCount;
    private Boolean isReturned;
}
