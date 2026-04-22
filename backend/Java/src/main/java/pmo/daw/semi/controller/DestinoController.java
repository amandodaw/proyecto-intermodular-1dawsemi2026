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
import pmo.daw.semi.model.entities.Destino;
import pmo.daw.semi.model.service.DestinoService;

/**
 * Controlador REST para la entidad Destino.
 * Gestiona las operaciones CRUD sobre los destinos turísticos.
 */
@RestController
@RequestMapping("/api/destino")
public class DestinoController extends BaseController<Destino, Integer> {

    // Servicio Singleton para la lógica de negocio de Destino
    private final DestinoService destinoService = DestinoService.getInstance();

    /**
     * GET /api/destino
     * Devuelve la lista completa de destinos.
     * Retorna 200 con la lista o 500/400 en caso de error.
     */
    @Override
    @GetMapping
    public ResponseEntity<List<Destino>> findAll() {
        try {
            List<Destino> destinos = destinoService.findAll();
            return ResponseEntity.ok(destinos);
        } catch (TransactionManagerException e) {
            return ResponseEntity.internalServerError().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /api/destino/{id}
     * Devuelve un destino por su ID.
     * Retorna 200 con el destino, 404 si no existe, 500 si fallo interno.
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Destino> findById(@PathVariable Integer id) {
        try {
            Destino destino = destinoService.findById(id);
            return ResponseEntity.ok(destino);
        } catch (TransactionManagerException e) {
            return ResponseEntity.internalServerError().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * POST /api/destino
     * Crea un nuevo destino.
     * Retorna 201 con el destino creado y la cabecera Location.
     */
    @Override
    @PostMapping
    public ResponseEntity<Destino> save(@RequestBody Destino destino) {
        try {
            Destino creado = destinoService.save(destino);
            URI location = URI.create("/api/destino/" + creado.getId());
            return ResponseEntity.created(location).body(creado);
        } catch (TransactionManagerException e) {
            return ResponseEntity.internalServerError().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * PUT /api/destino/{id}
     * Actualiza un destino existente.
     * Retorna 200 con el destino actualizado.
     */
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Destino> update(@PathVariable Integer id, @RequestBody Destino destino) {
        try {
            Destino modificado = destinoService.update(id, destino);
            return ResponseEntity.ok(modificado);
        } catch (TransactionManagerException e) {
            return ResponseEntity.internalServerError().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * DELETE /api/destino/{id}
     * Elimina un destino por su ID.
     * Retorna 204 si se elimina correctamente.
     */
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        try {
            destinoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (TransactionManagerException e) {
            return ResponseEntity.internalServerError().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}