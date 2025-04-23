package etsm.tcc.minha_tipagem.controllers;

import etsm.tcc.minha_tipagem.entities.Hospital;
import etsm.tcc.minha_tipagem.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    /* Metodo POST (Criação de um cadastro de um hospital */
    @PostMapping("/criar")
    public Hospital criarHospital(@RequestBody Hospital hospital) {

        return hospitalService.criarHospital(hospital);
    }

    /* Metodo GET (Recuperar todos os hospitais cadastrados */
    @GetMapping("/listarTodos")
    public Page<Hospital> getTodosHospitais(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return hospitalService.getTodosHospitais(pagina, tamanho);
    }


}
