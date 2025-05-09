package etsm.tcc.minha_tipagem.repositories;

import etsm.tcc.minha_tipagem.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
