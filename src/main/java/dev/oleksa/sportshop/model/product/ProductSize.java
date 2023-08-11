package dev.oleksa.sportshop.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity(name = "size")
public class ProductSize {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;
    @NotNull
    private String size;
}
