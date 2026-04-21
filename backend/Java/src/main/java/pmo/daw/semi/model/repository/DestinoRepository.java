package pmo.daw.semi.model.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pmo.daw.semi.excepciones.RepositoryException;
import pmo.daw.semi.model.entities.Destino;
import pmo.daw.semi.model.repository.base.BaseRepository;

public class DestinoRepository extends BaseRepository<Destino, Integer> {
	// Eager Singleton (JNA-style)
	private static final DestinoRepository INSTANCE = new DestinoRepository();

	private DestinoRepository() {
	}

	public static DestinoRepository getInstance() {
		return INSTANCE;
	}
	// Fin del Eager Singleton

	// =============================================
	// CRUD básicos
	// =============================================

	/**
	 * Recupera todos los destinos de la base de datos.
	 * 
	 * @param conexion Connection a la base de datos
	 * @return Lista de destinos
	 * @throws RepositoryException si ocurre un error de SQL
	 */

	@Override
	public List<Destino> findAll(Connection conexion) throws RepositoryException {

		List<Destino> listaDestinos = new ArrayList<>();
		String sql = "SELECT * FROM destino;";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					listaDestinos.add(Destino.mapResultSet(rs));
				}

			}

		} catch (SQLException e) {
			throw new RepositoryException("Error al consultar los destinos. ", e);
		}

		return listaDestinos;
	}

	/**
	 * Recupera destino por id de la base de datos.
	 * 
	 * @param conexion Connection a la base de datos
	 * @param id del destino a buscar
	 * @return destino econtrado si o null si no existe
	 * @throws RepositoryException si ocurre un error de SQL
	 */

	@Override
	public Destino findById(Connection conexion, Integer id) throws RepositoryException {

		Destino destino = null;
		String sql = "SELECT * FROM destino WHERE id = ?;";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					destino = Destino.mapResultSet(rs);
				}

			}

		} catch (SQLException e) {
			throw new RepositoryException("Error al consultar el destino. ", e);
		}

		return destino;
	}

	/**
	 * Guarda un destino en la base de datos.
	 * 
	 * @param conexion Connection a la base de datos
	 * @param entity el destino a guardar
	 * @return el destino guardado
	 * @throws RepositoryException si ocurre un error de SQL
	 */

	@Override
	public Destino save(Connection conexion, Destino entity) throws RepositoryException {

		String sql = "INSERT INTO destino (ciudad, pais, precio, requiere_pasaporte) VALUES (?, ?, ?, ?)";

		try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, entity.getCiudad());
			ps.setString(2, entity.getPais());
			ps.setDouble(3, entity.getPrecio());
			ps.setBoolean(4, entity.isRequierePasaporte());

			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					entity.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new RepositoryException("El destino no ha sido guardado. ", e);
		}

		return entity;
	}

	/**
	 * Actualiza un destino por id en la base de datos.
	 * 
	 * @param conexion Connection a la base de datos
	 * @param id del destino a actualizar
	 * @param entity destino para actualizar
	 * @return destino actualizado
	 * @throws RepositoryException si ocurre un error de SQL
	 */

	@Override
	public Destino update(Connection conexion, Integer id, Destino entity) throws RepositoryException {

		String sql = "UPDATE destino SET ciudad = ?, pais = ?, precio = ?, requiere_pasaporte = ? WHERE id = ?;";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setString(1, entity.getCiudad());
			ps.setString(2, entity.getPais());
			ps.setDouble(3, entity.getPrecio());
			ps.setBoolean(4, entity.isRequierePasaporte());

			// El ID para el WHERE
			ps.setInt(5, id);

			ps.executeUpdate();

			// Es buena práctica asegurar que el objeto devuelto tenga el ID correcto
			entity.setId(id);

		} catch (SQLException e) {
			throw new RepositoryException("Error al actualizar el destino con ID: " + id, e);
		}

		return entity;
	}

	/**
	 * Borra un destino por id en la base de datos.
	 * 
	 * @param conexion Connection a la base de datos
	 * @param id del destino a eliminar.
	 * @return void
	 * @throws RepositoryException si ocurre un error de SQL
	 */

	@Override
	public void deleteById(Connection conexion, Integer id) throws RepositoryException {

		String sql = "DELETE FROM destino WHERE id = ?";
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException("Error al eliminar el destino", e);
		}
	}

	// =============================================
	// Queries extra
	// =============================================
}