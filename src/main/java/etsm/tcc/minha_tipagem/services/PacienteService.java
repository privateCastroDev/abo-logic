package etsm.tcc.minha_tipagem.services;

import etsm.tcc.minha_tipagem.entities.Paciente;
import etsm.tcc.minha_tipagem.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente criarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    /* Lógica de Criação do Paciente (Regra de Negocio), passa uma lista de pacientes em uma pagina
    * passamos o tamanho e quantas paginas podem ter, criamos um obj da classe Pageable, dps um request
    * recuperando os dados da pagina e tamanho, em seguida, retorna todos os pacientes presentes no bd */
    public Page<Paciente> getAllPacientes(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return pacienteRepository.findAll(pageable);
    }
}
