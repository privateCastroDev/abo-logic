package etsm.tcc.minha_tipagem.dtos.responses;

import etsm.tcc.minha_tipagem.enums.Parentesco;

public record ResponsaveisResponse (
        Long id,
        String nome,
        String tipagemSanguinea,
        Parentesco parentesco
) {
}
