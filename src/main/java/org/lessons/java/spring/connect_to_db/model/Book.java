package org.lessons.java.spring.connect_to_db.model;


import java.time.LocalDate;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    //prevedo l'aggiunta di una relazione tra UN LIBRO e 0,1 o PIU prestiti
    @OneToMany(mappedBy = "book")
    // @JsonManagedReference
    private List<Borrowing> borrowings;

    @ManyToMany()
    @JoinTable(
        name="book_category",
        joinColumns = @JoinColumn(name= "book_id"),
        inverseJoinColumns = @JoinColumn(name= "category_id")
    )
    // @JsonManagedReference
    private List<Category> categories;

    @Size(min = 13 , max =13 , message = "ISBN must be made of 13 character")
    @Column(name= "isbn_code" , length = 13 , nullable = false)
    @NotBlank
    private String isbn;

    @NotBlank(message = "The title can't be null, empty or blank")
    private String title;

    @NotBlank(message = "The author can't be null, empty or blank")
    private String author;

    @NotBlank(message = "The publisher can't be null, empty or blank")
    private String publisher;

    @Lob
    private String synopsis;

    @NotNull
    @PastOrPresent(message = "La data non può essere nel futuro")
    private LocalDate publicationDate;

    @NotNull
    @Min( value =0 , message = "The number of copies must be positive" )
    private Integer numberOfCopies;

    

    public List<Borrowing> getBorrowings() {
        return this.borrowings;
    }

    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }


    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }



    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSynopsis() {
        return this.synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public LocalDate getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getNumberOfCopies() {
        return this.numberOfCopies;
    }

    public void setNumberOfCopies(Integer numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }
    


    @Override
    public String toString(){

        return String.format("%s di %s, pubblicato da %s",title,author, publisher);
    }

}
