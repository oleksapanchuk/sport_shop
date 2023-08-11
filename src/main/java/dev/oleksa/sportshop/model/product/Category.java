package dev.oleksa.sportshop.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    @NotNull
    private Category category;

    @NotNull
    private String nameUa;

    @NotNull
    private String nameEng;

    @Column(length = 300)
    @NotNull
    private String imageUrl;
}
