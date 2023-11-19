/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Modelo.Producto;
import Modelo.Respuesta;
import Repositorio.RepositorioProductos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControladorListaProductos extends HttpServlet {

    RepositorioProductos repoProducto = new RepositorioProductos();
    Boolean esError = false;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        switch (accion) {
            case "lista-productos":

                Respuesta<List<Producto>> respuestaBD = repoProducto.GetProductos();

                if (respuestaBD.esExito()) {
                    List<Producto> listaProducto = respuestaBD.contenido;
                    
                    request.setAttribute("listaProducto", listaProducto);
                    request.getRequestDispatcher("listaProductos.jsp").forward(request, response);
                } else {
                    this.esError = true;
                    request.setAttribute("mensajeErrorBD", respuestaBD.mensaje);
                    request.setAttribute("esError", esError);
//                    this.responseError(request, respuestaBD.mensaje);
                    request.getRequestDispatcher("listaProductos.jsp").forward(request, response);
                }
                break;
            case "logout":
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect("index.jsp");
                break;
//            case "crear":
//                request.getRequestDispatcher("crearProducto.jsp").forward(request, response);
//                break;
//            case "cancelar":
//                request.getRequestDispatcher("listaProductos.jsp").forward(request, response);
//                break;
            default:
                processRequest(request, response);
        }
        processRequest(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("idProducto");
        if (id != null && id != "") {
            String accion = request.getParameter("accion");
            switch (accion) {
//                case "editar":
//
//                    int idProducto = Integer.parseInt(id);
//                    Respuesta<Producto> respuestaProducto = repoProducto.GetProductoPorId(idProducto);
//
//                    if (respuestaProducto.esExito()) {
//                        Producto producto = respuestaProducto.contenido;
//                        response.setContentType("application/json");
//                        response.setCharacterEncoding("UTF-8");
//
//                        // Ejemplo: Envía una respuesta JSON simple
//                        String jsonResponse = "{ \"id\": " + idProducto + ", \"detalle\": \"Información del producto\" }";
//                        response.getWriter().write(jsonResponse);
//                    } else {
//                        // Manejar el caso en el que no se puede obtener el producto
//                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                        response.getWriter().write("Error al obtener los detalles del producto.");
//                    }
//                    break;
                case "eliminar":

                    int idProductoEliminar = Integer.parseInt(id);
                    Respuesta respuestaEliminar = repoProducto.DeleteProductoPorId(idProductoEliminar);

                    if (respuestaEliminar.esExito()) {

                        Respuesta<List<Producto>> respuestaBD = repoProducto.GetProductos();

                        if (respuestaBD.esExito()) {
                            List<Producto> listaProducto = respuestaBD.contenido;
                            request.setAttribute("listaProducto", listaProducto);
                            request.getRequestDispatcher("listaProductos.jsp").forward(request, response);
                        } else {
                            this.esError = true;
                            request.setAttribute("mensajeErrorBD", respuestaBD.mensaje);
                            request.setAttribute("esError", esError);
                            request.getRequestDispatcher("listaProductos.jsp").forward(request, response);
                        }
                    } else {
                        // Manejar el caso en el que no se puede obtener el producto
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write("Error al obtener los detalles del producto.");
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String MensajeErrorCrear = null;
//        String accion = request.getParameter("accion");
//        switch (accion) {
//            case "Agregar":
//                String textCodBarra = request.getParameter("textCodBarra");
//                String textNombre = request.getParameter("textNombre");
//                String textDescripcion = request.getParameter("textDescripcion");
//                String textPrecio = request.getParameter("textPrecio");
//                String textStock = request.getParameter("textStock");
//
//                Respuesta<Boolean> responseBD = repoProducto.ObtenerNombreRepetido(textNombre);
//
//                if (responseBD.esExito()) {
//
//                    Respuesta<Boolean> responseBDCod = repoProducto.ObtenerCodigoRepetido(textCodBarra);
//
//                    if (responseBDCod.esExito()) {
//
//                        Producto producto = new Producto(0, textNombre, 0, textDescripcion, Double.parseDouble(textStock), textCodBarra);
//
//                        Respuesta<Boolean> responseCrear = repoProducto.AgregarProducto(producto, textPrecio, responseBD.contenido, responseBDCod.contenido);
//
//                        if (responseCrear.esExito()) {
////                            this.CrearCorrecto(response);
//                        } else {
//                            this.responseError(request, responseCrear.mensaje);
//                        }
//                    } else {
//                        this.responseError(request, responseBDCod.mensaje);
//                    }
//                } else {
//                    this.responseError(request, responseBD.mensaje);
//                }
//
//                break;
//            default:
//                processRequest(request, response);
//        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String convertirProductoAJson(Producto producto) {
        // Aquí deberías usar una biblioteca como Gson para convertir el objeto Producto a JSON
        // Esto es un ejemplo simple sin Gson:
        return "{ \"id\": " + producto.getId() + ", \"codigoBarra\": \"" + producto.getCodigoBarra() + "\", \"nombre\": \"" + producto.getNombre() + "\", \"descripcion\": \"" + producto.getDescripcion() + "\", \"precio\": " + producto.getPrecio() + ", \"stock\": " + producto.getStock() + "}";
    }

//    private void responseError(HttpServletRequest request, String mensajeError) {
//        this.esError = true;
//        request.setAttribute("mensajeErrorBD", mensajeError);
//        request.setAttribute("esError", esError);
//    }

//    private void CrearCorrecto(HttpServletResponse response) {
//        try {
//            response.setContentType("text/html;charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<html><head><script>");
//            out.println("function reiniciarFormulario() { document.getElementById('miFormulario').reset(); }");
//            out.println("window.onload = reiniciarFormulario;");
//            out.println("</script></head><body></body></html>");
//        } catch (Exception e) {
//        }
//    }
}
