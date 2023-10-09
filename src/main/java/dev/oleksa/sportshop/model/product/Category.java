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
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category category;

    @NotNull
    @Column(name = "name_ua", nullable = false, length = 100)
    private String nameUa;

    @NotNull
    @Column(name = "name_eng", nullable = false, length = 100)
    private String nameEng;

    @NotNull
    @Column(name = "image_url", nullable = false, length = 1000)
    private String imageUrl;
}
