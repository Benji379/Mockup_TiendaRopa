package com.mockup.controlador;

import com.mockup.dao.DaoIngreso;
import com.mockup.dao.DaoUsuario;
import com.mockup.modelo.PasswordHasher;
import com.mockup.modelo.Usuario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ControladorIngreso")
public class ControladorIngreso extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        JSONObject jsonResponse = new JSONObject();

        if (isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List<FileItem> items = upload.parseRequest(request);
                String nombreUsuario = null;
                String password = null;

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        switch (item.getFieldName()) {
                            case "usuario":
                                nombreUsuario = item.getString("UTF-8");
                                break;
                            case "password":
                                password = item.getString("UTF-8");
                                break;
                        }
                    }
                }

                boolean credencialesCorrectas = false;
                int idUsuario = 0;
                for (Usuario us : DaoUsuario.listaUsuarios()) {
                    if (nombreUsuario.equalsIgnoreCase(us.getNombreUsuario()) || nombreUsuario.equalsIgnoreCase(us.getCorreoUsuario())) {
                        if (PasswordHasher.verificarPassword(password, us.getPassword())) {
                            credencialesCorrectas = true;
                            idUsuario = us.getIdUsuario();
                            break;
                        }
                    }
                }

                if (credencialesCorrectas) {
                    DaoIngreso.registrarIngreso(idUsuario);
                    jsonResponse.put("success", true);
                    jsonResponse.put("message", "Ingreso exitoso.");
//                    response.sendRedirect("index.jsp");
                } else {
                    jsonResponse.put("success", false);
                    jsonResponse.put("message", "Credenciales incorrectas.");
                }

            } catch (FileUploadException e) {
                System.out.println("Error: " + e.getMessage());
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Error al procesar el formulario. Intente nuevamente.");
            }
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "El formulario no es de tipo multipart/form-data.");
        }

        response.getWriter().write(jsonResponse.toString());
    }
}
