package ru.geekbrains.mini.market.entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Data
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Authors authors;
}
