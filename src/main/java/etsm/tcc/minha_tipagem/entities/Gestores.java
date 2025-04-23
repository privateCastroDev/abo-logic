package etsm.tcc.minha_tipagem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Gestores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private UUID protocolo;




    @ManyToOne
    @JoinColumn(name = "mae_id", referencedColumnName = "id", nullable = false)
    private Mae mae;

    @ManyToOne
    @JoinColumn(name = "pai_id", referencedColumnName = "id", nullable = false)
    private Pai pai;

}
