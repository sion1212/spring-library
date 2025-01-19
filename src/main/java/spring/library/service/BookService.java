package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.controller.request.BookRequest;
import spring.library.domain.Book;
import spring.library.dto.BookDto;
import spring.library.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BookDto save(BookRequest bookRequest) {
        Book book = Book.from(bookRequest);
        BookDto bookDto = BookDto.from(bookRepository.save(book));
        return bookDto;
    }

    public List<BookDto> load(){
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = books.stream().map(BookDto::from).toList();
        return bookDtos;
    }

    @Transactional
    public BookDto update(Long bookId, BookRequest BookRequest) {
        Book bookOfTitle = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("null"));
        BookDto bookDto = BookDto.from(BookRequest);
        bookOfTitle.update(bookDto);
        return bookDto;
    }

    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
