package etsm.tcc.minha_tipagem.dtos.requests;

import java.util.List;

public record ConsultaRequest(
        String nomeCrianca,
        List<ResponsavelRequest> responsaveis
) {}
