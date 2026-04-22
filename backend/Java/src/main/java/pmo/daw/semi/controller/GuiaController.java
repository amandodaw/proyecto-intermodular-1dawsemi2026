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
import pmo.daw.semi.model.entities.Guia;
import pmo.daw.semi.model.service.GuiaService;

/**
 * Controlador REST para la entidad Guía. Expone endpoints CRUD y operaciones de
 * asignación de destinos para guías.
 */
@RestController
@RequestMapping("/api/guia")
public class GuiaController extends BaseController<Guia, Integer> {

	// Servicio Singleton para la lógica de negocio de Guía
	private final GuiaService guiaService = GuiaService.getInstance();

	// =============================================
	// MÉTODOS CRUD
	// =============================================

	/**
	 * GET /api/guia Devuelve la lista completa de guías. Retorna 200 con la lista o
	 * 500/400 en caso de error.
	 */
	@Override
	@GetMapping
	public ResponseEntity<List<Guia>> findAll() {
		try {
			List<Guia> guias = guiaService.findAll();
			return ResponseEntity.ok(guias);
		} catch (TransactionManagerException e) {
			return ResponseEntity.internalServerError().build();
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * GET /api/guia/{id} Devuelve un guía por su ID. Retorna 200 con el guía, 400
	 * si id inválido, 500 si fallo interno.
	 */
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Guia> findById(@PathVariable Integer id) {
		try {
			Guia guia = guiaService.findById(id);
			return ResponseEntity.ok(guia);
		} catch (TransactionManagerException e) {
			return ResponseEntity.internalServerError().build();
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * POST /api/guia Crea un nuevo guía. Retorna 201 con el guía creado y la
	 * cabecera Location.
	 */
	@Override
	@PostMapping
	public ResponseEntity<Guia> save(@RequestBody Guia guia) {
		try {
			Guia creado = guiaService.save(guia);
			URI location = URI.create("/api/guia/" + creado.getId());
			return ResponseEntity.created(location).body(creado);
		} catch (TransactionManagerException e) {
			return ResponseEntity.internalServerError().build();
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * PUT /api/guia/{id} Actualiza un guía existente. Retorna 200 con el guía
	 * actualizado, 400 si id inválido, 500 si error interno.
	 */
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<Guia> update(@PathVariable Integer id, @RequestBody Guia guia) {
		try {
			Guia modificado = guiaService.update(id, guia);
			return ResponseEntity.ok(modificado);
		} catch (TransactionManagerException e) {
			return ResponseEntity.internalServerError().build();
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * DELETE /api/guia/{id} Elimina un guía por su ID. Retorna 204 si se elimina
	 * correctamente, 400 si id inválido, 500 si error interno.
	 */
	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		try {
			guiaService.deleteById(id);
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
	 * GET /api/guia/destino/{idDestino} Devuelve la lista de guías filtrados por
	 * idDestino. Retorna 200 con la lista de guías, 400 si idDestino es inválido,
	 * 500 si fallo interno.
	 */
	@GetMapping("/destino/{idDestino}")
	public ResponseEntity<List<Guia>> findByIdDestino(@PathVariable Integer idDestino) {
		try {
			List<Guia> guias = guiaService.findByIdDestino(idDestino);
			return ResponseEntity.ok(guias);
		} catch (TransactionManagerException e) {
			return ResponseEntity.internalServerError().build();
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * GET /api/guia/sin-destino Devuelve la lista de guías que no tienen un destino
	 * asignado. Retorna 200 con la lista de guías o 500/400 en caso de error.
	 */
	@GetMapping("/sin-destino")
	public ResponseEntity<List<Guia>> findIfIdDestinoIsNull() {
		try {
			List<Guia> guias = guiaService.findIfIdDestinoIsNull();
			return ResponseEntity.ok(guias);
		} catch (TransactionManagerException e) {
			return ResponseEntity.internalServerError().build();
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * PUT /api/guia/{id}/asignar-destino/{idDestino} Asigna un destino a un guía.
	 * Retorna 200 si se asigna correctamente, 400 si los IDs son inválidos, 500 si
	 * error interno.
	 */
	@PutMapping("/{id}/asignar-destino/{idDestino}")
	public ResponseEntity<Void> addDestino(@PathVariable Integer id, @PathVariable Integer idDestino) {
		try {
			guiaService.addDestino(id, idDestino);
			return ResponseEntity.ok().build();
		} catch (TransactionManagerException e) {
			return ResponseEntity.internalServerError().build();
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * PUT /api/guia/{id}/quitar-destino Elimina la asignación de destino de un
	 * guía. Retorna 200 si se quita correctamente, 400 si el ID es inválido, 500 si
	 * error interno.
	 */
	@PutMapping("/{id}/quitar-destino")
	public ResponseEntity<Void> removeDestino(@PathVariable Integer id) {
		try {
			guiaService.removeDestino(id);
			return ResponseEntity.ok().build();
		} catch (TransactionManagerException e) {
			return ResponseEntity.internalServerError().build();
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}