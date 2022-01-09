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

    @Schema(description = "Launch date of the book.",
            example = "1", required = true)
    @Column(name = "launchDate", nullable = false, length = 6)
    private Date launch_date;

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

    public Date getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(Date launch_date) {
        this.launch_date = launch_date;
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
        return getId() == book.getId() && Float.compare(book.getPrice(), getPrice()) == 0 && Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getLaunch_date(), book.getLaunch_date()) && Objects.equals(getTitle(), book.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getLaunch_date(), getPrice(), getTitle());
    }
}
