package etsm.tcc.minha_tipagem.projections;

import etsm.tcc.minha_tipagem.enums.Parentesco;

public interface ConsultaProjection {
    Long getResponsavelId();
    String getNomeResponsavel();
    String getTipagem();
    String getNumeroProtocolo();
    String getNomeCrianca();
    Parentesco getParentesco();

    // Metodo padrão para obter a descrição do parentesco
    default String getParentescoDescricao() {
        return getParentesco() != null ? getParentesco().getDescricao() : null;
    }
}