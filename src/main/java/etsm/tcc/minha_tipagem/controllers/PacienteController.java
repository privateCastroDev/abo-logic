package etsm.tcc.minha_tipagem.controllers;

import etsm.tcc.minha_tipagem.entities.Paciente;
import etsm.tcc.minha_tipagem.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/criar")
    public Paciente criarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.criarPaciente(paciente);
    }

    @GetMapping("/listarTodos")
    public Page<Paciente> getAllPacientes (
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return pacienteService.getAllPacientes(pagina, tamanho);
    }
}
