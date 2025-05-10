package etsm.tcc.minha_tipagem.dtos.responses;

public record PacienteResponse(
        Long id,
        String nomeCrianca,
        ResponsaveisResponse responsavel1,
        ResponsaveisResponse responsavel2
) {
}