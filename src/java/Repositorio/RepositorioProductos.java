package Repositorio;

import Conexion.ConexionBD;
import Modelo.Producto;
import Modelo.Respuesta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioProductos {

    Connection conexion;
    PreparedStatement ps;
    ResultSet rs;

    public Respuesta<List<Producto>> GetProductos() {
        List<Producto> listaProductos = new ArrayList<Producto>();
        try {
            String sql = "SELECT*FROM producto WHERE Activo = 1";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            Producto prod;
            while (rs.next()) {
                prod = new Producto();
                prod.setCodigoBarra(rs.getString("CodigoBarra"));
                prod.setId(rs.getInt("Id"));
                prod.setNombre(rs.getString("Nombre"));
                prod.setDescripcion(rs.getString("Descripcion"));
                prod.setPrecio(rs.getDouble("Precio"));
                prod.setStock(rs.getDouble("Stock"));
                listaProductos.add(prod);
            }
            conexion.close();
            rs.close();
            ps.close();

        } catch (Exception e) {
            return new Respuesta<List<Producto>>("Error BD: No se a completado la consulta de Productos, intente nuevamente.");
        }

        return new Respuesta<List<Producto>>(listaProductos);
    }

    public Respuesta<Producto> GetProductoPorId(int idProducto) {
        Producto producto = new Producto();
        try {
            String sql = "SELECT*FROM producto WHERE Activo = 1 AND Id = ?";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();
            while (rs.next()) {
                producto.setCodigoBarra(rs.getString("CodigoBarra"));
                producto.setId(rs.getInt("Id"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setStock(rs.getDouble("Stock"));
            }
            conexion.close();
            rs.close();
            ps.close();

            if (producto.getNombre() == null) {
                return new Respuesta<Producto>("El producto se previamente eliminado.");
            }

        } catch (Exception e) {
            return new Respuesta<Producto>("Error BD: No se a completado la consulta del Producto, intente nuevamente. " + e.getMessage());
        }

        return new Respuesta<Producto>(producto);
    }

    public Respuesta DeleteProductoPorId(int idProducto) {
        try {
            String sql = "DELETE FROM producto WHERE Id = ?";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idProducto);

            ps.executeUpdate();
            conexion.close();
            rs.close();
            ps.close();

        } catch (Exception e) {
            return new Respuesta("Error BD: No se a completado la eliminacion del Producto, intente nuevamente..." + e.getMessage());
        }
        return new Respuesta();
    }

    public Respuesta<Boolean> ObtenerNombreRepetido(String nombre) {

        if (nombre == null || nombre.isEmpty()) {
            return new Respuesta<Boolean>("El campo 'Nombre' es obligario.");
        }

        try {
            String sql = "SELECT COUNT(Id) AS repetidos FROM producto WHERE Activo = 1 AND Nombre = ?";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            int repetidos = 0;

            while (rs.next()) {
                repetidos = rs.getInt("repetidos");
            }

            if (repetidos != 0) {
                return new Respuesta<Boolean>(true);
            }

        } catch (SQLException e) {
            return new Respuesta<Boolean>("Error BD: No se a completado la consulta del Producto, intente nuevamente.");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new Respuesta<Boolean>(false);
    }
    
    public Respuesta<Boolean> ObtenerNombreRepetidoPorId(String nombre, int Id) {

        if (nombre == null || nombre.isEmpty()) {
            return new Respuesta<Boolean>("El campo 'Nombre' es obligario.");
        }

        try {
            String sql = "SELECT COUNT(Id) AS repetidos FROM producto WHERE Activo = 1 AND Nombre = ? AND Id != ?";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, Id);
            rs = ps.executeQuery();

            int repetidos = 0;

            while (rs.next()) {
                repetidos = rs.getInt("repetidos");
            }

            if (repetidos != 0) {
                return new Respuesta<Boolean>(true);
            }

        } catch (SQLException e) {
            return new Respuesta<Boolean>("Error BD: No se a completado la consulta del Producto, intente nuevamente.");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new Respuesta<Boolean>(false);
    }

    public Respuesta<Boolean> ObtenerCodigoRepetido(String codigoBarra) {

        if (codigoBarra == null || codigoBarra.isEmpty()) {
            return new Respuesta<Boolean>("El campo 'Nombre' es obligario.");
        }

        try {
            String sql = "SELECT COUNT(Id) AS repetidos FROM producto WHERE Activo = 1 AND CodigoBarra = ?";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, codigoBarra);
            rs = ps.executeQuery();

            int repetidos = 0;

            while (rs.next()) {
                repetidos = rs.getInt("repetidos");
            }

            if (repetidos != 0) {
                return new Respuesta<Boolean>(true);
            }

        } catch (SQLException e) {
            return new Respuesta<Boolean>("Error BD: No se a completado la consulta del Producto, intente nuevamente.");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new Respuesta<Boolean>(false);
    }
    
    public Respuesta<Boolean> ObtenerCodigoRepetidoPorId(String codigoBarra, int Id) {

        if (codigoBarra == null || codigoBarra.isEmpty()) {
            return new Respuesta<Boolean>("El campo 'Nombre' es obligario.");
        }

        try {
            String sql = "SELECT COUNT(Id) AS repetidos FROM producto WHERE Activo = 1 AND CodigoBarra = ? AND Id != ?";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, codigoBarra);
            ps.setInt(2, Id);
            rs = ps.executeQuery();

            int repetidos = 0;

            while (rs.next()) {
                repetidos = rs.getInt("repetidos");
            }

            if (repetidos != 0) {
                return new Respuesta<Boolean>(true);
            }

        } catch (SQLException e) {
            return new Respuesta<Boolean>("Error BD: No se a completado la consulta del Producto, intente nuevamente.");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new Respuesta<Boolean>(false);
    }

    public Respuesta<Producto> AgregarProducto(Producto producto, String precio, Boolean nombreRepetido, Boolean codigoBarraRepetido) {

        if (producto == null) {
            return new Respuesta<Producto>("Ha ocurrido en la creación del Producto, intente de nuevo.");
        }

        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            return new Respuesta<Producto>("El campo 'Nombre' es obligario.");
        }

        if (producto.getCodigoBarra() == null || producto.getCodigoBarra().isEmpty()) {
            return new Respuesta<Producto>("El campo 'Código de Barra' es obligario.");
        }

        if (nombreRepetido) {
            return new Respuesta<Producto>("El Nombre '" + producto.getNombre() + "' ya se encuentra registrado.");
        }

        if (codigoBarraRepetido) {
            return new Respuesta<Producto>("El Código de Barra '" + producto.getCodigoBarra() + "' ya se encuentra registrado.");
        }

        try {
            double valorParceado = Double.parseDouble(precio);

            if (valorParceado == 0) {
                return new Respuesta<Producto>("El campo 'Precio' es obligario.");
            }

            producto.setPrecio(valorParceado);

        } catch (NumberFormatException e) {
            return new Respuesta<Producto>("Por favor, ingrese un valor válido para el Campo 'Precio'.");
        }

        try {
            String sql = "INSERT INTO producto (Nombre, Precio, Descripcion, Stock, CodigoBarra) VALUES (?, ?, ?, ?, ?)";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);

            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getStock());
            ps.setString(5, producto.getCodigoBarra());

            ps.executeUpdate();

        } catch (Exception e) {
            return new Respuesta<Producto>("Error BD: No se a completado la eliminacion del Producto, intente nuevamente..." + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Respuesta<Producto>(producto);
    }

    public Respuesta<Producto> Modificar(Producto producto, String precio, Boolean nombreRepetido, Boolean codigoBarraRepetido) {

        if (producto == null) {
            return new Respuesta<Producto>("Ha ocurrido en la creación del Producto, intente de nuevo.");
        }

        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            return new Respuesta<Producto>("El campo 'Nombre' es obligario.");
        }

        if (producto.getCodigoBarra() == null || producto.getCodigoBarra().isEmpty()) {
            return new Respuesta<Producto>("El campo 'Código de Barra' es obligario.");
        }

        if (nombreRepetido) {
            return new Respuesta<Producto>("El Nombre '" + producto.getNombre() + "' ya se encuentra registrado.");
        }

        if (codigoBarraRepetido) {
            return new Respuesta<Producto>("El Código de Barra '" + producto.getCodigoBarra() + "' ya se encuentra registrado.");
        }

        try {
            double valorParceado = Double.parseDouble(precio);

            if (valorParceado == 0) {
                return new Respuesta<Producto>("El campo 'Precio' es obligario.");
            }

            producto.setPrecio(valorParceado);

        } catch (NumberFormatException e) {
            return new Respuesta<Producto>("Por favor, ingrese un valor válido para el Campo 'Precio'.");
        }

        try {
            String sql = "UPDATE producto SET Nombre = ?, Precio = ?, Descripcion = ?, Stock = ?, CodigoBarra = ? WHERE Id = ?;";
            conexion = ConexionBD.GetConnection();
            ps = conexion.prepareStatement(sql);

            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getStock());
            ps.setString(5, producto.getCodigoBarra());
            ps.setInt(6, producto.getId());
            
            ps.executeUpdate();

        } catch (Exception e) {
            return new Respuesta<Producto>("Error BD: No se a completado la Modificación del Producto, intente nuevamente..." + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Respuesta<Producto>(producto);
    }
}
