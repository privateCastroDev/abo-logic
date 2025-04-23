package etsm.tcc.minha_tipagem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "gestor1")

@Getter
@Setter
public class Mae {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mae_id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 2)
    private String tipagem;

    @Column(nullable = false, columnDefinition = "CHAR")
    private String fator_rh;

    @Column(nullable = true)
    private LocalDate ultima_mens;

}
