package ru.geekbrains.mini.market.dto;

import ch.qos.logback.core.pattern.util.AlmostAsIsEscapeUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.mini.market.entites.Authors;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class AuthorsDto {
    private Long id;
    private String title;
    private List<BooksDto> books;

    public AuthorsDto(Authors c) {
        this.id = c.getId();
        this.title = c.getTitle();
        this.books = c.getBooks().stream().map(BooksDto::new).collect(Collectors.toList());
    }
}
