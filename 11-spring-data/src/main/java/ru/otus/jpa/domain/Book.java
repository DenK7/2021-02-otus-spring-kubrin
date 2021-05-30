package ru.otus.jpa.domain;

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

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 5)
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

}
