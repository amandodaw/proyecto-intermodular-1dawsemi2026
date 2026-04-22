package pmo.daw.semi.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pmo.daw.semi.excepciones.RepositoryException;
import pmo.daw.semi.model.entities.Guia;
import pmo.daw.semi.model.repository.base.BaseRepository;

public class GuiaRepository extends BaseRepository<Guia, Integer> {
	// Eager Singleton (JNA-style)
	private static final GuiaRepository INSTANCE = new GuiaRepository();

	private GuiaRepository() {
	}

	public static GuiaRepository getInstance() {
		return INSTANCE;
	}
	// Fin del Eager Singleton

	/**
	 * Recupera todos los guias.
	 * 
	 * @param conexion Connection a la base de datos
	 * @return Lista de Guias
	 * @throws RepositoryException si ocurre un error de SQL
	 */

	@Override
	public List<Guia> findAll(Connection conexion) throws RepositoryException {

		List<Guia> listaGuias = new ArrayList<>();
		String sql = "SELECT * FROM guia";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					listaGuias.add(Guia.mapResultSet(rs));
				}
			}

		} catch (SQLException e) {
			throw new RepositoryException("Error al consultar los guías.", e);
		}

		return listaGuias;
	}

	/**
	 * Recupera un Guia por id.
	 * 
	 * @param conexion Connection a la base de datos
	 * @param id       del Guia a recuperar
	 * @return Guia recuperado de la base de datos
	 * @throws RepositoryException si ocurre un error de SQL
	 */

	@Override
	public Guia findById(Connection conexion, Integer id) throws RepositoryException {

		Guia guia = null;
		String sql = "SELECT * FROM guia WHERE id = ?";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					guia = Guia.mapResultSet(rs);
				}
			}
		} catch (SQLException e) {
			throw new RepositoryException("No se ha podido recuperar el Guia con ID: " + id, e);
		}
		return guia;
	}

	/**
	 * Guarda un nuevo guía en la base de datos. * @param conexion Connection a la
	 * base de datos
	 * 
	 * @param entity Guía a guardar
	 * @return Guía guardado con su ID generado
	 * @throws RepositoryException si ocurre un error de SQL
	 */
	@Override
	public Guia save(Connection conexion, Guia entity) throws RepositoryException {

		String sql = "INSERT INTO guia (nombre, apellidos, especialidad, id_destino) VALUES (?, ?, ?, ?)";

		try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, entity.getNombre());
			ps.setString(2, entity.getApellidos());
			ps.setString(3, entity.getEspecialidad() != null ? entity.getEspecialidad().name() : null);
			ps.setObject(4, entity.getIdDestino());

			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {

				if (rs.next())
					entity.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new RepositoryException("Error al guardar el guía", e);
		}
		return entity;
	}

	/**
	 * Actualiza un guía existente por su ID. * @param conexion Connection a la base
	 * de datos
	 * 
	 * @param id     ID del guía a actualizar
	 * @param entity Nuevos datos del guía
	 * @return Guía actualizado
	 * @throws RepositoryException si ocurre un error de SQL
	 */
	@Override
	public Guia update(Connection conexion, Integer id, Guia entity) throws RepositoryException {

		String sql = "UPDATE guia SET nombre = ?, apellidos = ?, especialidad = ?, id_destino = ? WHERE id = ?";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setString(1, entity.getNombre());
			ps.setString(2, entity.getApellidos());
			ps.setString(3, entity.getEspecialidad() != null ? entity.getEspecialidad().name() : null);
			ps.setObject(4, entity.getIdDestino());
			ps.setInt(5, id);

			ps.executeUpdate();
			entity.setId(id);

		} catch (SQLException e) {
			throw new RepositoryException("Error al actualizar el guía", e);
		}
		return entity;
	}

	/**
	 * Elimina un guía por su ID. * @param conexion Connection a la base de datos
	 * 
	 * @param id ID del guía a eliminar
	 * @throws RepositoryException si ocurre un error de SQL
	 */
	@Override
	public void deleteById(Connection conexion, Integer id) throws RepositoryException {

		String sql = "DELETE FROM guia WHERE id = ?";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RepositoryException("Error al eliminar el guía", e);
		}
	}

	// =============================================
	// Queries extra
	// =============================================

	/**
	 * Busca todos los guías que están asignados a un destino concreto. * @param
	 * conexion Connection a la base de datos
	 * 
	 * @param idDestino ID del destino para filtrar los guías
	 * @return Lista de guías asociados a ese destino
	 * @throws RepositoryException si ocurre un error de SQL
	 */
	public List<Guia> findByIdDestino(Connection conexion, Integer idDestino) throws RepositoryException {

		List<Guia> lista = new ArrayList<>();
		String sql = "SELECT * FROM guia WHERE id_destino = ?";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, idDestino);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next())
					lista.add(Guia.mapResultSet(rs));
			}
		} catch (SQLException e) {
			throw new RepositoryException("Error al buscar guías por destino", e);
		}
		return lista;
	}

	/**
	 * Recupera la lista de guías que actualmente no tienen asignado ningún destino.
	 * * @param conexion Connection a la base de datos
	 * 
	 * @return Lista de guías "libres" (id_destino es NULL)
	 * @throws RepositoryException si ocurre un error de SQL
	 */
	public List<Guia> findIfIdDestinoIsNull(Connection conexion) throws RepositoryException {

		List<Guia> lista = new ArrayList<>();
		String sql = "SELECT * FROM guia WHERE id_destino IS NULL";

		try (PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next())
				lista.add(Guia.mapResultSet(rs));
		} catch (SQLException e) {
			throw new RepositoryException("Error al buscar guías sin destino", e);
		}
		return lista;
	}

	/**
	 * Vincula un guía con un destino específico actualizando su clave ajena.
	 * * @param conexion Connection a la base de datos
	 * 
	 * @param id        ID del guía
	 * @param idDestino ID del destino a asignar
	 * @throws RepositoryException si ocurre un error de SQL
	 */
	public void addDestino(Connection conexion, Integer id, Integer idDestino) throws RepositoryException {

		String sql = "UPDATE guia SET id_destino = ? WHERE id = ?";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, idDestino);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException("Error al asignar destino al guía", e);
		}
	}

	/**
	 * Desvincula a un guía de su destino actual (pone el campo id_destino a NULL).
	 * * @param conexion Connection a la base de datos
	 * 
	 * @param id ID del guía
	 * @throws RepositoryException si ocurre un error de SQL
	 */
	public void removeDestino(Connection conexion, Integer id) throws RepositoryException {

		String sql = "UPDATE guia SET id_destino = NULL WHERE id = ?";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException("Error al desasignar destino del guía", e);
		}
	}
}