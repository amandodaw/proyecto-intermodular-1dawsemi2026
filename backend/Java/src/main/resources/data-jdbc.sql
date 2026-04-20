-- USUARIOS
INSERT INTO usuario (id, nombre, apellidos, email, telefono, fecha_nacimiento) VALUES
(1, 'Juan', 'Pérez', 'juan@email.com', '600111222', '1995-03-10'),
(2, 'Laura', 'García', 'laura@email.com', '600333444', '1990-07-21'),
(3, 'Carlos', 'López', 'carlos@email.com', '600555666', '1988-11-05');

ALTER TABLE usuario ALTER COLUMN id RESTART WITH 4;

-- PASAPORTES
INSERT INTO pasaporte (id, numero, pais_expedicion, fecha_caducidad, id_usuario) VALUES
(1, 'X1234567', 'España', '2030-05-01', 1),
(2, 'Y7654321', 'España', '2029-09-15', 2);

ALTER TABLE pasaporte ALTER COLUMN id RESTART WITH 3;

-- DESTINOS
INSERT INTO destino (id, ciudad, pais, precio, requiere_pasaporte) VALUES
(1, 'París', 'Francia', 450.0, false),
(2, 'Tokio', 'Japón', 1200.0, true),
(3, 'Roma', 'Italia', 380.0, false),
(4, 'Nueva York', 'EEUU', 950.0, true);

ALTER TABLE destino ALTER COLUMN id RESTART WITH 5;

-- GUIAS
INSERT INTO guia (id, nombre, apellidos, especialidad, id_destino) VALUES
(1, 'Jean', 'Dupont', 'ARQUITECTURA', 1),
(2, 'Yuki', 'Tanaka', 'GEOGRAFIA', 2),
(3, 'Marco', 'Rossi', 'HISTORIA', 3);

ALTER TABLE guia ALTER COLUMN id RESTART WITH 4;

-- RESERVAS
INSERT INTO reserva (id, fecha, id_usuario, id_destino, precio_total) VALUES
(1, '2026-06-01', 1, 1, 450.0),
(2, '2026-08-15', 2, 2, 1200.0),
(3, '2026-09-20', 1, 3, 380.0);

ALTER TABLE reserva ALTER COLUMN id RESTART WITH 4;