package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.BookRequest;
import spring.library.controller.response.BookResponse;
import spring.library.dto.BookDto;
import spring.library.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest) {
        BookDto bookDto = bookService.save(bookRequest);
        BookResponse bookResponse = BookResponse.from(bookDto);
        return ResponseEntity.ok().body(bookResponse);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> getBooks(){
        List<BookDto> bookDtoList = bookService.load();
        List<BookResponse> bookResponseList = bookDtoList.stream().map(BookResponse::from).toList();
        return ResponseEntity.ok().body(bookResponseList);
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long bookId, @RequestBody BookRequest bookRequest) {
        BookDto bookDto = bookService.update(bookId, bookRequest);
        return ResponseEntity.ok().body(BookResponse.from(bookDto));
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        bookService.delete(bookId);
        return ResponseEntity.ok().build();
    }
}
