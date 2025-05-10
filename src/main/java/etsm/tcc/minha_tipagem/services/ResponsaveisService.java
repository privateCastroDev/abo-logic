package etsm.tcc.minha_tipagem.services;

import etsm.tcc.minha_tipagem.entities.Responsaveis;
import etsm.tcc.minha_tipagem.entities.Protocolo;
import etsm.tcc.minha_tipagem.entities.Paciente;
import etsm.tcc.minha_tipagem.enums.Parentesco;
import etsm.tcc.minha_tipagem.dtos.requests.ConsultaRequest;
import etsm.tcc.minha_tipagem.dtos.requests.ResponsaveisRequest;
import etsm.tcc.minha_tipagem.projections.ConsultaProjection;
import etsm.tcc.minha_tipagem.repositories.ResponsaveisRepository;
import etsm.tcc.minha_tipagem.repositories.ProtocoloRepository;
import etsm.tcc.minha_tipagem.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ResponsaveisService {

    @Autowired
    private ResponsaveisRepository responsaveisRepository;

    @Autowired
    private ProtocoloRepository protocoloRepository;

    @Autowired
    private PacienteRepository pacienteRepository;


    public Map<Parentesco, Responsaveis> cadastrarFamilia(ConsultaRequest request) {
        Map<Parentesco, Responsaveis> responsaveis = new HashMap<>();

        for (ResponsaveisRequest responsavelRequest : request.responsaveis()) {
            Responsaveis responsavel = new Responsaveis();
            responsavel.setNome(responsavelRequest.nome());
            responsavel.setTipagemSanguinea(responsavelRequest.tipagemSanguinea());
            responsavel.setParentesco(responsavelRequest.parentesco());
            responsaveis.put(responsavelRequest.parentesco(), responsaveisRepository.save(responsavel));
        }

        if (!responsaveis.isEmpty()) {
            criarVinculos(request, responsaveis);
        }

        return responsaveis;
    }

    private void criarVinculos(ConsultaRequest request, Map<Parentesco, Responsaveis> responsaveis) {
        Paciente paciente = criarESalvarPaciente(request, responsaveis);
        criarESalvarProtocolo(paciente, responsaveis);
    }

    private Paciente criarESalvarPaciente(ConsultaRequest request, Map<Parentesco, Responsaveis> responsaveis) {
        Paciente paciente = new Paciente();
        paciente.setNomeCrianca(request.nomeCrianca());
        atribuirResponsaveisAoPaciente(paciente, responsaveis);
        return pacienteRepository.save(paciente);
    }

    private Protocolo criarESalvarProtocolo(Paciente paciente, Map<Parentesco, Responsaveis> responsaveis) {
        Protocolo protocolo = new Protocolo();
        protocolo.setNumeroProtocolo(gerarNumeroProtocolo());
        protocolo.setPaciente(paciente);
        atribuirResponsaveisAoProtocolo(protocolo, responsaveis);
        return protocoloRepository.save(protocolo);
    }

    private String gerarNumeroProtocolo() {
        return "PROT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private void atribuirResponsaveisAoPaciente(Paciente paciente, Map<Parentesco, Responsaveis> responsaveis) {
        Optional.ofNullable(responsaveis.get(Parentesco.MAE))
                .ifPresent(paciente::setResponsavel1);
        Optional.ofNullable(responsaveis.get(Parentesco.PAI))
                .ifPresent(paciente::setResponsavel2);
    }

    private void atribuirResponsaveisAoProtocolo(Protocolo protocolo, Map<Parentesco, Responsaveis> responsaveis) {
        Optional.ofNullable(responsaveis.get(Parentesco.MAE))
                .ifPresent(protocolo::setResponsavel1);
        Optional.ofNullable(responsaveis.get(Parentesco.PAI))
                .ifPresent(protocolo::setResponsavel2);
    }

    public Page<Responsaveis> listarResponsaveisPaginados(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return responsaveisRepository.findAll(pageable);
    }

    public Page<ConsultaProjection> consultarDadosCompletos(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return responsaveisRepository.consultarDados(pageable);
    }
}
