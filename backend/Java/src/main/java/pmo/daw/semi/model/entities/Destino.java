package pmo.daw.semi.model.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Destino {
	private Integer id;
	private String ciudad;
	private String pais;
	private Double precio;
	private boolean requierePasaporte;

	public Destino() {

	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String nombreCiudad) {
		this.ciudad = nombreCiudad;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String nombrePais) {
		this.pais = nombrePais;
	}

	public Double getPrecio() {
		return this.precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public boolean isRequierePasaporte() {
		return this.requierePasaporte;
	}

	public void setRequierePasaporte(boolean requierePasaporte) {
		this.requierePasaporte = requierePasaporte;
	}

	@Override
	public String toString() {
		return "Destino [id=" + id + ", ciudad=" + ciudad + ", pais=" + pais + ", precio=" + precio
				+ ", requierePasaporte=" + requierePasaporte + "]";
	}

	public static Destino mapResultSet(ResultSet rs) throws SQLException {
		return mapResultSet(rs, ""); // Llama al de abajo pasando "nada" como prefijo
	}

	public static Destino mapResultSet(ResultSet rs, String alias) throws SQLException {
		Destino destino = new Destino();

		// Usamos el alias para que, si en el futuro hay prefijos (ej: "d.id"),
		// el código siga funcionando.
		destino.setId(rs.getInt(alias + "id"));
		destino.setCiudad(rs.getString(alias + "ciudad"));
		destino.setPais(rs.getString(alias + "pais"));
		destino.setPrecio(rs.getDouble(alias + "precio"));
		destino.setRequierePasaporte(rs.getBoolean(alias + "requiere_pasaporte"));

		return destino;
	}
}