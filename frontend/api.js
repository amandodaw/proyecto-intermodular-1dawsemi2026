const API_BASE = 'http://localhost:8080/api';

async function getDestinos() {
    const response = await fetch(`${API_BASE}/destino`);
    if (!response.ok) throw new Error('Error al obtener destinos');
    return await response.json();
}

async function getDestinoById(id) {
    const response = await fetch(`${API_BASE}/destino/${id}`);
    if (!response.ok) throw new Error('Error al obtener destino');
    return await response.json();
}

async function crearUsuario(usuario) {
    const response = await fetch(`${API_BASE}/usuario`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(usuario)
    });
    if (!response.ok) throw new Error('Error al crear usuario');
    return await response.json();
}

async function getReservasPorUsuario(idUsuario) {
    const response = await fetch(`${API_BASE}/reserva/usuario/${idUsuario}`);
    if (!response.ok) throw new Error('Error al obtener reservas');
    return await response.json();
}

async function crearReserva(reserva) {
    const response = await fetch(`${API_BASE}/reserva`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(reserva)
    });
    if (!response.ok) throw new Error('Error al crear reserva');
    return await response.json();
}