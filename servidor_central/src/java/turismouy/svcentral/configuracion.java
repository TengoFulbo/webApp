package turismouy.svcentral;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class configuracion {
    // Obtén la carpeta de documentos del usuario
    private static final String CARPETA_DOCUMENTOS = System.getProperty("user.home") + File.separator + "Documentos";
    private static final String RUTA_ARCHIVO = CARPETA_DOCUMENTOS + File.separator + "jaxws.properties";

    public static void generarArchivoSiNoExiste() {
        File archivo = new File(RUTA_ARCHIVO);

        if (!archivo.exists()) {
            try (OutputStream output = new FileOutputStream(archivo)) {
                Properties propiedades = new Properties();
                // Agrega las propiedades necesarias al archivo
                propiedades.setProperty("endpoint.name", "TuServicioWeb");
                propiedades.setProperty("endpoint.implementation", "paquete.tu.paquete.TuClaseImplementacion");
                propiedades.setProperty("endpoint.url-pattern", "/rutadelwebservice");
                propiedades.store(output, null);
                System.out.println("Archivo generado: " + RUTA_ARCHIVO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El archivo ya existe: " + RUTA_ARCHIVO);
        }
    }

    public static Properties obtenerConfiguracion() {
        Properties propiedades = new Properties();
        try {
            // Carga las propiedades desde el archivo
            propiedades.load(configuracion.class.getClassLoader().getResourceAsStream(RUTA_ARCHIVO));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propiedades;
    }

    public static Properties obtenerConfiguracionConDefecto() {
        // En caso de error al obtener la configuración, devuelve un conjunto de propiedades vacío
        return new Properties();
    }

    // public static void main(String[] args) {
    //     generarArchivoSiNoExiste();
    //     Properties configuracion = obtenerConfiguracion();
    //     System.out.println("Configuración obtenida:\n" + configuracion);

    //     // Si hay un error al obtener la configuración, se utiliza un conjunto de propiedades vacío
    //     Properties configuracionConDefecto = obtenerConfiguracionConDefecto();
    //     System.out.println("Configuración con defecto:\n" + configuracionConDefecto);
    // }

    public static void ejecutar() {
        generarArchivoSiNoExiste();
        Properties configuracion = obtenerConfiguracion();
        System.out.println("Configuración obtenida:\n" + configuracion);

        // Si hay un error al obtener la configuración, se utiliza un conjunto de propiedades vacío
        Properties configuracionConDefecto = obtenerConfiguracionConDefecto();
        System.out.println("Configuración con defecto:\n" + configuracionConDefecto);
    };
}
