package ru.otus.web.flux.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthorDto {

    private String id;

    private String authorName;
}
