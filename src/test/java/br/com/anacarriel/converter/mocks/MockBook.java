package br.com.anacarriel.converter.mocks;

import br.com.anacarriel.data.model.Book;
import br.com.anacarriel.data.vo.v1.BookVO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MockBook {

    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockVO() {
        return mockVO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }

    private Book mockEntity(Integer number) {
        Book book = new Book();
        book.setTitle("Rosas dos ventos" + number);
        book.setAuthor("Ana Maria Machado" + number);
        book.setLaunch_date(new Date(1196-10-25));
        book.setId(number.longValue());
        book.setPrice(123 + number);
        return book;
    }

    private BookVO mockVO(Integer number) {
        BookVO book = new BookVO();
        book.setTitle("Rosas dos ventos" + number);
        book.setAuthor("Ana Maria Machado" + number);
        book.setLaunchDate(new Date(1196-10-25));
        book.setKey(number.longValue());
        book.setPrice(123 + number);
        return book;
    }
}
