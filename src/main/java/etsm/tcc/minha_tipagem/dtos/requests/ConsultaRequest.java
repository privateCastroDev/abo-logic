package etsm.tcc.minha_tipagem.dtos.requests;

import etsm.tcc.minha_tipagem.enums.Parentesco;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public record ConsultaRequest (
   // Recebe o array do json dos responsaveis, por ex
   // [
   // { nome: ...,
   // tipagemSanguinea: ...,
   // e etc }
   // ]
   List <ResponsaveisRequest> responsaveis,

   String nomeCrianca
) {
}