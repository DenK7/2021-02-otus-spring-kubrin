package ru.otus.orm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "GENRE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "GENRE_NAME")
    private String genreName;

    @Override
    public String toString() {
        return "id = " + id + ", genreName = " + genreName;
    }
}
