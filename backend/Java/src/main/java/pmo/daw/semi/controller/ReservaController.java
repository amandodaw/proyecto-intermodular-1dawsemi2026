package pmo.daw.semi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pmo.daw.semi.controller.base.BaseController;
import pmo.daw.semi.excepciones.ServiceException;
import pmo.daw.semi.excepciones.TransactionManagerException;
import pmo.daw.semi.model.entities.Reserva;
import pmo.daw.semi.model.service.ReservaService;

/**
 * Controlador REST para la entidad Reserva.
 * Gestiona el proceso de reservas de viajes y su ciclo de vida.
 */
@RestController
@RequestMapping("/api/reserva")
public class ReservaController extends BaseController<Reserva, Integer> {

    // Servicio Singleton para la lógica de negocio de Reserva
    private final ReservaService reservaService = ReservaService.getInstance();

    // =============================================
    // MÉTODOS CRUD
    // =============================================

    /**
     * GET /api/reserva
     * Devuelve la lista completa de reservas realizadas.
     * Retorna 200 con la lista o 500/400 en caso de error.
     */
    @Override
    @GetMapping
    public ResponseEntity<List<Reserva>> findAll() {
        try {
            List<Reserva> reservas = reservaService.findAll();
            return ResponseEntity.ok(reservas);
        } catch (TransactionManagerException e) {
            return ResponseEntity.internalServerError().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /api/reserva/{id}
     * Devuelve una reserva específica por su ID.
     * Retorna 200 con la reserva, 400 si id inválido, 500 si fallo interno.
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> findById(@PathVariable Integer id) {
        try {
            Reserva reserva = reservaService.findById(id);
            return ResponseEntity.ok(reserva);
        } catch (TransactionManagerException e) {
            return ResponseEntity.internalServerError().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * POST /api/reserva
     * Crea una nueva reserva. 
     * Retorna 201 con la reserva creada, 400 si hay errores de validación (ej. pasaporte caducado), 500 si fallo interno.
     */
    @Override
    @PostMapping
    public ResponseEntity<Reserva> save(@RequestBody Reserva reserva) {
        try {
            Reserva creada = reservaService.save(reserva);
            URI location = URI.create("/api/reserva/" + creada.getId());
            return ResponseEntity.created(location).body(creada);
        } catch (TransactionManagerException e) {
            return ResponseEntity.internalServerError().build();
        } catch (ServiceException e) {
            // Aquí es donde se captura el error si el pasaporte no es válido
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * PUT /api/reserva/{id}
     * Actualiza los datos de una reserva existente.
     * Retorna 200 con la reserva actualizada, 400 si id inválido, 500 si error interno.
     */
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@PathVariable Integer id, @RequestBody Reserva reserva) {
        try {
            Reserva modificado = reservaService.update(id, reserva);
            return ResponseEntity.ok(modificado);
        } catch (TransactionManagerException e) {
            return ResponseEntity.internalServerError().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * DELETE /api/reserva/{id}
     * Cancela y elimina una reserva del sistema.
     * Retorna 204 si se elimina correctamente, 400 si id inválido, 500 si error interno.
     */
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        try {
            reservaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (TransactionManagerException e) {
            return ResponseEntity.internalServerError().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // =============================================
    // CONSULTAS AUXILIARES
    // =============================================

    /**
     * GET /api/reserva/usuario/{idUsuario}
     * Devuelve el historial de reservas de un usuario específico.
     * Retorna 200 con la lista de reservas, 400 si idUsuario es inválido, 500 si fallo interno.
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Reserva>> findByIdUsuario(@PathVariable Integer idUsuario) {
        try {
            List<Reserva> reservas = reservaService.findByIdUsuario(idUsuario);
            return ResponseEntity.ok(reservas);
        } catch (TransactionManagerException e) {
            return ResponseEntity.internalServerError().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}