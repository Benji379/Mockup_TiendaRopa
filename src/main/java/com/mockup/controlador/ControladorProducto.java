package com.mockup.controlador;

import com.mockup.dao.DaoProducto;
import com.mockup.modelo.Producto;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/ControladorProducto")
public class ControladorProducto extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        boolean registroExitoso = false;

        // Verificar si el formulario se envió como multipart/form-data (para la carga de archivos)
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            try {
                // Crear un FileItemFactory para manejar los items del formulario
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                // Parsear los items del formulario
                List<FileItem> items = upload.parseRequest(request);

                // Inicializar variables para guardar los datos del producto
                String nombreProducto = null;
                double precioProducto = 0.0;
                String descripcionProducto = null;
                InputStream fotoStream = null;

                // Iterar sobre los items del formulario
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        // Procesar campos de texto normales
                        if (item.getFieldName().equals("nombreProducto")) {
                            nombreProducto = item.getString("UTF-8");
                        } else if (item.getFieldName().equals("precioProducto")) {
                            try {
                                precioProducto = Double.parseDouble(item.getString());
                            } catch (NumberFormatException e) {
                                // Manejar error de conversión
                                System.out.println("ERROR: " + e.getMessage());
                            }
                        } else if (item.getFieldName().equals("descripcionProducto")) {
                            descripcionProducto = item.getString("UTF-8");
                        }
                    } else {
                        // Procesar el campo de archivo (foto)
                        fotoStream = item.getInputStream();
                    }
                }

                // Crear el objeto Producto con los datos recolectados
                Producto producto = new Producto(nombreProducto, precioProducto, descripcionProducto, fotoStream);

                // Llamar al método para registrar el producto en la base de datos
                registroExitoso = DaoProducto.registrarProducto(producto);

                // Preparar la respuesta JSON para enviar al cliente
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                if (registroExitoso) {
                    out.println("{ \"success\": true, \"message\": \"Registro Exitoso\" }");
                } else {
                    out.println("{ \"success\": false, \"message\": \"Hubo un problema al registrar el producto. Por favor, intenta nuevamente.\" }");
                }

            } catch (IOException | FileUploadException e) {
                System.out.println("ERROR: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                PrintWriter out = response.getWriter();
                out.println("{ \"success\": false, \"message\": \"Error en el servidor. Por favor, intenta nuevamente más tarde.\" }");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.println("{ \"success\": false, \"message\": \"El formulario no es de tipo multipart/form-data. Por favor, intenta nuevamente.\" }");
        }
    }
}
