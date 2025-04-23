// pagination.js
import { getTodosHospitais } from './api.js';

let paginaAtual = 0;
const tamanhoPagina = 10;
let totalPaginas = 0;

async function carregarHospitais() {
    const data = await getTodosHospitais(paginaAtual, tamanhoPagina);
    const tbody = document.getElementById('hospital-body');
    tbody.innerHTML = '';

    data.content.forEach(produto => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${produto.id}</td>
            <td>${produto.nome}</td>
            <td>${produto.cnpj}</td>
            <td>${produto.endereco}</td>
            <td>${produto.telefone}</td>
            <td>${produto.email}</td>
            <td>${produto.senha}</td>
        `;
        tbody.appendChild(row);
    });

    totalPaginas = data.totalPages;
    document.getElementById('infoPagina').innerText = `Página ${paginaAtual + 1} de ${totalPaginas}`;

    document.getElementById('btnAnterior').disabled = paginaAtual === 0;
    document.getElementById('btnProxima').disabled = paginaAtual >= totalPaginas - 1;
}

function proxima() {
    if (paginaAtual < totalPaginas - 1) {
        paginaAtual++;
        carregarHospitais();
    }
}

function anterior() {
    if (paginaAtual > 0) {
        paginaAtual--;
        carregarHospitais();
    }
}

// Carrega a primeira página ao iniciar
carregarHospitais();
