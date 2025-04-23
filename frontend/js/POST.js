// cadastro.js
import { criarHospital } from './api.js';

document.getElementById('formulario').addEventListener('submit', async (event) => {
    event.preventDefault();

    const hospital = {
        nome: document.getElementById('nome').value,
        cnpj: document.getElementById('cnpj').value,
        endereco: document.getElementById('endereco').value,
        telefone: document.getElementById('telefone').value,
        email: document.getElementById('email').value,
        senha: document.getElementById('senha').value
    };

    const novoHospital = await criarHospital(hospital);
    alert(`Hospital Registrado: ${novoHospital.nome}`);

    // Limpa o formulário após o cadastro
    document.getElementById('formulario').reset();
});
