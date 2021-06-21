package ru.otus.spring.batch.domain.h2;

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
public class H2Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BOOK_NAME")
    private String bookName;

    @Column(name = "MONGO_ID")
    private String mongoId;

    @ManyToMany(fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    private List<H2Author> h2Authors;

    @ManyToOne(fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    @JoinColumn(name = "GENRE_ID")
    private H2Genre h2Genre;

}
