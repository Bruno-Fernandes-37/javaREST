package controllers;

import Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repositories.BookRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("")
    public List<Book> index(){
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book show(@PathVariable int id){
        return bookRepository.findById(id).get();
    }

    @PostMapping("/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping("")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable int id, @RequestBody Map<String, String> body){
        Book book = bookRepository.findById(id).get();
        book.setTitle(book.getTitle());
        book.setAuthor(book.getAuthor());
        book.setDescription(book.getDescription());
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id){
        bookRepository.deleteById(id);
        return true;
    }
}