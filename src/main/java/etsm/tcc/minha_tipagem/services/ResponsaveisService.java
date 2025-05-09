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

    /*
     * Cadastro completo da família (responsáveis + criança + protocolo)
     * Recebe o request DTO com todos os dados
     * Retorna o Mapa com os responsáveis cadastrados
     */
    public Map<Parentesco, Responsaveis> cadastrarFamilia(ConsultaRequest request) {
        Map<Parentesco, Responsaveis> responsaveis = new HashMap<>();

        // Cadastra todos os responsáveis
        for (ResponsaveisRequest responsavelRequest : request.responsaveis()) {
            Responsaveis responsavel = criarResponsavel(
                    responsavelRequest.nome(),
                    responsavelRequest.tipagemSanguinea(),
                    responsavelRequest.parentesco()
            );
            responsaveis.put(responsavelRequest.parentesco(), responsavel);
        }

        // Cria paciente e protocolo (apenas se houver pelo menos 1 responsável)
        if (!responsaveis.isEmpty()) {
            criarVinculos(request, responsaveis);
        }

        return responsaveis;
    }

    private Responsaveis criarResponsavel(String nome, String tipagem, Parentesco parentesco) {
        Responsaveis responsavel = new Responsaveis();
        responsavel.setNome(nome);
        responsavel.setTipagemSanguinea(tipagem);
        responsavel.setParentesco(parentesco);
        return responsaveisRepository.save(responsavel);
    }

    private void criarVinculos(ConsultaRequest request, Map<Parentesco, Responsaveis> responsaveis) {
        // Cria o paciente
        Paciente paciente = new Paciente();
        paciente.setNomeCrianca(request.nomeCrianca());

        // Define os responsáveis
        if (responsaveis.containsKey(Parentesco.MAE)) {
            paciente.setResponsavel1(responsaveis.get(Parentesco.MAE));
            if (responsaveis.containsKey(Parentesco.PAI)) {
                paciente.setResponsavel2(responsaveis.get(Parentesco.PAI));
            }
        }
        pacienteRepository.save(paciente);

        // Cria o protocolo com número gerado automaticamente
        Protocolo protocolo = new Protocolo();
        protocolo.setNumeroProtocolo(gerarNumeroProtocolo());
        protocolo.setPaciente(paciente);

        // Vincula os mesmos responsáveis do paciente
        if (responsaveis.containsKey(Parentesco.MAE)) {
            protocolo.setResponsavel1(responsaveis.get(Parentesco.MAE));
            if (responsaveis.containsKey(Parentesco.PAI)) {
                protocolo.setResponsavel2(responsaveis.get(Parentesco.PAI));
            }
        }
        protocoloRepository.save(protocolo);
    }

    private String gerarNumeroProtocolo() {
        return "PROT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public Page<Responsaveis> getAllResponsaveis(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return responsaveisRepository.findAll(pageable);
    }

    public Page<ConsultaProjection> consultaCompleta(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return responsaveisRepository.consultarDados(pageable);
    }
}