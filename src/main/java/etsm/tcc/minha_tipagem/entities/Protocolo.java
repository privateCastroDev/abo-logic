package etsm.tcc.minha_tipagem.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "protocolo")
@Data
public class Protocolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_protocolo", nullable = false, unique = true)
    private String numeroProtocolo;

    @ManyToOne
    @JoinColumn(name = "responsavel1_id", nullable = false)
    private Responsaveis responsavel1;

    @ManyToOne
    @JoinColumn(name = "responsavel2_id", nullable = false)
    private Responsaveis responsavel2;  

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
