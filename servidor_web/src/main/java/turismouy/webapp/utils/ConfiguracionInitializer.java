package turismouy.webapp.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfiguracionInitializer {
    public static void main(String[] args) {
        // Lógica para obtener las propiedades de configuración
        Configuracion configuracion = new Configuracion();

        // Genera el nuevo contenido para el pom.xml con las propiedades actualizadas
        String newPomContent = generarNuevoPom(configuracion);

        // Guarda el nuevo contenido en el archivo pom.xml
        guardarNuevoPom(newPomContent);
    }

    private static String generarNuevoPom(Configuracion configuracion) {
        // Lee el contenido del archivo pom.xml
        String pomContent;
        try {
            pomContent = new String(Files.readAllBytes(Paths.get("pom.xml")));
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo pom.xml", e);
        }

        // Reemplaza las propiedades con los valores de Configuracion
        pomContent = pomContent.replace("${server.ip}", configuracion.getIP());
        pomContent = pomContent.replace("${server.puerto}", configuracion.getPuerto());
        pomContent = pomContent.replace("${server.path}", configuracion.getPath());

        return pomContent;
    }

    private static void guardarNuevoPom(String newPomContent) {
        // Guarda el nuevo contenido en el archivo pom.xml
        try {
            Files.write(Paths.get("pom.xml"), newPomContent.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo pom.xml", e);
        }
    }
}
