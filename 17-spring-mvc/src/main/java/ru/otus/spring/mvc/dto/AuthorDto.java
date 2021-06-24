package ru.otus.spring.mvc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "Автор")
@Builder
@Getter
@Setter
public class AuthorDto {

    @ApiModelProperty(value = "уникальный идентификатор")
    private String id;

    @ApiModelProperty(value = "ФИО автора")
    private String authorName;
}
