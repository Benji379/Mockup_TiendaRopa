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
            <h2>Registrarse</h2>
            <form id="form" class="topBefore" action="../index.jsp" method="POST"  enctype="multipart/form-data">

                <div class="input-group">
                    <label for="nombreUsuario">Nombre</label>
                    <input type="text" id="nombreUsuario" name="nombreUsuario" placeholder="usuario" required>
                </div>
                <div class="input-group">
                    <label for="correoUsuario">Correo Electrónico</label>
                    <input type="email" id="correoUsuario" name="correoUsuario" placeholder="email@gmail.com" required>
                </div>
                <div class="input-group">
                    <label for="passwordUsuario">Contraseña</label>
                    <input type="password" id="passwordUsuario" name="passwordUsuario" placeholder="password"required>
                </div>
                <div class="input-group">
                    <label for="passwordUsuario2">Confirmar contraseña</label>
                    <input type="password" id="passwordUsuario2" name="passwordUsuario2" placeholder="confirmar password"required>
                </div>
                <input id="submit" type="submit" class="btn" value="Registrate"></input>
            </form>
            <p>¿Ya tienes una cuenta? <a href="login.jsp">Iniciar sesión aquí</a></p>
            <script src="../js/registrarUsuario.js"></script>
        </div>
    </body>
</html>