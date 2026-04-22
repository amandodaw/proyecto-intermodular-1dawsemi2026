package pmo.daw.semi.model.service;

import java.sql.Connection;
import java.util.List;

import pmo.daw.semi.excepciones.ServiceException;
import pmo.daw.semi.model.entities.Reserva;
import pmo.daw.semi.model.entities.Destino;
import pmo.daw.semi.model.entities.Pasaporte;
import pmo.daw.semi.model.repository.ReservaRepository;
import pmo.daw.semi.model.service.base.BaseService;

public class ReservaService extends BaseService<Reserva, Integer> {

	// =============================================
	// Singleton estilo JNA
	// =============================================
	private static final ReservaService INSTANCE = new ReservaService();
	private ReservaService() {}
	public static ReservaService getInstance() { return INSTANCE; }

	// =============================================
	// Repositorios y servicios asociados
	// =============================================
	private final ReservaRepository reservaRepository = ReservaRepository.getInstance();
	private final DestinoService destinoService = DestinoService.getInstance();
	private final PasaporteService pasaporteService = PasaporteService.getInstance();

	// =============================================
	// CRUD básicos
	// =============================================

	/**
	 * Recupera todas las reservas de la base de datos.
	 * @param conexion Connection a la base de datos
	 * @return Lista de todas las reservas
	 * @throws ServiceException si falla la consulta SQL
	 */
	@Override
	public List<Reserva> findAll(Connection conexion) throws ServiceException {
		return reservaRepository.findAll(conexion);
	}

	/**
	 * Recupera una reserva por su id.
	 * @param conexion Connection a la base de datos
	 * @param id       Identificador de la reserva
	 * @return Reserva encontrada
	 * @throws ServiceException si el id es null o la reserva no existe
	 */
	@Override
	public Reserva findById(Connection conexion, Integer id) throws ServiceException {
		if (id == null) throw new ServiceException("id no puede ser null");

		Reserva reserva = reservaRepository.findById(conexion, id);
		if (reserva == null) throw new ServiceException("reserva no encontrada con id = " + id);

		return reserva;
	}

	/**
	 * Crea una nueva reserva validando destino y requisitos de pasaporte.
	 * @param conexion Connection a la base de datos
	 * @param entity   Objeto reserva a guardar
	 * @return Reserva guardada con ID generado
	 * @throws ServiceException si faltan requisitos (pasaporte) o el destino no existe
	 */
	@Override
	public Reserva save(Connection conexion, Reserva entity) throws ServiceException {
		if (entity == null) throw new ServiceException("reserva no puede ser null");

		// Validación de destino (uso de otro servicio)
		Destino destino = destinoService.findById(conexion, entity.getIdDestino());

		// Validación de pasaporte si el destino lo requiere
		if (destino.isRequierePasaporte()) {
			Pasaporte p = pasaporteService.findByIdUsuario(conexion, entity.getIdUsuario());
			
			if (p == null) {
				throw new ServiceException("El destino requiere pasaporte y el usuario no dispone de uno");
			}
			
			if (p.getFechaCaducidad().before(entity.getFecha())) {
				throw new ServiceException("El pasaporte del usuario estará caducado en la fecha del viaje");
			}
		}

		return reservaRepository.save(conexion, entity);
	}

	/**
	 * Actualiza los datos de una reserva.
	 * @param conexion Connection a la base de datos
	 * @param id       ID de la reserva
	 * @param entity   Nuevos datos de la reserva
	 * @return Reserva actualizada
	 * @throws ServiceException si la reserva no existe
	 */
	@Override
	public Reserva update(Connection conexion, Integer id, Reserva entity) throws ServiceException {
		if (id == null || entity == null) throw new ServiceException("Datos incompletos");

		if (reservaRepository.findById(conexion, id) == null) {
			throw new ServiceException("reserva no encontrada con id = " + id);
		}

		return reservaRepository.update(conexion, id, entity);
	}

	/**
	 * Elimina una reserva de la base de datos.
	 * @param conexion Connection a la base de datos
	 * @param id       ID de la reserva a borrar
	 * @throws ServiceException si el id es null o no existe
	 */
	@Override
	public void deleteById(Connection conexion, Integer id) throws ServiceException {
		if (id == null) throw new ServiceException("id no puede ser null");

		if (reservaRepository.findById(conexion, id) == null) {
			throw new ServiceException("reserva no encontrada con id = " + id);
		}

		reservaRepository.deleteById(conexion, id);
	}

	// =============================================
	// Queries auxiliares
	// =============================================

	/**
	 * Recupera las reservas de un usuario específico.
	 * @param conexion  Connection a la base de datos
	 * @param idUsuario Identificador del usuario
	 * @return Lista de reservas asociadas al usuario
	 * @throws ServiceException si el idUsuario es null
	 */
	public List<Reserva> findByIdUsuario(Connection conexion, Integer idUsuario) throws ServiceException {
		if (idUsuario == null) throw new ServiceException("idUsuario no puede ser null");
		return reservaRepository.findByIdUsuario(conexion, idUsuario);
	}
}