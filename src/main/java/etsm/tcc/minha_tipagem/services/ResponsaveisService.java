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

import java.util.*;

@Service
// https://www.geeksforgeeks.org/spring-boot-transaction-management-using-transactional-annotation/
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

    public Map<String, List<String>> calcularTipagemFilho(String tipagemMae, String tipagemPai) {

        // Converter para uppercase e remover espaços
        tipagemMae = tipagemMae.toUpperCase().trim();
        tipagemPai = tipagemPai.toUpperCase().trim();

        // Extrair grupo sanguíneo (A, B, AB, O) e Rh (+/-)
        String grupoMae = tipagemMae.substring(0, tipagemMae.length() - 1);
        String rhMae = tipagemMae.substring(tipagemMae.length() - 1);

        String grupoPai = tipagemPai.substring(0, tipagemPai.length() - 1);
        String rhPai = tipagemPai.substring(tipagemPai.length() - 1);

        // Calcular possível grupo sanguíneo do filho
        List<String> grupoFilho = calcularGrupoSanguineo(grupoMae, grupoPai);

        // Calcular possível fator RH do filho
        List<String> rhFilho = calcularFatorRh(rhMae, rhPai);

        // Combina os dois resultados em uma nova lista
        Map<String, List<String>> newTipagemFilho = new HashMap<>();
        newTipagemFilho.put("tipagemFilho", grupoFilho);
        newTipagemFilho.put("rh", rhFilho);

        return newTipagemFilho;
    }

    private List<String> calcularGrupoSanguineo(String grupoMae, String grupoPai) {
        // Implementação das leis de Mendel para grupos sanguíneos
        if (grupoMae.equals("O") && grupoPai.equals("O")) {
            return List.of("100% O");
        } else if (grupoMae.equals("A") && grupoPai.equals("A")) {
            return List.of("75% A", "25% O");
        } else if (grupoMae.equals("B") && grupoPai.equals("B")) {
            return List.of("75% B", "25% O");
        } else if ((grupoMae.equals("A") && grupoPai.equals("B")) ||
                (grupoMae.equals("B") && grupoPai.equals("A"))) {
            return List.of("25% A", "25% B", "25% AB", "25% O");
        } else if ((grupoMae.equals("A") && grupoPai.equals("O")) ||
                (grupoMae.equals("O") && grupoPai.equals("A"))) {
            return List.of("50% A", "50% O");
        } else if ((grupoMae.equals("B") && grupoPai.equals("O")) ||
                (grupoMae.equals("O") && grupoPai.equals("B"))) {
            return List.of("50% B", "50% O");
        } else if (grupoMae.equals("AB") && grupoPai.equals("AB")) {
            return List.of("50% AB", "25% A", "25% B");
        } else if ((grupoMae.equals("AB") && grupoPai.equals("O")) ||
                (grupoMae.equals("O") && grupoPai.equals("AB"))) {
            return List.of("50% A", "50% B");
        } else if ((grupoMae.equals("AB") && grupoPai.equals("A")) ||
                (grupoMae.equals("A") && grupoPai.equals("AB"))) {
            return List.of("50% A", "25% B", "25% AB");
        } else if ((grupoMae.equals("AB") && grupoPai.equals("B")) ||
                (grupoMae.equals("B") && grupoPai.equals("AB"))) {
            return List.of("50% B", "25% A", "25% AB");
        } else {
            return List.of("Erro, tipagem não encontrada!");
        }
    }

    private List<String> calcularFatorRh(String rhMae, String rhPai) {
        // Rh+ é dominante, Rh- é recessivo
        if (rhMae.equals("-") && rhPai.equals("-")) {
            return List.of("-");
        } else if (rhMae.equals("+") && rhPai.equals("-") ||
                   rhMae.equals("-") && rhPai.equals("+")) {
            return List.of("50% +", "50% -");
        }
        return List.of("75% +", "25% -");
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
