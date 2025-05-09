package etsm.tcc.minha_tipagem.services;

import etsm.tcc.minha_tipagem.entities.Protocolo;
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
    public Page<Protocolo> getAllProtocolos (int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return protocoloRepository.findAll(pageable);
    }
}
