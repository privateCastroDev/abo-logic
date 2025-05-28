package etsm.tcc.minha_tipagem.dtos.responses;

import jakarta.validation.constraints.Size;

public record ProtocoloConsultaResponse(
        String numeroProtocolo,
        String nomeCrianca,
        String tipagemSanguineaFilho,
        String fatorRhFilho,
        String nomeMae,
        String tipagemMae,
        String nomePai,
        String tipagemPai
) {
}