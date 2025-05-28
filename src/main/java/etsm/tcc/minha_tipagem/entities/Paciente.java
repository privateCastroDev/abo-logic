    package etsm.tcc.minha_tipagem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import etsm.tcc.minha_tipagem.enums.Parentesco;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity(name = "paciente")
@Data
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 150)
    @Column(name = "nome_crianca") // Nome da criança
    private String nomeCrianca;

    @Column(name = "tipagem_sanguinea", columnDefinition = "TEXT") // Tipagem sanguínea da criança
    private String tipagemSanguineaFilho;

    @Column(name = "fator-rh", columnDefinition = "TEXT") // Fator RH da criança
    private String fatorRhFilho;

    @ManyToOne
    @JsonManagedReference("paciente-responsavel1")
    @JoinColumn(name = "responsavel1_id") // FK para o responsável 1
    private Responsaveis responsavel1;

    @ManyToOne
    @JsonManagedReference("paciente-responsavel2")
    @JoinColumn(name = "responsavel2_id") // FK para o responsável 2
    private Responsaveis responsavel2;

    @OneToMany(mappedBy = "paciente", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference("protocolo-paciente")
    private List<Protocolo> protocolos; // Lista de protocolos associados ao paciente

}
