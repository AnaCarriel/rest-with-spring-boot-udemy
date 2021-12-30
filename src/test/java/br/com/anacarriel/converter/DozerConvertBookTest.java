package br.com.anacarriel.converter;

import br.com.anacarriel.converter.mocks.MockBook;
import br.com.anacarriel.converter.mocks.MockPerson;
import br.com.anacarriel.data.model.Book;
import br.com.anacarriel.data.model.Person;
import br.com.anacarriel.data.vo.v1.BookVO;
import br.com.anacarriel.data.vo.v1.PersonVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class DozerConvertBookTest {
    MockBook inputObject;

    @Before
    public void setUp() {
        inputObject = new MockBook();
    }

    @Test
    public void parseEntityToVOTest() {
        BookVO output = DozerConverter.parseObject(inputObject.mockEntity(), BookVO.class);
        Assert.assertEquals(Long.valueOf(0L), output.getKey());
        Assert.assertEquals("Ana Maria Machado0", output.getAuthor());
        Assert.assertEquals("Rosas dos ventos0", output.getTitle());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<BookVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), BookVO.class);
        BookVO outputZero = outputList.get(0);

        Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
        Assert.assertEquals("Ana Maria Machado0", outputZero.getAuthor());
        Assert.assertEquals("Rosas dos ventos0", outputZero.getTitle());

        BookVO outputSeven = outputList.get(7);

        Assert.assertEquals(Long.valueOf(7L), outputSeven.getKey());
        Assert.assertEquals("Ana Maria Machado0", outputZero.getAuthor());
        Assert.assertEquals("Rosas dos ventos0", outputZero.getTitle());

        BookVO outputTwelve = outputList.get(12);

        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        Assert.assertEquals("Ana Maria Machado0", outputZero.getAuthor());
        Assert.assertEquals("Rosas dos ventos0", outputZero.getTitle());
    }

    @Test
    public void parseVOToEntityTest() {
        Book output = DozerConverter.parseObject(inputObject.mockVO(), Book.class);
        Assert.assertEquals("Ana Maria Machado0", output.getAuthor());
        Assert.assertEquals("Rosas dos ventos0", output.getTitle());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Book> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Book.class);
        Book outputZero = outputList.get(0);

        Assert.assertEquals("Ana Maria Machado0", outputZero.getAuthor());
        Assert.assertEquals("Rosas dos ventos0", outputZero.getTitle());

        Book outputSeven = outputList.get(7);

        Assert.assertEquals("Ana Maria Machado7", outputSeven.getAuthor());
        Assert.assertEquals("Rosas dos ventos7", outputSeven.getTitle());

        Book outputTwelve = outputList.get(12);

        Assert.assertEquals("Ana Maria Machado12", outputTwelve.getAuthor());
        Assert.assertEquals("Rosas dos ventos12", outputTwelve.getTitle());
    }
}