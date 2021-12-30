package br.com.anacarriel.services;

import br.com.anacarriel.converter.DozerConverter;
import br.com.anacarriel.data.model.Book;
import br.com.anacarriel.data.vo.v1.BookVO;
import br.com.anacarriel.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServices {

    @Autowired
    BookRepository repository;

    public BookVO createBook(BookVO book){
        var entity = DozerConverter.parseObject(book, Book.class);
        var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }
}
