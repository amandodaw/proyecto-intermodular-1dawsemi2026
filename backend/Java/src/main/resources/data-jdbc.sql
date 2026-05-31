-- USUARIOS
INSERT INTO usuario (id, nombre, apellidos, email, telefono, fecha_nacimiento) VALUES
(1, 'Ana', 'Estrella', 'ana@galaxy.com', '600111222', '1995-03-10'),
(2, 'Luna', 'Solar', 'luna@galaxy.com', '600333444', '1990-07-21'),
(3, 'Orión', 'Nebular', 'orion@galaxy.com', '600555666', '1988-11-05');

ALTER TABLE usuario ALTER COLUMN id RESTART WITH 4;

-- PASAPORTES
INSERT INTO pasaporte (id, numero, pais_expedicion, fecha_caducidad, id_usuario) VALUES
(1, 'GX-2026-001', 'Federación Galáctica', '2030-05-01', 1),
(2, 'GX-2026-002', 'Federación Galáctica', '2029-09-15', 2);

ALTER TABLE pasaporte ALTER COLUMN id RESTART WITH 3;

-- DESTINOS
INSERT INTO destino (id, ciudad, pais, precio, requiere_pasaporte) VALUES
(1, 'Ciudad Ares', 'Marte', 4500.0, true),
(2, 'Titán Prime', 'Saturno', 8200.0, true),
(3, 'Estación Poseidón', 'Neptuno', 12000.0, true),
(4, 'Europa Termal', 'Júpiter', 6500.0, true);

ALTER TABLE destino ALTER COLUMN id RESTART WITH 5;

-- GUIAS
INSERT INTO guia (id, nombre, apellidos, especialidad, id_destino) VALUES
(1, 'Nebulosa', 'Capitán', 'HISTORIA', 1),
(2, 'Estelar', 'Doctora', 'GEOGRAFIA', 2),
(3, 'Viento', 'Comandante', 'ARQUITECTURA', 3),
(4, 'Luz', 'Profesor', 'COMIDA', 4);

ALTER TABLE guia ALTER COLUMN id RESTART WITH 5;

-- RESERVAS
INSERT INTO reserva (id, fecha, id_usuario, id_destino, precio_total) VALUES
(1, '2026-06-01', 1, 1, 4500.0),
(2, '2026-08-15', 2, 2, 8200.0),
(3, '2026-09-20', 1, 3, 12000.0);

ALTER TABLE reserva ALTER COLUMN id RESTART WITH 4;
