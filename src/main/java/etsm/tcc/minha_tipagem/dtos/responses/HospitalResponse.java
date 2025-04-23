package etsm.tcc.minha_tipagem.dtos.responses;

import etsm.tcc.minha_tipagem.enums.Status;

import java.util.UUID;

public record HospitalResponse (
        Long id,
        String criadoPeloUsuario,
        String criadoDataEHora,
        String editadoPeloUsuario,
        String editadoDataEHora,
        Status status
){

}
