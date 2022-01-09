package br.com.anacarriel.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "author", "launchDate", "price", "title"})
public class BookVO extends RepresentationModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;
    private String author;
    private Date launchDate;
    private float price;
    private String title;

    public BookVO(){}

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookVO)) return false;
        if (!super.equals(o)) return false;
        BookVO bookVO = (BookVO) o;
        return Float.compare(bookVO.getPrice(), getPrice()) == 0 && Objects.equals(getKey(), bookVO.getKey()) && Objects.equals(getAuthor(), bookVO.getAuthor()) && Objects.equals(getLaunchDate(), bookVO.getLaunchDate()) && Objects.equals(getTitle(), bookVO.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getAuthor(), getLaunchDate(), getPrice(), getTitle());
    }
}
