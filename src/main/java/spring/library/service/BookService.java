package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.controller.request.BookRequest;
import spring.library.domain.Book;
import spring.library.domain.BookHistory;
import spring.library.dto.BookDto;
import spring.library.repository.BookHistoryRepository;
import spring.library.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookHistoryRepository bookHistoryRepository;

    public BookDto save(BookRequest bookRequest) {
        BookHistory bookHistory = BookHistory.from(bookRequest);
        bookHistoryRepository.save(bookHistory);
        Book book = Book.from(bookRequest);
        bookRepository.save(book);

        return BookDto.from(book);
    }

    public List<BookDto> load(){
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = books.stream().map(BookDto::from).toList();
        return bookDtos;
    }

    @Transactional
    public BookDto update(Long bookId, BookRequest BookRequest) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("There is not book for endtered bookId"));
        BookHistory bookHistory = bookHistoryRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("null"));

        BookDto bookDto = BookDto.from(BookRequest);

        bookHistory.update(bookDto);
        book.update(bookDto);

        bookHistoryRepository.save(bookHistory);
        bookRepository.save(book);
        return bookDto;
    }

    public void delete(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("There is not book for endtered bookId"));
        if(book.getStatus().equals("대출중")){
            throw new IllegalArgumentException("you cannot delete book : this book is loaned");
        }
        bookRepository.deleteById(bookId);
    }
}
