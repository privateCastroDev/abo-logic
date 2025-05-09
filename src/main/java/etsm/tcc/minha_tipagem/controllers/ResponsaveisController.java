package etsm.tcc.minha_tipagem.controllers;

import etsm.tcc.minha_tipagem.dtos.requests.ConsultaRequest;
import etsm.tcc.minha_tipagem.entities.Responsaveis;
import etsm.tcc.minha_tipagem.enums.Parentesco;
import etsm.tcc.minha_tipagem.projections.ConsultaProjection;
import etsm.tcc.minha_tipagem.services.ResponsaveisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/responsaveis")
public class ResponsaveisController {

    @Autowired
    private ResponsaveisService responsaveisService;

    /*
     * Criação completa: responsáveis + paciente + protocolo
     * Agora retorna um Map<Parentesco, Responsaveis> em vez de Map<String, Responsaveis>
     */
    @PostMapping("/criarCompleto")
    public ResponseEntity<Map<Parentesco, Responsaveis>> cadastrarFamilia(
            @RequestBody ConsultaRequest request
    ) {
        Map<Parentesco, Responsaveis> responsaveis = responsaveisService.cadastrarFamilia(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsaveis);
    }

     // Lista todos os responsáveis (paginado)

    @GetMapping("/listar")
    public Page<Responsaveis> getAllResponsaveis(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return responsaveisService.getAllResponsaveis(pagina, tamanho);
    }

    /*
     * Consulta completa com dados relacionados (paginado)
     */
    @GetMapping("/consultarDados")
    public Page<ConsultaProjection> consultaCompleta(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return responsaveisService.consultaCompleta(pagina, tamanho);
    }
}