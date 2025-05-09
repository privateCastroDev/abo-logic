package etsm.tcc.minha_tipagem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity(name = "hospital") // Definindo nome da table
@Data // Getter, Setter e Constructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 15)
    private String cnpj;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String endereco;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(nullable = false, length = 254)
    @Email
    private String email;

    @Column(nullable = false, length = 60)
    private String senha;
}
