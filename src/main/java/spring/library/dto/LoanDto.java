package spring.library.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.domain.Book;
import spring.library.domain.User;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class LoanDto {
    private Long id;
    private User user;
    private Book book;
    private LocalDateTime dueDate;
    private LocalDateTime loanDate;
    private int renewalCount;
    private Boolean isReturned;
}
