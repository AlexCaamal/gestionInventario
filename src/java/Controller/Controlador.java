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
                request.getRequestDispatcher("crearProducto.jsp").forward(request, response);
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
//        String param = request.getParameter("accion");
//        if (param != null && param.equalsIgnoreCase("lista")) {
//            request.getRequestDispatcher("index.jsp").forward(request, response);
//        }
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

    private void handleListaAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Respuesta<List<Producto>> respuestaBD = this.repoProducto.GetProductos();

        if (respuestaBD.esExito()) {
            List<Producto> listaProducto = respuestaBD.contenido;
            request.setAttribute("listaProducto", listaProducto);
            request.getRequestDispatcher("listaProductos.jsp").forward(request, response);
        } else {
            this.esError = true;
            request.setAttribute("mensajeErrorBD", respuestaBD.mensaje);
            request.setAttribute("esError", this.esError);
            // Puedes manejar el error directamente aquí o llamar a un método específico
            // this.responseError(request, respuestaBD.mensaje);
            request.getRequestDispatcher("listaProductos.jsp").forward(request, response);
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
}
