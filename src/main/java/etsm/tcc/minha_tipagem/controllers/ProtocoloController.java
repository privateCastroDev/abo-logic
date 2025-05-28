package etsm.tcc.minha_tipagem.controllers;

import etsm.tcc.minha_tipagem.dtos.responses.ProtocoloConsultaResponse;
import etsm.tcc.minha_tipagem.dtos.responses.ProtocoloResponse;
import etsm.tcc.minha_tipagem.dtos.responses.ResponsaveisResponse;
import etsm.tcc.minha_tipagem.entities.Protocolo;
import etsm.tcc.minha_tipagem.services.ProtocoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/protocolos")
public class ProtocoloController {

    @Autowired
    private ProtocoloService protocoloService;

    // Criar ou atualizar um protocolo
    @PostMapping("/salvar")
    public ResponseEntity<Protocolo> salvarProtocolo(@RequestBody Protocolo protocolo) {
        Protocolo salvoProtocolo = protocoloService.salvarProtocolo(protocolo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvoProtocolo);
    }

    // Listar todos os protocolos
    @GetMapping("/listar")
    public Page<Protocolo> getAllProtocolos (
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return protocoloService.getAllProtocolos(pagina, tamanho);
    }

    @GetMapping("/buscar")
    public ResponseEntity<ProtocoloConsultaResponse> buscarPorProtocolo(@RequestParam String protocolo) {
        ProtocoloConsultaResponse response = protocoloService.buscarPorProtocolo(protocolo);
        return ResponseEntity.ok(response);
    }
}
