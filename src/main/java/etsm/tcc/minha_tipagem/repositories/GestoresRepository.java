package etsm.tcc.minha_tipagem.repositories;

import etsm.tcc.minha_tipagem.dtos.querys.QueryGestoresDTO;
import etsm.tcc.minha_tipagem.entities.Gestores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GestoresRepository extends JpaRepository<Gestores, Long> {
    @Query("SELECT new etsm.tcc.minha_tipagem.dto.querys.QueryGestoresDTO(" +
            "g1.nome, g2.nome, ge.protocolo, CONCAT(ge.mae.id, '-', ge.pai.id)) " +
            "FROM Gestores ge " +
            "JOIN ge.mae g1 " +
            "JOIN ge.pai g2")
    List<QueryGestoresDTO> ConsultarGestores();
}

