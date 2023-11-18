<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-boxes' viewBox='0 0 16 16'%3E%3Cpath d='M7.752.066a.5.5 0 0 1 .496 0l3.75 2.143a.5.5 0 0 1 .252.434v3.995l3.498 2A.5.5 0 0 1 16 9.07v4.286a.5.5 0 0 1-.252.434l-3.75 2.143a.5.5 0 0 1-.496 0l-3.502-2-3.502 2.001a.5.5 0 0 1-.496 0l-3.75-2.143A.5.5 0 0 1 0 13.357V9.071a.5.5 0 0 1 .252-.434L3.75 6.638V2.643a.5.5 0 0 1 .252-.434L7.752.066ZM4.25 7.504 1.508 9.071l2.742 1.567 2.742-1.567L4.25 7.504ZM7.5 9.933l-2.75 1.571v3.134l2.75-1.571V9.933Zm1 3.134 2.75 1.571v-3.134L8.5 9.933v3.134Zm.508-3.996 2.742 1.567 2.742-1.567-2.742-1.567-2.742 1.567Zm2.242-2.433V3.504L8.5 5.076V8.21l2.75-1.572ZM7.5 8.21V5.076L4.75 3.504v3.134L7.5 8.21ZM5.258 2.643 8 4.21l2.742-1.567L8 1.076 5.258 2.643ZM15 9.933l-2.75 1.571v3.134L15 13.067V9.933ZM3.75 14.638v-3.134L1 9.933v3.134l2.75 1.571Z'/%3E%3C/svg%3E" type="image/svg+xml">
        <link href="./style/styles.css" rel="stylesheet" crossorigin="anonymous">
        <link rel="icon" href="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-boxes' viewBox='0 0 16 16'%3E%3Cpath d='M7.752.066a.5.5 0 0 1 .496 0l3.75 2.143a.5.5 0 0 1 .252.434v3.995l3.498 2A.5.5 0 0 1 16 9.07v4.286a.5.5 0 0 1-.252.434l-3.75 2.143a.5.5 0 0 1-.496 0l-3.502-2-3.502 2.001a.5.5 0 0 1-.496 0l-3.75-2.143A.5.5 0 0 1 0 13.357V9.071a.5.5 0 0 1 .252-.434L3.75 6.638V2.643a.5.5 0 0 1 .252-.434L7.752.066ZM4.25 7.504 1.508 9.071l2.742 1.567 2.742-1.567L4.25 7.504ZM7.5 9.933l-2.75 1.571v3.134l2.75-1.571V9.933Zm1 3.134 2.75 1.571v-3.134L8.5 9.933v3.134Zm.508-3.996 2.742 1.567 2.742-1.567-2.742-1.567-2.742 1.567Zm2.242-2.433V3.504L8.5 5.076V8.21l2.75-1.572ZM7.5 8.21V5.076L4.75 3.504v3.134L7.5 8.21ZM5.258 2.643 8 4.21l2.742-1.567L8 1.076 5.258 2.643ZM15 9.933l-2.75 1.571v3.134L15 13.067V9.933ZM3.75 14.638v-3.134L1 9.933v3.134l2.75 1.571Z'/%3E%3C/svg%3E" type="image/svg+xml">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <title>
            Inventario de Productos
        </title>

    </head>
    <body style="background-color: #333;">
        <div class="container" style="margin-top: 8%; width: 500px; text-align:  center;">
            <form class=" form-sing" action="ControladorMain" method="POST">
                <div class="form-group text-center" style="color: white; justify-content: center; width: 100%">
                    <h1>Login</h1>
                    <h2>Bienvenido</h3>
                </div>
                <br>
                <div class="row mb-3">
                    <label for="inputEmail3" class="col-sm-2 col-form-label" style="color: white">Usuario</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputUsuario" name="inputUsuario">
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="inputPassword3" class="col-sm-2 col-form-label" style="color: white">Password</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPassword" name="inputPassword">
                    </div>
                </div>
                <%
                    String mensajeError = (String) request.getAttribute("mensajeError");
                    if (mensajeError != null && !mensajeError.isEmpty()) {
                %>
                <div style="color: red;">
                    <%= mensajeError%>
                </div>
                <%
                    }
                %>


                <div style="text-align: right;"> <input type="submit" name="accion" class="btn btn-primary" value="Ingresar" style="height: 50px; width: 100px"></div>
            </form>
        </div>
        <footer>
            <p><li> Juan Armando Carranza Madagan <li> Erick Armando Díaz Jimenez <br> &copy; 2023 Inventario de Productos</p>

        </footer>
    </body>
</html>
