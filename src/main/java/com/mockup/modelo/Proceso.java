package com.mockup.modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;

public class Proceso {

    public static Timestamp getDateTime() {
        // Obtiene el instante actual
        Instant now = Instant.now();
        // Convierte el instante en un Timestamp y lo retorna
        return Timestamp.from(now);
    }

    public static String getIp() {
        try {
            URL url = new URL("http://checkip.amazonaws.com");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String ipAddress = br.readLine().trim();
            System.out.println("Dirección IP pública: " + ipAddress);
            return ipAddress;
        } catch (MalformedURLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return null;
    }

}
