<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONObject" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Producto</title>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.32/dist/sweetalert2.all.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.32/dist/sweetalert2.min.css">
        <link rel="stylesheet" href="../css/registerProducto.css"/>
    </head>
    <body>
        <header style="margin-left: 50px">REGISTRAR PRODUCTO</header>
        <form id="form" class="topBefore" action="" method="post" enctype="multipart/form-data">
            <!-- Campos del formulario -->
            <input id="nombreProducto" name="nombreProducto" type="text" placeholder="Nombre Producto">
            <input id="precioProducto" name="precioProducto" type="number" placeholder="Precio Producto">
            <textarea id="descripcionProducto" name="descripcionProducto" placeholder="Descripción de Producto"></textarea>
            <input id="imagen" name="imagen" type="file" accept="image/*" class="file-input">
            <input id="submit" type="submit" value="Registrar">
        </form>
        <script src="../js/productos.js"></script>

    </body>
</html>
