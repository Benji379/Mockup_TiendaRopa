<%@page import="java.util.Base64"%>
<%@page import="com.mockup.dao.DaoProducto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.SQLException, java.sql.Statement, java.sql.ResultSet" %>
<%@page import="com.mockup.dao.ConexionBD, com.mockup.modelo.Producto" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="css/cards.css"/>
        <title>Mockup</title>
    </head>
    <body>
        <header>
            <div class="container">
                <div class="logo">
                    <a class="itemm" href="#">Tienda de Ropa</a>
                </div>
                <nav style="margin-top: 15px">
                    <ul>
                        <li><a href="#">Hombres</a></li>
                        <li><a href="#">Mujeres</a></li>
                        <li><a href="#">Niños</a></li>
                        <li><a href="#">Ofertas</a></li>
                    </ul>
                </nav>
                <div class="header-icons">
                    <form class="search-form" action="#" method="GET">
                        <input type="text" placeholder="Buscar..." name="search">
                        <button type="submit"><i class="fas fa-search"></i></button>

                        <a href="#" class="cart-icon"><i class="fas fa-shopping-cart"></i> Carrito</a>
                        <a href="vista/login.jsp" class="login-icon"><i class="fas fa-user"></i> Login</a></form>
                </div>
            </div>
        </header>

        <!-- Banner Principal -->
        <section class="banner">
            <div class="container1">
                <h1>Nueva Colección de Verano</h1><br>
                <p>¡Hasta 50% de Descuento!</p>
                <a href="#" class="btn">Comprar Ahora</a>
            </div>
        </section>

        <!-- Secciones Destacadas -->
        <section class="featured">
            <div class="container1">
                <h2>Nuevas Llegadas</h2>
            </div>
            <div class="container-products" bis_skin_checked="1">
                <%
//                    Connection co = new ConexionBD().conexion();
//                    if (co != null) {
////                        out.print("SE CONECTO GAAAAAA");
//                    }
                    for (Producto p : DaoProducto.listaProductos()) {
                        out.print("<div class=\"card-product\">");
                        out.print("<div class=\"container-img\">");
                        // Convertir byte[] a base64
                        String base64Image = Base64.getEncoder().encodeToString(p.getFoto().readAllBytes());
                        out.print("<img src=\"data:image/png;base64," + base64Image + "\" alt=\"" + p.getNombreProducto() + "\">");
                        out.print("</div>");
                        out.print("<div class=\"content-card-product\">");
                        out.print("<h3>" + p.getNombreProducto() + "</h3>");
                        out.print("<p class=\"price\">S/" + p.getPrecioProducto() + "</p>");
                        out.print("</div>");
                        out.print("</div>");
                    }

                %>

            </div>

        </section>
        <!-- Footer -->
        <footer>
            <div class="container1">
                <div class="footer-info">
                    <p>&copy; 2024 Tienda de Ropa. Todos los derechos reservados.</p>
                    <ul>
                        <li><a href="#">Términos y Condiciones</a></li>
                        <li><a href="#">Política de Privacidad</a></li>
                    </ul>
                </div>
            </div>
        </footer>

    </body>
</html>
