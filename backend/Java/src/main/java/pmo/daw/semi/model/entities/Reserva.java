package pmo.daw.semi.model.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Reserva {
	private Integer id;
	private LocalDate fecha;
	private Integer idUsuario;
	private Integer idDestino;
	private Double precioTotal;

	public Reserva() {

	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return this.fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@JsonIgnore
	public java.sql.Date getFechaDate() {
		return (this.fecha == null) ? null : java.sql.Date.valueOf(this.fecha);
	}

	public void setFechaDate(java.sql.Date fecha) {
		this.fecha = (fecha == null) ? null : fecha.toLocalDate();
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdDestino() {
		return this.idDestino;
	}

	public void setIdDestino(Integer idDestino) {
		this.idDestino = idDestino;
	}

	public Double getPrecioTotal() {
		return this.precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	@Override
	public String toString() {
	    return "Reserva [id=" + id + ", fecha=" + fecha + ", idUsuario=" + idUsuario 
	            + ", idDestino=" + idDestino + ", precioTotal=" + precioTotal + "]";
	}

	/**
	 * Mapea un ResultSet a un objeto Reserva.
	 * 
	 * @param rs    ResultSet con la fila de la reserva
	 * @param alias Prefijo para las columnas (ej: "r.")
	 * @return Reserva mapeada
	 * @throws SQLException si ocurre un error de SQL
	 */

	public static Reserva mapResultSet(ResultSet rs) throws SQLException {
		return mapResultSet(rs, "");
	}

	public static Reserva mapResultSet(ResultSet rs, String alias) throws SQLException {
	    Reserva reserva = new Reserva();
	    
	    reserva.setId(rs.getInt(alias + "id"));
	    reserva.setFechaDate(rs.getDate(alias + "fecha"));
	    reserva.setIdUsuario(rs.getObject(alias + "id_usuario", Integer.class));
	    reserva.setIdDestino(rs.getObject(alias + "id_destino", Integer.class));	    
	    reserva.setPrecioTotal(rs.getDouble(alias + "precio_total"));
	    
	    return reserva;
	}

}