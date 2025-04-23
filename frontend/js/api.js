// api.js
const API_URL = 'http://localhost:8080/hospital';

async function getTodosHospitais(pagina = 0, tamanho = 10) {
    const response = await fetch(`${API_URL}/listarTodos?page=${pagina}&size=${tamanho}`);
    return await response.json();
}

async function criarHospital(hospital) {
    const response = await fetch(`${API_URL}/criar`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(hospital),
    });
    console.log(hospital);
    return await response.json();
    
}
export { getTodosHospitais, criarHospital };
