package ru.geekbrains.mini.market.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.mini.market.entites.Books;

import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(description = "Books dto in the application.")
public class BooksDto {
    @ApiModelProperty(notes = "Unique identifier of the book. No two books can have the same id.", example = "1", required = true, position = 0)
    private Long id;

    @ApiModelProperty(notes = "Title of the book.", example = "Kapitanskaya dochka", required = true, position = 1)
    private String title;

    @ApiModelProperty(notes = "Price of the book.", example = "100", required = true, position = 2)
    private int price;

    @ApiModelProperty(notes = "Author title of the book.", example = "Pushkin", required = true, position = 3)
    private String authorTitle;

    public BooksDto(Books p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
        this.authorTitle = p.getAuthors().getTitle();
    }

}
