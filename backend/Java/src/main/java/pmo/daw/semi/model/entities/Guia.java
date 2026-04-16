package pmo.daw.semi.model.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Guia {

	private Integer id;
	private String nombre;
	private String apellidos;
	private Especialidad especialidad;
	private Integer idDestino; // FK hacia la tabla destino
	
	public Guia() {
		
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Especialidad getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(Especialidad esp) {
		this.especialidad = esp;
	}

	public Integer getIdDestino() {
		return this.idDestino;
	}

	public void setIdDestino(Integer idDestino) {
		this.idDestino = idDestino;
	}

	@Override
	public String toString() {
		return "Guia [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", especialidad=" + especialidad
				+ ", idDestino=" + idDestino + "]";

	}
	
	/**
	 * Mapea un ResultSet a un objeto Guia.
	 * 
	 * @param rs ResultSet con la fila del Guia
	 * @param alias Prefijo para las columnas (ej: "r.")
	 * @return Guia mapeado
	 * @throws SQLException si ocurre un error de SQL
	 */
	
	public static Guia mapResultSet(ResultSet rs) throws SQLException{
		return mapResultSet(rs, "");
	}
	
	public static Guia mapResultSet(ResultSet rs, String alias) throws SQLException {
		Guia guia = new Guia();
		
		guia.setId(rs.getInt(alias + "id"));
	    guia.setNombre(rs.getString(alias + "nombre"));
	    guia.setApellidos(rs.getString(alias + "apellidos"));
	    guia.setIdDestino(rs.getInt(alias + "id_destino"));

	    String espStr = rs.getString(alias + "especialidad");
	    if (espStr != null) {
	        // Convertimos el texto de la BD al valor del Enum
	        guia.setEspecialidad(Especialidad.valueOf(espStr.toUpperCase().trim()));
	    }

	    return guia;
		
	}
}