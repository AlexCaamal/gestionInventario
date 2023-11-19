
<%@page import="Modelo.usuario"%>
<%@page import="Modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-boxes' viewBox='0 0 16 16'%3E%3Cpath d='M7.752.066a.5.5 0 0 1 .496 0l3.75 2.143a.5.5 0 0 1 .252.434v3.995l3.498 2A.5.5 0 0 1 16 9.07v4.286a.5.5 0 0 1-.252.434l-3.75 2.143a.5.5 0 0 1-.496 0l-3.502-2-3.502 2.001a.5.5 0 0 1-.496 0l-3.75-2.143A.5.5 0 0 1 0 13.357V9.071a.5.5 0 0 1 .252-.434L3.75 6.638V2.643a.5.5 0 0 1 .252-.434L7.752.066ZM4.25 7.504 1.508 9.071l2.742 1.567 2.742-1.567L4.25 7.504ZM7.5 9.933l-2.75 1.571v3.134l2.75-1.571V9.933Zm1 3.134 2.75 1.571v-3.134L8.5 9.933v3.134Zm.508-3.996 2.742 1.567 2.742-1.567-2.742-1.567-2.742 1.567Zm2.242-2.433V3.504L8.5 5.076V8.21l2.75-1.572ZM7.5 8.21V5.076L4.75 3.504v3.134L7.5 8.21ZM5.258 2.643 8 4.21l2.742-1.567L8 1.076 5.258 2.643ZM15 9.933l-2.75 1.571v3.134L15 13.067V9.933ZM3.75 14.638v-3.134L1 9.933v3.134l2.75 1.571Z'/%3E%3C/svg%3E" type="image/svg+xml">
        <link href="./style/main-users.css" rel="stylesheet" crossorigin="anonymous">
        <link rel="icon" href="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-boxes' viewBox='0 0 16 16'%3E%3Cpath d='M7.752.066a.5.5 0 0 1 .496 0l3.75 2.143a.5.5 0 0 1 .252.434v3.995l3.498 2A.5.5 0 0 1 16 9.07v4.286a.5.5 0 0 1-.252.434l-3.75 2.143a.5.5 0 0 1-.496 0l-3.502-2-3.502 2.001a.5.5 0 0 1-.496 0l-3.75-2.143A.5.5 0 0 1 0 13.357V9.071a.5.5 0 0 1 .252-.434L3.75 6.638V2.643a.5.5 0 0 1 .252-.434L7.752.066ZM4.25 7.504 1.508 9.071l2.742 1.567 2.742-1.567L4.25 7.504ZM7.5 9.933l-2.75 1.571v3.134l2.75-1.571V9.933Zm1 3.134 2.75 1.571v-3.134L8.5 9.933v3.134Zm.508-3.996 2.742 1.567 2.742-1.567-2.742-1.567-2.742 1.567Zm2.242-2.433V3.504L8.5 5.076V8.21l2.75-1.572ZM7.5 8.21V5.076L4.75 3.504v3.134L7.5 8.21ZM5.258 2.643 8 4.21l2.742-1.567L8 1.076 5.258 2.643ZM15 9.933l-2.75 1.571v3.134L15 13.067V9.933ZM3.75 14.638v-3.134L1 9.933v3.134l2.75 1.571Z'/%3E%3C/svg%3E" type="image/svg+xml">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nueva Cuenta</title>
    </head>
    <body>
        <header>
            <div class="row justify-content-between align-items-center">
                <div class="col" style="justify-content: left; text-align: left; margin-left: 1%;">
                    <h1>Crear Cuenta</h1>
                </div>
                <div style="margin-right: 1%; width: 150px;">
                    <a href="ControladorDashBoard?accion=logout" class="btn btn-outline-danger">Cancelar</a>
                </div>
            </div>
        </header>

        <div class="container" style="height: 100vh; text-align: center; justify-content: center; display: flex;">

            <%
                Boolean esError = (Boolean) request.getAttribute("esError");
                String mensajeError = (String) request.getAttribute("mensajeErrorBD");
                usuario user = (usuario) request.getAttribute("usuario");

                if (esError != null && mensajeError != "" && esError) {
            %>
            <div class="alert alert-danger" role="alert" style="position: absolute; top: 0; left: 50%; transform: translateX(-50%);">
                <%=mensajeError%>
            </div>
            <%} else {%>

            <div class="alert alert-danger" role="alert" style="display: none;">
                A simple danger alert—check it out!
            </div>
            <%}%>
            <form action="ControladorMain" method="POST">

                <label for="nombres">Nombres:</label>
                <input type="text" id="nombres" name="nombres" required value="<%=user.getNombres() == null ? "" : user.getNombres()%>">

                <label for="apellidos">Apellidos:</label>
                <input type="text" id="apellidos" name="apellidos" value="<%=user.getApellidos() == null ? "" : user.getApellidos()%>">

                <label for="telefonos">Teléfonos:</label>
                <input type="text" id="telefonos" name="telefono" required value="<%=user.getTelefonos() == null ? "" : user.getTelefonos()%>" maxlength="10">

                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" required value="<%=user.getUsuario() == null ? "" : user.getUsuario()%>">

                <label for="contrasena">Contraseña:</label>
                <input type="password" id="contrasena" name="contrasena" required value="<%=user.getContraseña() == null ? "" : user.getContraseña()%>">
                <br><br>

                <input type="submit" name="accion" value="Crear" class="btn btn-outline-success">
            </form>
        </div>
        <footer>
            <p>* Juan Armando Carranza Madagan * Erick Armando Díaz Jimenez</p>

        </footer>
    </body>

</html>
