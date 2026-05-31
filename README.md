# 🌍 Agencia de Viajes — Proyecto Intermodular 1º DAW

> Proyecto Full Stack desarrollado en **JavaScript Vanilla + Java (Spring Boot + JDBC sin JPA)** para el **Proyecto Intermodular 1º DAW**.

---

## 📋 Descripción del proyecto

Aplicación web de gestión de una agencia de viajes que permite consultar destinos y sus viajes asociados, crear nuevos registros y visualizar la relación entre entidades. La aplicación está dividida en frontend (HTML, CSS y JavaScript Vanilla) y backend (Spring Boot con JDBC y base de datos H2 en memoria), comunicados a través de una API REST.

**Relación del modelo de datos:** Un **Destino** tiene muchos **Viajes** (relación 1:M).

---

## 🏗️ Estructura del repositorio

```
proyecto-intermodular-1dawsemi2026/
├── backend/          # API REST con Spring Boot + JDBC (Java)
├── frontend/         # Aplicación web con HTML, CSS y JavaScript Vanilla
├── docs/             # Documentación adicional del proyecto
├── .gitignore
└── README.md
```

---

## ✅ Requisitos cumplidos

### Frontend (HTML + CSS + JavaScript Vanilla)
- [x] Interfaz desarrollada con HTML5 y CSS3
- [x] Lógica de cliente con JavaScript Vanilla (sin frameworks)
- [x] Llamadas a la API REST mediante `fetch`
- [x] Navegación entre vistas
- [x] Diagrama de Gantt del proyecto

### Backend (Spring Boot + JDBC)
- [x] API REST expuesta y funcional
- [x] Operaciones básicas sobre datos (CRUD)
- [x] Base de datos H2 en memoria
- [x] Relación 1:M entre las entidades `Destino` y `Viaje`
- [x] Persistencia con base de datos relacional (JDBC, sin JPA)

---

## 🚀 Instrucciones de arranque

### Requisitos previos

| Herramienta | Versión mínima |
|-------------|---------------|
| Java JDK    | 25+           |
| Maven       | 3.8+          |

---

### 1. Clonar el repositorio

```bash
git clone git@github.com:amandodaw/proyecto-intermodular-1dawsemi2026.git
cd proyecto-intermodular-1dawsemi2026
```

---

### 2. Arrancar el Backend

```bash
cd backend
mvn spring-boot:run
```

El servidor arrancará en: **http://localhost:8080**

La consola H2 está disponible en: **http://localhost:8080/h2-console**
- JDBC URL: `jdbc:h2:mem:agenciadb`
- Usuario: `sa`
- Contraseña: *(vacía)*

---

### 3. Arrancar el Frontend

Abre el fichero `frontend/index.html` directamente en el navegador, o sírvelo con cualquier servidor estático local (por ejemplo con la extensión Live Server de VS Code).

---

## 🔌 Endpoints de la API REST

### Destinos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/destinos` | Listar todos los destinos |
| GET | `/api/destinos/{id}` | Ver detalle de un destino |
| POST | `/api/destinos` | Crear un nuevo destino |
| GET | `/api/destinos/{id}/viajes` | Consultar los viajes de un destino |

### Viajes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/viajes` | Listar todos los viajes |
| GET | `/api/viajes/{id}` | Ver detalle de un viaje |
| POST | `/api/viajes` | Crear un nuevo viaje |

---

## 🗃️ Modelo de datos

```
Destino (1) ────────── (M) Viaje
  - id (Long)               - id (Long)
  - nombre (String)         - nombre (String)
  - pais (String)           - fechaSalida (LocalDate)
  - descripcion (String)    - precio (Double)
                            - plazasDisponibles (Integer)
                            - destino_id (FK → Destino)
```

---

## 🌱 Datos de ejemplo

La base de datos H2 se inicializa automáticamente al arrancar el backend con datos de ejemplo mediante el script `data.sql` ubicado en `src/main/resources/`.

---

## 👥 Equipo

| Nombre | Rol principal |
|--------|--------------|
| Amando Cuenca Carabia | JavaScript y diagrama de Gantt |
| Juan José León Carmona | Maquetación (HTML & CSS) |
| Marcos Romá Sánchez | Backend y base de datos |

---

## 📄 Tecnologías utilizadas

- **Frontend:** HTML5, CSS3, JavaScript Vanilla
- **Backend:** Java 25, Spring Boot 3, JDBC (sin JPA)
- **Base de datos:** H2 (en memoria, desarrollo)
- **Build:** Maven (backend)
- **Control de versiones:** Git + GitHub

---

*Proyecto Intermodular 1º DAW · Curso 2025/2026*
