
package Repositorio;

import Conexion.ConexionBD;
import Modelo.Respuesta;
import java.sql.*;

public class RepositorioUsuario {
    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    
    public Respuesta<Boolean> ValidarUsuario(String usuario, String password){
        
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
            
            conexion.close(); rs.close(); ps.close();
            
            if(respuesta.isEmpty() || respuesta == null)
                return new Respuesta("Usuario y/o Contraseña son invalidos, intente nuevamente.");
            
        } catch (SQLException e) {
            return new Respuesta("Error BD: "+e.getMessage());
        }
        
        return new Respuesta<Boolean>(true);
    }
}
