const API_BASE = "http://localhost:8080";

async function getDestinos() {
    const response = await fetch(`${API_BASE}/api/destino`);
    return await response.json();
}

async function getDestinoById(id) {
    const response = await fetch(`${API_BASE}/api/destino/${id}`);
    return await response.json();
}

async function createUsuario(data) {
    const response = await fetch(`${API_BASE}/api/usuario`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });
    return response;
}