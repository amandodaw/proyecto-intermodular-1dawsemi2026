package pmo.daw.semi.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pmo.daw.semi.excepciones.RepositoryException;
import pmo.daw.semi.model.entities.Reserva;
import pmo.daw.semi.model.repository.base.BaseRepository;

public class ReservaRepository extends BaseRepository<Reserva, Integer> {
	// Eager Singleton (JNA-style)
	private static final ReservaRepository INSTANCE = new ReservaRepository();

	private ReservaRepository() {
	}

	public static ReservaRepository getInstance() {
		return INSTANCE;
	}
	// Fin del Eager Singleton

	/**
	 * Recupera todas las reservas de la base de datos.
	 * 
	 * @param conexion Connection a la base de datos
	 * @return Lista de reservas
	 * @throws RepositoryException si ocurre un error de SQL
	 */
	@Override
	public List<Reserva> findAll(Connection conexion) throws RepositoryException {
		List<Reserva> listaReservas = new ArrayList<>();
		String sql = "SELECT * FROM reserva";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					listaReservas.add(Reserva.mapResultSet(rs));
				}
			}
		} catch (SQLException e) {
			throw new RepositoryException("Error al consultar las reservas.", e);
		}
		return listaReservas;
	}

	/**
	 * Recupera una reserva por su id.
	 * 
	 * @param conexion Connection a la base de datos
	 * @param id       del la reserva a recuperar
	 * @return Reserva recuperada o null si no existe
	 * @throws RepositoryException si ocurre un error de SQL
	 */
	@Override
	public Reserva findById(Connection conexion, Integer id) throws RepositoryException {
		Reserva reserva = null;
		String sql = "SELECT * FROM reserva WHERE id = ?";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					reserva = Reserva.mapResultSet(rs);
				}
			}
		} catch (SQLException e) {
			throw new RepositoryException("No se ha podido recuperar la reserva con ID: " + id, e);
		}
		return reserva;
	}

	/**
	 * Guarda una nueva reserva en la base de datos.
	 * 
	 * @param conexion Connection a la base de datos
	 * @param entity   la reserva a guardar
	 * @return la reserva guardada con su ID generado
	 * @throws RepositoryException si ocurre un error de SQL
	 */
	@Override
	public Reserva save(Connection conexion, Reserva entity) throws RepositoryException {
		String sql = "INSERT INTO reserva (fecha, id_usuario, id_destino, precio_total) VALUES (?, ?, ?, ?)";

		try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setDate(1, entity.getFechaDate());
			ps.setInt(2, entity.getIdUsuario());
			ps.setInt(3, entity.getIdDestino());
			ps.setDouble(4, entity.getPrecioTotal());

			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					entity.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new RepositoryException("Error al guardar la reserva.", e);
		}
		return entity;
	}

	/**
	 * Actualiza una reserva existente por su ID.
	 * 
	 * @param conexion Connection a la base de datos
	 * @param id       de la reserva a actualizar
	 * @param entity   datos de la reserva para actualizar
	 * @return reserva actualizada
	 * @throws RepositoryException si ocurre un error de SQL
	 */
	@Override
	public Reserva update(Connection conexion, Integer id, Reserva entity) throws RepositoryException {
		String sql = "UPDATE reserva SET fecha = ?, id_usuario = ?, id_destino = ?, precio_total = ? WHERE id = ?";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setDate(1, entity.getFechaDate());
			ps.setInt(2, entity.getIdUsuario());
			ps.setInt(3, entity.getIdDestino());
			ps.setDouble(4, entity.getPrecioTotal());
			ps.setInt(5, id);

			ps.executeUpdate();
			entity.setId(id);
		} catch (SQLException e) {
			throw new RepositoryException("Error al actualizar la reserva con ID: " + id, e);
		}
		return entity;
	}

	/**
	 * Elimina una reserva por su ID.
	 * 
	 * @param conexion Connection a la base de datos
	 * @param id       de la reserva a eliminar
	 * @throws RepositoryException si ocurre un error de SQL
	 */
	@Override
	public void deleteById(Connection conexion, Integer id) throws RepositoryException {
		String sql = "DELETE FROM reserva WHERE id = ?";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException("Error al eliminar la reserva.", e);
		}
	}

	// =============================================
	// Queries extra
	// =============================================

	/**
	 * Recupera todas las reservas asociadas a un usuario concreto.
	 * 
	 * @param conexion  Connection a la base de datos
	 * @param idUsuario ID del usuario
	 * @return Lista de reservas del usuario
	 * @throws RepositoryException si ocurre un error de SQL
	 */
	public List<Reserva> findByIdUsuario(Connection conexion, Integer idUsuario) throws RepositoryException {
		List<Reserva> lista = new ArrayList<>();
		String sql = "SELECT * FROM reserva WHERE id_usuario = ?";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					lista.add(Reserva.mapResultSet(rs));
				}
			}
		} catch (SQLException e) {
			throw new RepositoryException("Error al buscar reservas por usuario.", e);
		}
		return lista;
	}
}