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

async function crearDestino(destino) {
    const response = await fetch(`${API_BASE}/destino`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(destino)
    });
    if (!response.ok) throw new Error('Error al crear destino');
    return await response.json();
}

async function actualizarDestino(id, destino) {
    const response = await fetch(`${API_BASE}/destino/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(destino)
    });
    if (!response.ok) throw new Error('Error al actualizar destino');
    return await response.json();
}

async function eliminarDestino(id) {
    const response = await fetch(`${API_BASE}/destino/${id}`, {
        method: 'DELETE'
    });
    if (!response.ok) throw new Error('Error al eliminar destino');
}

async function getUsuarios() {
    const response = await fetch(`${API_BASE}/usuario`);
    if (!response.ok) throw new Error('Error al obtener usuarios');
    return await response.json();
}

async function getUsuarioById(id) {
    const response = await fetch(`${API_BASE}/usuario/${id}`);
    if (!response.ok) throw new Error('Error al obtener usuario');
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

async function getGuias() {
    const response = await fetch(`${API_BASE}/guia`);
    if (!response.ok) throw new Error('Error al obtener guías');
    return await response.json();
}

async function getGuiaById(id) {
    const response = await fetch(`${API_BASE}/guia/${id}`);
    if (!response.ok) throw new Error('Error al obtener guía');
    return await response.json();
}

async function crearGuia(guia) {
    const response = await fetch(`${API_BASE}/guia`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(guia)
    });
    if (!response.ok) throw new Error('Error al crear guía');
    return await response.json();
}

async function actualizarGuia(id, guia) {
    const response = await fetch(`${API_BASE}/guia/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(guia)
    });
    if (!response.ok) throw new Error('Error al actualizar guía');
    return await response.json();
}

async function eliminarGuia(id) {
    const response = await fetch(`${API_BASE}/guia/${id}`, {
        method: 'DELETE'
    });
    if (!response.ok) throw new Error('Error al eliminar guía');
}

async function getGuiasPorDestino(idDestino) {
    const response = await fetch(`${API_BASE}/guia/destino/${idDestino}`);
    if (!response.ok) throw new Error('Error al obtener guías por destino');
    return await response.json();
}

async function getGuiasSinDestino() {
    const response = await fetch(`${API_BASE}/guia/sin-destino`);
    if (!response.ok) throw new Error('Error al obtener guías sin destino');
    return await response.json();
}

async function asignarDestinoAGuia(idGuia, idDestino) {
    const response = await fetch(`${API_BASE}/guia/${idGuia}/asignar-destino/${idDestino}`, {
        method: 'PUT'
    });
    if (!response.ok) throw new Error('Error al asignar destino');
}

async function quitarDestinoAGuia(idGuia) {
    const response = await fetch(`${API_BASE}/guia/${idGuia}/quitar-destino`, {
        method: 'PUT'
    });
    if (!response.ok) throw new Error('Error al quitar destino');
}

async function getUsuariosPorDestino(idDestino) {
    const response = await fetch(`${API_BASE}/usuario/destino/${idDestino}`);
    if (!response.ok) throw new Error('Error al obtener usuarios por destino');
    return await response.json();
}
