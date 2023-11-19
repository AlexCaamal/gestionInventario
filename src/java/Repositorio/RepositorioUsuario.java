package Repositorio;

import Conexion.ConexionBD;
import Modelo.Respuesta;
import Modelo.usuario;
import java.sql.*;

public class RepositorioUsuario {

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;

    public Respuesta<Boolean> ValidarUsuario(String usuario, String password) {

        String sql = "SELECT*FROM empleado WHERE Usuario = ? AND Contraseña = ? AND Activo = 1";

        try {
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            rs = ps.executeQuery();

            String respuesta = "";

            while (rs.next()) {
                respuesta = rs.getString("Nombres");
            }

            conexion.close();
            rs.close();
            ps.close();

            if (respuesta.isEmpty() || respuesta == null) {
                return new Respuesta("Usuario y/o Contraseña son invalidos, intente nuevamente.");
            }

        } catch (SQLException e) {
            return new Respuesta("Error BD: " + e.getMessage());
        }

        return new Respuesta<Boolean>(true);
    }

    public Respuesta<Boolean> ValidarUsuarioRepetido(String usuario) {

        if (usuario == null || usuario.isEmpty()) {
            return new Respuesta<Boolean>("El campo 'Usuario' es obligatorio.");
        }

        String sql = "SELECT COUNT(Id) as Repetidos FROM empleado WHERE Usuario = ? AND Activo = 1";

        try {
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            int repetido = 0;

            while (rs.next()) {
                repetido = rs.getInt("Repetidos");
            }

            conexion.close();
            rs.close();
            ps.close();

            if (repetido != 0) {
                return new Respuesta<Boolean>(true);
            }

        } catch (SQLException e) {
            return new Respuesta("Error BD: " + e.getMessage());
        }

        return new Respuesta<Boolean>(false);
    }

    public Respuesta<usuario> CrearUsuario(usuario user, Boolean usuarioRepetido) {

        if (user.getNombres().isEmpty() || user.getNombres() == null) {
            return new Respuesta<usuario>("El campo 'Nombres' es obligatorio.");
        }

        if (user.getUsuario().isEmpty() || user.getUsuario() == null) {
            return new Respuesta<usuario>("El campo 'Usuario' es obligatorio.");
        }

        if (usuarioRepetido) {
            return new Respuesta<usuario>("El Nombre de Usuario '" + user.getUsuario() + "' es obligatorio.");
        }

        if (user.getContraseña().isEmpty() || user.getContraseña() == null) {
            return new Respuesta<usuario>("El campo 'Contraseña' es obligatorio.");
        }

        if (user.getTelefonos().isEmpty() || user.getTelefonos() == null) {
            return new Respuesta("El Telefono es Obligatorio");
        }

        if (!user.getTelefonos().matches("\\d+")) {
            return new Respuesta("El Teléfono debe contener solo números.");
        }

        if (user.getTelefonos().length() > 10 || user.getTelefonos().length() < 10) {
            return new Respuesta("El Teléfono debe contener 10 digitos");
        }

        String sql = "INSERT INTO empleado (Codigo, Nombres, Apellidos, Telefonos, Usuario, Contraseña) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, user.getCodigo());
            ps.setString(2, user.getNombres());
            ps.setString(3, user.getApellidos());
            ps.setString(4, user.getTelefonos());
            ps.setString(5, user.getUsuario());
            ps.setString(6, user.getContraseña());
            ps.executeUpdate();

            conexion.close();
            rs.close();
            ps.close();

        } catch (SQLException e) {
            return new Respuesta("Error BD: " + e.getMessage());
        }

        return new Respuesta<usuario>(user);
    }
}
