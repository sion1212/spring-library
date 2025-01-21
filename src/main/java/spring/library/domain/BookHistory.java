package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.controller.request.BookRequest;
import spring.library.dto.BookDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_history_id")
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String classification;
    private String status;
    @OneToMany(mappedBy = "bookHistory", cascade = CascadeType.ALL)
    private List<Loan> loanList = new ArrayList<>();

    public static BookHistory from(BookRequest bookRequest) {
        return BookHistory.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .publisher(bookRequest.getPublisher())
                .publicationYear(bookRequest.getPublicationYear())
                .classification(bookRequest.getClassification())
                .status(bookRequest.getStatus())
                .build();
    }

    public void update(BookDto bookDto){
        this.title = bookDto.getTitle();
        this.author = bookDto.getAuthor();
        this.publisher = bookDto.getPublisher();
        this.publicationYear = bookDto.getPublicationYear();
        this.classification = bookDto.getClassification();
        this.status = bookDto.getStatus();
    }
}
