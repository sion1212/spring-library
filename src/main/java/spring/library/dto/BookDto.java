package spring.library.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.controller.request.BookRequest;
import spring.library.domain.Book;

@Getter
@Setter
@Builder
public class BookDto {
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String classification;
    private String status;
    private String amount;
    private int userIdNumber;

    public static BookDto from(Book book) {
        return BookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publicationYear(book.getPublicationYear())
                .classification(book.getClassification())
                .status(book.getStatus())
                .amount(book.getAmount())
                .userIdNumber(book.getUser().getIdNumber())
                .build();
    }

    public static BookDto from(BookRequest bookRequest){
        return BookDto.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .publisher(bookRequest.getPublisher())
                .publicationYear(bookRequest.getPublicationYear())
                .classification(bookRequest.getClassification())
                .status(bookRequest.getStatus())
                .amount(bookRequest.getAmount())
                .build();
    }
}
