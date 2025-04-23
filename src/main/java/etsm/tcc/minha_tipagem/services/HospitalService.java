package etsm.tcc.minha_tipagem.services;

import etsm.tcc.minha_tipagem.entities.Hospital;
import etsm.tcc.minha_tipagem.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    /* Lógica de Criação do Hospital (Regra de Negocio) */
    public Hospital criarHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public Page<Hospital> getTodosHospitais(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return hospitalRepository.findAll(pageable);
    }

}
