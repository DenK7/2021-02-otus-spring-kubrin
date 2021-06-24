package ru.otus.spring.mvc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(description = "Жанр")
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreDto {
    @ApiModelProperty(value = "уникальный идентификатор")
    private String id;

    @ApiModelProperty(value = "наименование жанра")
    private String genreName;
}
