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
@Table(name = "GENRE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class H2Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GENRE_NAME")
    private String genreName;

    @Column(name = "MONGO_ID")
    private String mongoId;

    @OneToMany(mappedBy = "h2Genre", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 5)
    private List<H2Book> h2Books;

    @Override
    public String toString() {
        return "id = " + id + ", genreName = " + genreName;
    }
}
