package etsm.tcc.minha_tipagem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "protocolo")
@Data
public class Protocolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_protocolo", nullable = false, unique = true)
    private String numeroProtocolo;

    @ManyToOne
    @JsonManagedReference("protocolo-responsavel1")
    @JoinColumn(name = "responsavel1_id", nullable = false)
    private Responsaveis responsavel1;

    @ManyToOne
    @JsonManagedReference("protocolo-responsavel2")
    @JoinColumn(name = "responsavel2_id", nullable = false)
    private Responsaveis responsavel2;  

    @ManyToOne
    @JsonBackReference("protocolo-paciente")
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
