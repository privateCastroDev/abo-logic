package etsm.tcc.minha_tipagem.repositories;

import etsm.tcc.minha_tipagem.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
