package etsm.tcc.minha_tipagem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gestor2")

@Getter
@Setter
public class Pai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 2)
    private String tipagem;

    @Column(nullable = false, columnDefinition = "CHAR")
    private String fator_rh;

}
