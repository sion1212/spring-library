package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Loan {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private Date dueDate;
    private Date loanDate;

    @Builder.Default
    private int renewalCount = 1;

    @Builder.Default
    private Boolean isReturned = false;
}
