package ru.otus.spring.batch.domain.h2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "AUTHOR")
@NoArgsConstructor
@AllArgsConstructor
public class H2Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AUTHOR_NAME")
    private String authorName;

    @ManyToMany(mappedBy = "h2Authors", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @BatchSize(size = 5)
    private List<H2Book> h2Books;

    @Override
    public String toString() {
        return "id = " + id + ", authorName = " + authorName ;
    }
}
