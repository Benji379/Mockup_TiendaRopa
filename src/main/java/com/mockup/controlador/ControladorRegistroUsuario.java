package com.mockup.controlador;

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

@WebServlet("/ControladorRegistroUsuario")
public class ControladorRegistroUsuario extends HttpServlet {

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
                String correoUsuario = null;
                String passwordUsuario = null;
                String passwordUsuario2 = null;

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        switch (item.getFieldName()) {
                            case "nombreUsuario":
                                nombreUsuario = item.getString("UTF-8");
                                break;
                            case "correoUsuario":
                                correoUsuario = item.getString("UTF-8");
                                break;
                            case "passwordUsuario":
                                passwordUsuario = item.getString("UTF-8");
                                break;
                            case "passwordUsuario2":
                                passwordUsuario2 = item.getString("UTF-8");
                                break;
                        }
                    }
                }

                if (passwordUsuario2.equalsIgnoreCase(passwordUsuario)) {
                    if (nombreUsuario != null && correoUsuario != null && passwordUsuario != null) {
                        boolean valido1 = true, valido2 = true;
                        for (Usuario us : DaoUsuario.listaUsuarios()) {
                            String nombreUs = us.getNombreUsuario();
                            String correo = us.getCorreoUsuario();
                            if (nombreUs.equalsIgnoreCase(nombreUsuario)) {
                                valido1 = false;
                                jsonResponse.put("success", false);
                                jsonResponse.put("message", "El nombre de usuario ya existe.");
                            }
                            if (correo.equalsIgnoreCase(correoUsuario)) {
                                valido2 = false;
                                jsonResponse.put("success", false);
                                jsonResponse.put("message", "Correo en uso");
                            }
                        }
                        if (valido1 && valido2) {
                            String hashh = PasswordHasher.hashearPassword(passwordUsuario);
                            Usuario usuario = new Usuario(nombreUsuario, correoUsuario, hashh);
                            boolean registroExitoso = DaoUsuario.registrarUsuario(usuario);
                            if (registroExitoso) {
                                jsonResponse.put("success", true);
                                jsonResponse.put("message", "Usuario registrado exitosamente.");
                            } else {
                                jsonResponse.put("success", false);
                                jsonResponse.put("message", "Error al registrar usuario. Intente nuevamente.");
                            }
                        }
                    } else {
                        jsonResponse.put("success", false);
                        jsonResponse.put("message", "rellene todos los campos");
                    }
                } else {
                    jsonResponse.put("success", false);
                    jsonResponse.put("message", "Verificar password");
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
