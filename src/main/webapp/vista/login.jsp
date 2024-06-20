<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONObject" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.32/dist/sweetalert2.all.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.32/dist/sweetalert2.min.css">
        <title>Registro</title>
        <link rel="stylesheet" href="../css/login_resgistro.css">
    </head>
    <body>
        <div class="register-container">
            <h2>Login</h2>
            <form id="form" class="topBefore" action="../index.jsp" method="POST"  enctype="multipart/form-data">
                <div class="input-group">
                    <label for="usuario">Usuario</label>
                    <input type="text" id="usuario" name="usuario" placeholder="usuario o email@gmail.com" required>
                </div>
                <div class="input-group">
                    <label for="passwordUsuario">Contraseña</label>
                    <input type="password" id="password" name="password" placeholder="password"required>
                </div>
                <input id="submit" type="submit" class="btn" value="Ingresar"></input>
            </form>
            <p>¿No tienes cuenta? <a href="registro.jsp">Registrate aquí</a></p>
            <script src="../js/ingresarUsuario.js"></script>
        </div>
    </body>
</html>