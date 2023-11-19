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

public class Controlador extends HttpServlet {

    Boolean esError = false;
    RepositorioProductos repoProducto = new RepositorioProductos();
    String mensajeErrorBD = null;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "lista":
                handleListaAction(request, response);
                break;
            case "logout":
                handleLogoutAction(request, response);
                break;
            case "crear":
                this.esError = false;
                request.setAttribute("producto", new Producto());
                request.setAttribute("mensajeErrorBD", "");
                request.setAttribute("esError", esError);
                request.getRequestDispatcher("crearProducto.jsp").forward(request, response);
                break;
            case "cancelar":
                request.getRequestDispatcher("Controlador?accion=lista").forward(request, response);
                break;
            case "Agregar":
                request.getRequestDispatcher("Controlador?accion=lista").forward(request, response);
                break;
            case "Modificar":
                request.getRequestDispatcher("Controlador?accion=lista").forward(request, response);
                break;
            case "Buscar":
                String textoBuscar = request.getParameter("busqueda");

                if (textoBuscar == null) {
                    handleListaAction(request, response);
                } else {
                    this.BuscarProductos(request, response, textoBuscar);
                }

                break;
            default:
                throw new AssertionError("Acción no válida: " + accion);
        }
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
                case "editar":
                    this.ObtenerPorId(request, response, id);
                    break;
                case "eliminar":
                    this.ElimibarPorId(request, response, id);
                    break;
                default:
                    throw new AssertionError();
            }
        }
        processRequest(request, response);
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
        String accion = request.getParameter("accion");
        switch (accion) {
            case "Agregar":
                this.CrearProducto(request, response);
                break;
            case "Modificar":
                this.Modificar(request, response);
                break;
            default:
                processRequest(request, response);
        }
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

    private void handleListaAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Respuesta<List<Producto>> respuestaBD = this.repoProducto.GetProductos();

        if (respuestaBD.esExito()) {
            request.setAttribute("listaProducto", respuestaBD.contenido);
            this.responseError(request, response, "", "listaProductos", respuestaBD.esError());

        } else {
            this.responseError(request, response, respuestaBD.mensaje, "listaProductos", respuestaBD.esError());
        }
    }

    private void handleLogoutAction(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("index.jsp");
    }

    private void responseError(HttpServletRequest request, HttpServletResponse response, String mensajeErrorDB, String path, Boolean esError) {
        this.esError = esError;
        try {
            request.setAttribute("mensajeErrorBD", mensajeErrorDB);
            request.setAttribute("esError", esError);
            request.getRequestDispatcher(path + ".jsp").forward(request, response);
        } catch (Exception e) {
        }
    }

    private void ObtenerPorId(HttpServletRequest request, HttpServletResponse response, String id) {
        int idProducto = Integer.parseInt(id);
        Respuesta<Producto> respuestaProducto = repoProducto.GetProductoPorId(idProducto);

        if (respuestaProducto.esExito()) {
            Producto producto = respuestaProducto.contenido;
            request.setAttribute("producto", producto);
            this.responseError(request, response, "", "editar_producto", respuestaProducto.esError());
        } else {
            this.responseError(request, response, respuestaProducto.mensaje, "editar_producto", respuestaProducto.esError());
        }
    }

    private void ElimibarPorId(HttpServletRequest request, HttpServletResponse response, String id) {
        int idProductoEliminar = Integer.parseInt(id);
        Respuesta respuestaEliminar = repoProducto.DeleteProductoPorId(idProductoEliminar);

        try {
            if (respuestaEliminar.esExito()) {
                this.handleListaAction(request, response);
            } else {
                this.responseError(request, response, respuestaEliminar.mensaje, "editar_producto", respuestaEliminar.esError());
            }
        } catch (Exception e) {
            this.responseError(request, response, "Error Interno: " + e.getMessage(), "editar_producto", respuestaEliminar.esError());
        }
    }

    private void CrearProducto(HttpServletRequest request, HttpServletResponse response) {
        String textCodBarra = request.getParameter("textCodBarra");
        String textNombre = request.getParameter("textNombre");
        String textDescripcion = request.getParameter("textDescripcion");
        String textPrecio = request.getParameter("textPrecio");
        String textStock = request.getParameter("textStock");

        Producto producto = new Producto(0, textNombre, 0, textDescripcion, Double.parseDouble(textStock), textCodBarra);

        Respuesta<Boolean> responseBD = repoProducto.ObtenerNombreRepetido(textNombre);

        if (responseBD.esExito()) {

            Respuesta<Boolean> responseBDCod = repoProducto.ObtenerCodigoRepetido(textCodBarra);

            if (responseBDCod.esExito()) {

                Respuesta<Producto> responseCrear = repoProducto.AgregarProducto(producto, textPrecio, responseBD.contenido, responseBDCod.contenido);

                if (responseCrear.esExito()) {
                    request.setAttribute("producto", new Producto());
                    this.responseError(request, response, "Se Agrego Correctamente.", "crearProducto", responseCrear.esError());
                } else {
                    request.setAttribute("producto", producto);
                    this.responseError(request, response, responseCrear.mensaje, "crearProducto", responseCrear.esError());
                }
            } else {
                request.setAttribute("producto", producto);
                this.responseError(request, response, responseBDCod.mensaje, "crearProducto", responseBDCod.esError());
            }
        } else {
            request.setAttribute("producto", producto);
            this.responseError(request, response, responseBD.mensaje, "crearProducto", responseBD.esError());
        }
    }

    private void Modificar(HttpServletRequest request, HttpServletResponse response) {
        String textCodBarra = request.getParameter("textCodBarra");
        String textNombre = request.getParameter("textNombre");
        String textDescripcion = request.getParameter("textDescripcion");
        String textPrecio = request.getParameter("textPrecio");
        String textStock = request.getParameter("textStock");
        String textId = request.getParameter("textId");

        Producto producto = new Producto(Integer.parseInt(textId), textNombre, 0, textDescripcion, Double.parseDouble(textStock), textCodBarra);

        Respuesta<Boolean> responseBD = repoProducto.ObtenerNombreRepetidoPorId(producto.getNombre(), producto.getId());

        if (responseBD.esExito()) {

            Respuesta<Boolean> responseBDCod = repoProducto.ObtenerCodigoRepetidoPorId(producto.getCodigoBarra(), producto.getId());

            if (responseBDCod.esExito()) {

                Respuesta<Producto> responseCrear = repoProducto.Modificar(producto, textPrecio, responseBD.contenido, responseBDCod.contenido);

                if (responseCrear.esExito()) {
                    request.setAttribute("producto", new Producto());
                    try {
                        this.handleListaAction(request, response);
                    } catch (Exception e) {
                        this.responseError(request, response, "Error Interno: " + e.getMessage(), "editar_producto", responseBDCod.esError());
                    }
                } else {
                    request.setAttribute("producto", producto);
                    this.responseError(request, response, responseCrear.mensaje, "editar_producto", responseCrear.esError());
                }
            } else {
                request.setAttribute("producto", producto);
                this.responseError(request, response, responseBDCod.mensaje, "editar_producto", responseBDCod.esError());
            }
        } else {
            request.setAttribute("producto", producto);
            this.responseError(request, response, responseBD.mensaje, "editar_producto", responseBD.esError());
        }
    }

    private void BuscarProductos(HttpServletRequest request, HttpServletResponse response, String criterio)
            throws ServletException, IOException {

        Respuesta<List<Producto>> respuestaBD = this.repoProducto.GetProductoPorCriterio(criterio);

        if (respuestaBD.esExito()) {
            request.setAttribute("listaProducto", respuestaBD.contenido);
            this.responseError(request, response, "", "listaProductos", respuestaBD.esError());

        } else {
            this.responseError(request, response, respuestaBD.mensaje, "listaProductos", respuestaBD.esError());
        }

    }
}
