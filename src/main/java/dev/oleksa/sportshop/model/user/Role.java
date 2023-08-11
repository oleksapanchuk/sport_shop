package dev.oleksa.sportshop.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(length = 20)
    @NotNull
    private String nameUa;

    @Column(length = 20)
    @NotNull
    private String nameEng;

}
