package etsm.tcc.minha_tipagem.services;

import etsm.tcc.minha_tipagem.entities.Paciente;
import etsm.tcc.minha_tipagem.entities.Protocolo;
import etsm.tcc.minha_tipagem.entities.Responsaveis;
import etsm.tcc.minha_tipagem.repositories.PacienteRepository;
import etsm.tcc.minha_tipagem.repositories.ProtocoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProtocoloRepository protocoloRepository;

    @Autowired
    private ResponsaveisService responsaveisService;

    public Paciente criarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Page<Paciente> getAllPacientes(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return pacienteRepository.findAll(pageable);
    }


}
