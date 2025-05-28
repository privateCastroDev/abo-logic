package etsm.tcc.minha_tipagem.dtos.requests;

public record ExameRequest(
        String nomeCrianca,
        String tipagemSanguinea,
        String fatorRh,
        Long responsavel1Id,
        Long responsavel2Id
) {}

