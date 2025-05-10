package etsm.tcc.minha_tipagem.dtos.responses;

public record ProtocoloResponse(
        Long id,
        String numeroProtocolo,
        ResponsaveisResponse responsavel1,
        ResponsaveisResponse responsavel2
) {

}
