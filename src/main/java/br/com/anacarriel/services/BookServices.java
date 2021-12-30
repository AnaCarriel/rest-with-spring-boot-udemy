package br.com.anacarriel.services;

import br.com.anacarriel.converter.DozerConverter;
import br.com.anacarriel.data.model.Book;
import br.com.anacarriel.data.vo.v1.BookVO;
import br.com.anacarriel.exception.ResourceNotFoundException;
import br.com.anacarriel.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServices {

    @Autowired
    BookRepository repository;

    public BookVO create(BookVO book){
        var entity = DozerConverter.parseObject(book, Book.class);
        var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public BookVO findById(long id){
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Norecords found for this ID"));
        return DozerConverter.parseObject(entity, BookVO.class);
    }

    public List<BookVO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);
    }

    public void delete (long id){
        Book entity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

    public BookVO update(BookVO book){
        var entity = repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        entity.setLauch_date(book.getLauchDate());

        var vo =  DozerConverter.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }
}
