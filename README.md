# 🌍 Agencia de Viajes — Proyecto Final FP Dual

> Proyecto Full Stack desarrollado en **Angular + Spring Boot** para el programa **1º FP Dual** de NTT Data.

---

## 📋 Descripción del proyecto

Aplicación web de gestión de una agencia de viajes que permite consultar destinos y sus viajes asociados, crear nuevos registros y visualizar la relación entre entidades. La aplicación está dividida en frontend (Angular) y backend (Spring Boot con base de datos H2 en memoria), comunicados a través de una API REST.

**Relación del modelo de datos:** Un **Destino** tiene muchos **Viajes** (relación 1:M).

---

## 🏗️ Estructura del repositorio

```
proyecto-intermodular-1dawsemi2026/
├── backend/          # API REST con Spring Boot (Java)
├── frontend/         # Aplicación Angular
├── database/         # Scripts SQL y datos de ejemplo
├── docs/             # Documentación adicional del proyecto
├── .gitignore
└── README.md
```

---

## ✅ Requisitos cumplidos

### Frontend (Angular 21)
- [x] Componentes StandAlone
- [x] Uso de Signals para manejo de estado
- [x] Formularios reactivos con validación
- [x] Llamadas a la API REST desde servicios Angular
- [x] Uso de Observables en los servicios
- [x] Navegación con rutas entre vistas

### Backend (Spring Boot)
- [x] API REST expuesta y funcional
- [x] Operaciones básicas sobre datos (CRUD)
- [x] Base de datos H2 en memoria
- [x] Relación 1:M entre las entidades `Destino` y `Viaje`
- [x] Persistencia con base de datos relacional (JPA / Hibernate)

---

## 🚀 Instrucciones de arranque

### Requisitos previos

| Herramienta | Versión mínima |
|-------------|---------------|
| Java JDK    | 17+           |
| Maven       | 3.8+          |
| Node.js     | 18+           |
| Angular CLI | 21+           |

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

```bash
cd frontend
npm install
ng serve
```

La aplicación estará disponible en: **http://localhost:4200**

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

También puedes encontrar el script SQL en la carpeta `database/` del repositorio para usarlo con otros motores de base de datos.

---

## ⚠️ Problemas encontrados durante el desarrollo

### 1. CORS entre Angular y Spring Boot
Al realizar llamadas desde Angular (`localhost:4200`) al backend (`localhost:8080`), el navegador bloqueaba las peticiones por política CORS.

**Solución:** Se añadió la anotación `@CrossOrigin` en los controladores REST y se configuró un bean `WebMvcConfigurer` global en Spring Boot para permitir el origen del frontend.

---

### 2. Serialización circular con JPA (relación 1:M)
Al serializar un `Destino` que contenía su lista de `Viajes`, Jackson entraba en bucle infinito porque `Viaje` a su vez referenciaba al `Destino`.

**Solución:** Se usaron las anotaciones `@JsonManagedReference` en la entidad padre (`Destino`) y `@JsonBackReference` en la entidad hija (`Viaje`) para romper la referencia circular.

---

### 3. Formularios reactivos y validaciones en Angular
Al enviar formularios con campos vacíos, la petición HTTP llegaba al backend con valores nulos causando errores 500.

**Solución:** Se añadieron validadores de Angular (`Validators.required`, `Validators.min`) en el `FormGroup` y se desactivó el botón de envío cuando el formulario es inválido. El backend también incluye validaciones con `@NotNull` y `@NotBlank`.

---

### 4. Integración de la base de datos H2 con datos iniciales
El script `data.sql` no se ejecutaba correctamente al arrancar porque Spring Boot 3.x cambia el comportamiento por defecto de inicialización SQL.

**Solución:** Se añadió `spring.sql.init.mode=always` en el fichero `application.properties` para forzar la ejecución del script al inicio.

---

### 5. Routing en Angular con componentes StandAlone
Al usar componentes StandAlone sin módulo (`AppModule`), la configuración de rutas requería un enfoque diferente al tradicional.

**Solución:** Se usó `provideRouter(routes)` en `app.config.ts` en lugar del clásico `RouterModule.forRoot()`, siguiendo el patrón de Angular 17+.

---

## 👥 Equipo

| Nombre | Rol principal |
|--------|--------------|
| Desarrollador/a 1 | Backend (Spring Boot) |
| Desarrollador/a 2 | Frontend (Angular) |
| Desarrollador/a 3 | Base de datos e integración |

---

## 📄 Tecnologías utilizadas

- **Frontend:** Angular 21, TypeScript, HTML5, CSS3
- **Backend:** Java 17, Spring Boot 3, Spring Data JPA, Hibernate
- **Base de datos:** H2 (en memoria, desarrollo)
- **Build:** Maven (backend), npm / Angular CLI (frontend)
- **Control de versiones:** Git + GitHub

---

*Proyecto realizado en el marco del programa 1º FP Dual — NTT Data · Curso 2025/2026*

