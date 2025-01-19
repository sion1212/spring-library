package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.controller.request.BookRequest;
import spring.library.dto.BookDto;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String classification;
    private String status;
    private String amount;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // 외래키 지정
    private User user;

    public static Book from(BookRequest bookRequest) {
        return Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .publisher(bookRequest.getPublisher())
                .publicationYear(bookRequest.getPublicationYear())
                .classification(bookRequest.getClassification())
                .status(bookRequest.getStatus())
                .amount(bookRequest.getAmount())
                .build();
    }

    public void update(BookDto bookDto){
        this.title = bookDto.getTitle();
        this.author = bookDto.getAuthor();
        this.publisher = bookDto.getPublisher();
        this.publicationYear = bookDto.getPublicationYear();
        this.classification = bookDto.getClassification();
        this.status = bookDto.getStatus();
        this.amount = bookDto.getAmount();
    }
}
