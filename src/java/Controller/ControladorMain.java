/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Modelo.Respuesta;
import Modelo.usuario;
import Repositorio.RepositorioUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CCNAR
 */
public class ControladorMain extends HttpServlet {

    RepositorioUsuario repoUsuario = new RepositorioUsuario();
    Boolean esError = false;

    LocalDate fechaHoy = LocalDate.now();
    DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String fechaFormateada = fechaHoy.format(formateador);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorMain</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorMain at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String accion = request.getParameter("accion");
        if (accion != null) {
            if (accion.equalsIgnoreCase("cuenta-nueva")) {
                request.setAttribute("usuario", new usuario());
                this.responseError(request, response, "", "crear-usuario", false);
                request.getRequestDispatcher("crear-usuario.jsp").forward(request, response);
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
        if (accion.equalsIgnoreCase("Ingresar")) {
            String user = request.getParameter("inputUsuario");
            String pass = request.getParameter("inputPassword");

            Respuesta respuestaRepo = repoUsuario.ValidarUsuario(user, pass);

            if (respuestaRepo.esExito()) {
                request.getRequestDispatcher("ControladorDashBoard?accion=lista").forward(request, response);
            } else {
                this.esError = true;
                request.setAttribute("mensajeError", respuestaRepo.mensaje);
                request.setAttribute("esError", esError);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else if (accion.equalsIgnoreCase("Crear")) {
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String telefono = request.getParameter("telefono");
            String usuario = request.getParameter("usuario");
            String contrasena = request.getParameter("contrasena");

            usuario user = new usuario(0, fechaFormateada + "-" + usuario, nombres, apellidos, telefono, usuario, contrasena, 0);

            this.CrearCuenta(request, response, user);

        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
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

    private void CrearCuenta(HttpServletRequest request, HttpServletResponse response, usuario user) {

        Respuesta<Boolean> repuesta = repoUsuario.ValidarUsuarioRepetido(user.getUsuario());

        if (repuesta.esExito()) {

            Respuesta<usuario> respuesta = repoUsuario.CrearUsuario(user, repuesta.contenido);

            if (respuesta.esExito()) {
                try {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } catch (Exception e) {
                    this.responseError(request, response, "Error Interno: " + e.getMessage(), "crear-usuario", true);
                }
            } else {
                request.setAttribute("usuario", user);
                this.responseError(request, response, respuesta.mensaje, "crear-usuario", respuesta.esError());
            }
        } else {
            request.setAttribute("usuario", user);
            this.responseError(request, response, repuesta.mensaje, "crear-usuario", repuesta.esError());
        }
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
}
