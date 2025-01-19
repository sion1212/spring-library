package spring.library.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.dto.BookDto;

@Getter
@Setter
@Builder
public class BookResponse {
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String classification;
    private String status;
    private String amount;

    public static BookResponse from(BookDto bookDto) {
        return BookResponse.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .publicationYear(bookDto.getPublicationYear())
                .classification(bookDto.getClassification())
                .status(bookDto.getStatus())
                .amount(bookDto.getAmount())
                .build();
    }
}
