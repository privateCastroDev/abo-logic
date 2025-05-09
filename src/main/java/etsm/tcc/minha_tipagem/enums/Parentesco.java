package etsm.tcc.minha_tipagem.enums;

import lombok.Getter;

@Getter
public enum Parentesco {
    MAE("MAE"),
    PAI("PAI");

    private final String descricao;

    Parentesco(String descricao) {
        this.descricao = descricao;
    }

}
