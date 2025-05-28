package etsm.tcc.minha_tipagem.services;

import etsm.tcc.minha_tipagem.dtos.responses.ProtocoloConsultaResponse;
import etsm.tcc.minha_tipagem.entities.Paciente;
import etsm.tcc.minha_tipagem.entities.Protocolo;
import etsm.tcc.minha_tipagem.entities.Responsaveis;
import etsm.tcc.minha_tipagem.repositories.ProtocoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProtocoloService {

    @Autowired
    private ProtocoloRepository protocoloRepository;

    // Criar um protocolo
    public Protocolo salvarProtocolo(Protocolo protocolo) {
        return protocoloRepository.save(protocolo);
    }

    // Listar todos os protocolos (paginados)
    public Page<Protocolo> getAllProtocolos(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return protocoloRepository.findAll(pageable);
    }

    public ProtocoloConsultaResponse buscarPorProtocolo(String numeroProtocolo) {
        Protocolo protocolo = protocoloRepository.findByNumeroProtocolo(numeroProtocolo)
                .orElseThrow(() -> new RuntimeException("Protocolo não encontrado"));

        Paciente paciente = protocolo.getPaciente();
        Responsaveis mae = paciente.getResponsavel1();
        Responsaveis pai = paciente.getResponsavel2();

        return new ProtocoloConsultaResponse(
                protocolo.getNumeroProtocolo(),
                paciente.getNomeCrianca(),
                paciente.getTipagemSanguineaFilho(),
                paciente.getFatorRhFilho(),
                mae != null ? mae.getNome() : null,
                mae != null ? mae.getTipagemSanguinea() : null,
                pai != null ? pai.getNome() : null,
                pai != null ? pai.getTipagemSanguinea() : null
        );
    }
}
