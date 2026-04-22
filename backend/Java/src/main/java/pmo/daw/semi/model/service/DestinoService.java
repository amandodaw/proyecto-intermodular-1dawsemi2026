package pmo.daw.semi.model.service;

import java.sql.Connection;
import java.util.List;

import pmo.daw.semi.excepciones.ServiceException;
import pmo.daw.semi.model.entities.Destino;
import pmo.daw.semi.model.repository.DestinoRepository;
import pmo.daw.semi.model.service.base.BaseService;

public class DestinoService extends BaseService<Destino, Integer> {

	// =============================================
	// Singleton estilo JNA
	// =============================================
	// Asegura que solo exista una instancia de DestinoService
	private static final DestinoService INSTANCE = new DestinoService();
	private DestinoService() {}
	public static DestinoService getInstance() { return INSTANCE; }

	// =============================================
	// Repositorio asociado
	// =============================================
	private final DestinoRepository destinoRepository = DestinoRepository.getInstance();

	// =============================================
	// CRUD básicos
	// =============================================

	/**
	 * Recupera todos los destinos de la base de datos.
	 * @param conexion Connection a la base de datos
	 * @return Lista de todos los destinos
	 * @throws ServiceException si ocurre un error en la capa de persistencia
	 */
	@Override
	public List<Destino> findAll(Connection conexion) throws ServiceException {
		return destinoRepository.findAll(conexion);
	}

	/**
	 * Recupera un destino por su id.
	 * @param conexion Connection a la base de datos
	 * @param id       Identificador del destino
	 * @return Destino encontrado
	 * @throws ServiceException si el id es null o el destino no existe
	 */
	@Override
	public Destino findById(Connection conexion, Integer id) throws ServiceException {
		if (id == null) throw new ServiceException("id no puede ser null");

		Destino destino = destinoRepository.findById(conexion, id);
		if (destino == null) throw new ServiceException("destino no encontrado con id = " + id);

		return destino;
	}

	/**
	 * Crea un nuevo destino en la base de datos.
	 * @param conexion Connection a la base de datos
	 * @param entity   Objeto destino a guardar
	 * @return Destino guardado con su ID generado
	 * @throws ServiceException si la entidad es null o falla el guardado
	 */
	@Override
	public Destino save(Connection conexion, Destino entity) throws ServiceException {
		if (entity == null) throw new ServiceException("destino no puede ser null");

		return destinoRepository.save(conexion, entity);
	}

	/**
	 * Actualiza un destino existente.
	 * @param conexion Connection a la base de datos
	 * @param id       Identificador del destino a modificar
	 * @param entity   Datos actualizados del destino
	 * @return Destino actualizado
	 * @throws ServiceException si el id/entidad son null o el destino no existe
	 */
	@Override
	public Destino update(Connection conexion, Integer id, Destino entity) throws ServiceException {
		if (id == null || entity == null) throw new ServiceException("id y destino no pueden ser null");

		if (destinoRepository.findById(conexion, id) == null) {
			throw new ServiceException("destino no encontrado con id = " + id);
		}

		return destinoRepository.update(conexion, id, entity);
	}

	/**
	 * Elimina un destino de la base de datos por su id.
	 * @param conexion Connection a la base de datos
	 * @param id       Identificador del destino a eliminar
	 * @throws ServiceException si el id es null o el destino no existe
	 */
	@Override
	public void deleteById(Connection conexion, Integer id) throws ServiceException {
		if (id == null) throw new ServiceException("id no puede ser null");

		if (destinoRepository.findById(conexion, id) == null) {
			throw new ServiceException("destino no encontrado con id = " + id);
		}

		destinoRepository.deleteById(conexion, id);
	}
}