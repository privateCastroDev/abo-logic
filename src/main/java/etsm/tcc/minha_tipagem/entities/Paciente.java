package etsm.tcc.minha_tipagem.entities;

import etsm.tcc.minha_tipagem.enums.Parentesco;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity(name = "paciente")
@Data
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 150)
    @Column(name = "nome_crianca") // Nome da criança
    private String nomeCrianca;

    @ManyToOne
    @JoinColumn(name = "responsavel1_id") // FK para o responsável 1
    private Responsaveis responsavel1;

    @ManyToOne
    @JoinColumn(name = "responsavel2_id") // FK para o responsável 2
    private Responsaveis responsavel2;
}
