package etsm.tcc.minha_tipagem.dtos.requests;

public record  HospitalRequest(
        String nome,
        String cnpj,
        String endereco,
        String telefone,
        String email,
        String senha
) {
}
