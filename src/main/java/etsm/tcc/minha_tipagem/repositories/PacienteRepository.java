package etsm.tcc.minha_tipagem.repositories;

import etsm.tcc.minha_tipagem.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByNomeCrianca(String nomeCrianca);
}
