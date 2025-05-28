package etsm.tcc.minha_tipagem.controllers;

import etsm.tcc.minha_tipagem.dtos.requests.ConsultaRequest;
import etsm.tcc.minha_tipagem.dtos.requests.ExameRequest;
import etsm.tcc.minha_tipagem.dtos.requests.ResponsavelRequest;
import etsm.tcc.minha_tipagem.dtos.responses.ProtocoloConsultaResponse;
import etsm.tcc.minha_tipagem.entities.Paciente;
import etsm.tcc.minha_tipagem.entities.Responsaveis;
import etsm.tcc.minha_tipagem.enums.Parentesco;
import etsm.tcc.minha_tipagem.projections.ConsultaProjection;
import etsm.tcc.minha_tipagem.services.PacienteService;
import etsm.tcc.minha_tipagem.services.ResponsaveisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/responsaveis")
public class ResponsaveisController {

    @Autowired
    private ResponsaveisService responsaveisService;

    @Autowired
    private PacienteService pacienteService;

    /*
     * Cadastro completo da família (responsáveis + criança + protocolo)
     * Recebe o request DTO com todos os dados
     * Retorna o Mapa com os responsáveis cadastrados
     */
    @PostMapping
    public ResponseEntity<Map<Parentesco, Responsaveis>> cadastrarFamilia(
            @RequestBody ConsultaRequest request) {
        Map<Parentesco, Responsaveis> responsaveis = responsaveisService.cadastrarFamilia(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsaveis);
    }

    // Esse endpoint é para listar os responsáveis cadastrados
    @GetMapping
    public Page<Responsaveis> listarResponsaveis(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return responsaveisService.listarResponsaveisPaginados(pagina, tamanho);
    }

    // Esse endpoint é para consultar dados completos, de responsáveis, crianças e protocolos
    @GetMapping("/consulta-completa")
    public Page<ConsultaProjection> consultarDadosCompletos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return responsaveisService.consultarDadosCompletos(pagina, tamanho);
    }

    @PostMapping("/calcular-tipagem")
    public ResponseEntity<Map<String, List<String>>> calcularTipagemFilho(
            @RequestBody List<ResponsavelRequest> responsaveis) {

        String tipagemMae = responsaveis.get(0).tipagemSanguinea();
        String tipagemPai = responsaveis.get(1).tipagemSanguinea();

        Map<String, List<String>> resultado = responsaveisService.calcularTipagemFilho(tipagemMae, tipagemPai);

        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/salvar-exame")
    public ResponseEntity<ProtocoloConsultaResponse> salvarExame(@RequestBody ConsultaRequest request) {
        ProtocoloConsultaResponse response = responsaveisService.salvarExame(request);
        return ResponseEntity.ok(response);
    }
}