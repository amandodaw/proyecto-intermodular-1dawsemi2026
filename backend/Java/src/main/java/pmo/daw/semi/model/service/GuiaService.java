package pmo.daw.semi.model.service;

import java.sql.Connection;
import java.util.List;

import pmo.daw.semi.excepciones.ServiceException;
import pmo.daw.semi.model.entities.Guia;
import pmo.daw.semi.model.repository.GuiaRepository;
import pmo.daw.semi.model.service.base.BaseService;

public class GuiaService extends BaseService<Guia, Integer> {

	// =============================================
	// Singleton estilo JNA
	// =============================================
	private static final GuiaService INSTANCE = new GuiaService();
	private GuiaService() {}
	public static GuiaService getInstance() { return INSTANCE; }

	// =============================================
	// Repositorio asociado
	// =============================================
	private final GuiaRepository guiaRepository = GuiaRepository.getInstance();

	// =============================================
	// CRUD básicos
	// =============================================

	/**
	 * Recupera todos los guías de la base de datos.
	 * @param conexion Connection a la base de datos
	 * @return Lista de todos los guías
	 * @throws ServiceException si ocurre un error de SQL
	 */
	@Override
	public List<Guia> findAll(Connection conexion) throws ServiceException {
		return guiaRepository.findAll(conexion);
	}

	/**
	 * Recupera un guía por su id.
	 * @param conexion Connection a la base de datos
	 * @param id       Identificador del guía
	 * @return Guía encontrado
	 * @throws ServiceException si el id es null o el guía no existe
	 */
	@Override
	public Guia findById(Connection conexion, Integer id) throws ServiceException {
		if (id == null) throw new ServiceException("id no puede ser null");

		Guia guia = guiaRepository.findById(conexion, id);
		if (guia == null) throw new ServiceException("guia no encontrado con id = " + id);

		return guia;
	}

	/**
	 * Crea un nuevo guía.
	 * @param conexion Connection a la base de datos
	 * @param entity   Objeto guía a guardar
	 * @return Guía guardado con su ID generado
	 * @throws ServiceException si la entidad es null
	 */
	@Override
	public Guia save(Connection conexion, Guia entity) throws ServiceException {
		if (entity == null) throw new ServiceException("guia no puede ser null");
		return guiaRepository.save(conexion, entity);
	}

	/**
	 * Actualiza un guía existente.
	 * @param conexion Connection a la base de datos
	 * @param id       Identificador del guía
	 * @param entity   Nuevos datos del guía
	 * @return Guía actualizado
	 * @throws ServiceException si no se encuentra el guía o los datos son null
	 */
	@Override
	public Guia update(Connection conexion, Integer id, Guia entity) throws ServiceException {
		if (id == null || entity == null) throw new ServiceException("Datos incompletos");

		if (guiaRepository.findById(conexion, id) == null) {
			throw new ServiceException("guia no encontrado con id = " + id);
		}

		return guiaRepository.update(conexion, id, entity);
	}

	/**
	 * Elimina un guía por su id.
	 * @param conexion Connection a la base de datos
	 * @param id       Identificador del guía a borrar
	 * @throws ServiceException si el id es null o no existe
	 */
	@Override
	public void deleteById(Connection conexion, Integer id) throws ServiceException {
		if (id == null) throw new ServiceException("id no puede ser null");

		if (guiaRepository.findById(conexion, id) == null) {
			throw new ServiceException("guia no encontrado con id = " + id);
		}

		guiaRepository.deleteById(conexion, id);
	}

	// =============================================
	// Queries auxiliares
	// =============================================

	/**
	 * Busca todos los guías asignados a un destino específico.
	 * @param conexion  Connection a la base de datos
	 * @param idDestino ID del destino a filtrar
	 * @return Lista de guías asociados
	 * @throws ServiceException si el idDestino es null
	 */
	public List<Guia> findByIdDestino(Connection conexion, Integer idDestino) throws ServiceException {
		if (idDestino == null) throw new ServiceException("idDestino no puede ser null");
		return guiaRepository.findByIdDestino(conexion, idDestino);
	}

	/**
	 * Recupera los guías que no tienen destino asignado.
	 * @param conexion Connection a la base de datos
	 * @return Lista de guías sin destino
	 * @throws ServiceException si falla la consulta
	 */
	public List<Guia> findIfIdDestinoIsNull(Connection conexion) throws ServiceException {
		return guiaRepository.findIfIdDestinoIsNull(conexion);
	}

	/**
	 * Asigna un destino a un guía.
	 * @param conexion  Connection a la base de datos
	 * @param id        ID del guía
	 * @param idDestino ID del destino a vincular
	 * @throws ServiceException si los IDs son nulos o el guía no existe
	 */
	public void addDestino(Connection conexion, Integer id, Integer idDestino) throws ServiceException {
		if (id == null || idDestino == null) throw new ServiceException("Los IDs no pueden ser null");
		
		if (guiaRepository.findById(conexion, id) == null) {
			throw new ServiceException("guia no encontrado con id = " + id);
		}
		
		guiaRepository.addDestino(conexion, id, idDestino);
	}

	/**
	 * Quita la asignación de destino de un guía.
	 * @param conexion Connection a la base de datos
	 * @param id       ID del guía
	 * @throws ServiceException si el id es null o el guía no existe
	 */
	public void removeDestino(Connection conexion, Integer id) throws ServiceException {
		if (id == null) throw new ServiceException("id no puede ser null");
		
		if (guiaRepository.findById(conexion, id) == null) {
			throw new ServiceException("guia no encontrado con id = " + id);
		}
		
		guiaRepository.removeDestino(conexion, id);
	}
}