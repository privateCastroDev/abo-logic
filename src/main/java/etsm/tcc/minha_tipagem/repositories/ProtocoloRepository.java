package etsm.tcc.minha_tipagem.repositories;

import etsm.tcc.minha_tipagem.entities.Paciente;
import etsm.tcc.minha_tipagem.entities.Protocolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProtocoloRepository extends JpaRepository<Protocolo, Long> {

    Optional<Protocolo> findByNumeroProtocolo(String numeroProtocolo);
    Optional<Protocolo> findTopByPacienteOrderByIdDesc(Paciente paciente);
}

