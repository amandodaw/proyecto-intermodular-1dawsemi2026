package pmo.daw.semi.model.repository;

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
	private DestinoRepository() {}
	public static DestinoRepository getInstance() {
		return INSTANCE;
	}
	// Fin del Eager Singleton

	@Override
	public List<Destino> findAll(Connection conexion) throws RepositoryException {
		
		List<Destino> listaDestinos = new ArrayList<>();
		String sql = "SELECT * FROM destino;";
		
		try(PreparedStatement ps = conexion.prepareStatement(sql)){
			
			try(ResultSet rs = ps.executeQuery()){
				
				while(rs.next()) {
					
					listaDestinos.add(Destino.mapResultSet(rs));
				}
				
			}
			
		}catch(SQLException e) {
			throw new RepositoryException("Error al consultar los destinos. ", e);
		}
		
		return listaDestinos;
	}

	@Override
	public Destino findById(Connection conexion, Integer id) throws RepositoryException {
		
		Destino destino = null;
		String sql = "SELECT * FROM destino WHERE id = ?;";
		
		try(PreparedStatement ps = conexion.prepareStatement(sql)){
			
			ps.setInt(1, id);
			
			try(ResultSet rs = ps.executeQuery()){
				
				while(rs.next()) {
					
					destino = Destino.mapResultSet(rs);
				}
				
			}
			
		}catch(SQLException e) {
			throw new RepositoryException("Error al consultar el destino. ", e);
		}
		
		return destino;
	}

	@Override
	public Destino save(Connection conexion, Destino entity) throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Destino update(Connection conexion, Integer id, Destino entity) throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Connection conexion, Integer id) throws RepositoryException {
		// TODO Auto-generated method stub
	}

	// =============================================
	// Queries extra
	// =============================================
}