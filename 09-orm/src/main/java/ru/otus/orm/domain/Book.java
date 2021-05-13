package ru.otus.orm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "BOOK")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "BOOK_NAME")
    private String bookName;

    @OneToMany(targetEntity = Comment.class, mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @BatchSize(size = 5)
    @JoinTable(name = "BOOK_AUTHOR", joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

}
