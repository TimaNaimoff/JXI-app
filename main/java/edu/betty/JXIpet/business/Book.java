package edu.betty.JXIpet.business;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="book_id")
    @Getter
    @Setter
    private Integer id;
    @Column(name="book_name")
    @NotEmpty(message="Book's name cannot be empty!")
    @Size(min=2,max=50,message="Book's name's interval is from 2 to 50!")
    @Getter
    @Setter
    private String title;
    @Column(name="book_author")
    @NotEmpty(message="Author's name cannot be empty!")
    @Size(min=2,max=50,message="Author's name's interval is from 2 to 50!")
    @Getter
    @Setter
    private String authorName;
    @Column(name="book_year")
    @Min(value=1700,message="Year should be > 1700")
    @Getter
    @Setter
    private Integer year;
    @ManyToOne
    @JoinColumn(name="person_id",referencedColumnName = "person_id")
    @Getter
    @Setter
    private Person person;
    public Book(){

    }
    public Book(String title,String authorName,Integer year){
        this.title=title;
        this.authorName=authorName;
        this.year=year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", year=" + year +
                '}';
    }
}
