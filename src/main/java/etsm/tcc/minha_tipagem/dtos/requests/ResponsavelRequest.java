package etsm.tcc.minha_tipagem.dtos.requests;

import etsm.tcc.minha_tipagem.enums.Parentesco;

public record ResponsavelRequest(
        String nome,
        String tipagemSanguinea,
        Parentesco parentesco
) {}
