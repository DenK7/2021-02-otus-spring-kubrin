package ru.otus.spring.mvc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel(description = "Книга")
@Builder
@Getter
@Setter
public class BookDto {

    @ApiModelProperty(value = "уникальный идентификатор")
    private String id;

    @ApiModelProperty(value = "Наименование книги")
    private String bookName;

    @ApiModelProperty(value = "Список авторов")
    private List<AuthorDto> authors;

    @ApiModelProperty(value = "Жанр")
    private GenreDto genre;
}
