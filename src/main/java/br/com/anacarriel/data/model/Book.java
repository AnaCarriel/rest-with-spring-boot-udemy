package br.com.anacarriel.data.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="books")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the Contact.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Name author of the book.",
            example = "Tarcila do amaral", required = true)
    @Column(name = "author", nullable = false, length = 80)
    private String author;

    @Schema(description = "Lauch date of the book.",
            example = "1", required = true)
    @Column(name = "lauchDate", nullable = false, length = 11)
    private Date lauch_date;

    @Schema(description = "Price of the Book.",
            example = "1", required = true)
    @Column(name = "price", nullable = false, length = 80)
    private float price;

    @Schema(description = "Title of the Book.",
            example = "1", required = true)
    @Column(name = "title", nullable = false, length = 80)
    private String title;

    public Book(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLauch_date() {
        return lauch_date;
    }

    public void setLauch_date(Date lauch_date) {
        this.lauch_date = lauch_date;
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
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getId() == book.getId() && Float.compare(book.getPrice(), getPrice()) == 0 && Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getLauch_date(), book.getLauch_date()) && Objects.equals(getTitle(), book.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getLauch_date(), getPrice(), getTitle());
    }
}
