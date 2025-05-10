package etsm.tcc.minha_tipagem.repositories;

import etsm.tcc.minha_tipagem.entities.Responsaveis;
import etsm.tcc.minha_tipagem.projections.ConsultaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsaveisRepository extends JpaRepository<Responsaveis, Long> {

    @Query(value = """
    SELECT 
        r.id AS responsavelId, r.nome AS nomeResponsavel, r.tipagem_sanguinea AS tipagem, prot.numero_protocolo AS numeroProtocolo,
        p.nome_crianca AS nomeCrianca, r.parentesco AS parentesco
    FROM responsaveis r
    LEFT JOIN paciente p ON (p.responsavel1_id = r.id OR p.responsavel2_id = r.id)
    LEFT JOIN protocolo prot ON prot.paciente_id = p.id
    WHERE r.parentesco IN ('MAE', 'PAI')
    """,
            countQuery = "SELECT COUNT(*) FROM responsaveis r WHERE r.parentesco IN ('MAE', 'PAI')",
            nativeQuery = true)
    Page<ConsultaProjection> consultarDados(Pageable pageable);
}