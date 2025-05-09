package etsm.tcc.minha_tipagem.entities;

import etsm.tcc.minha_tipagem.enums.Parentesco;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity(name = "responsaveis")
@Data // Getter, Setter e Constructor
public class Responsaveis {

    @OneToMany(mappedBy = "responsavel1", cascade = CascadeType.ALL)
    private List<Protocolo> protocolosResponsavel1;

    @OneToMany(mappedBy = "responsavel2", cascade = CascadeType.ALL)
    private List<Protocolo> protocolosResponsavel2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Size(max = 3)
    @Column(name = "tipagem_sanguinea", nullable = false)
    private String tipagemSanguinea;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Parentesco parentesco; // Enum para definir se o responsável é pai ou mãe

}