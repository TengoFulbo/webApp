package turismouy.webapp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import turismouy.svcentral.utilidades.log;

import java.io.InputStream;

public class Configuracion {
    Properties prop = new Properties();

    public Configuracion() {
        log.info(System.getProperty("user.home") + File.separator + ".turismouy" + File.separator + "config.properties");
        try {
            InputStream input = new FileInputStream(System.getProperty("user.home") + File.separator + ".turismouy" + File.separator + "config.properties");
            prop.load(input);

            // Establecer propiedades del sistema con los valores leídos
            System.setProperty("config.server.wsdlUrl", prop.getProperty("server.wsdlUrl"));
            System.setProperty("config.server.ip", prop.getProperty("server.ip"));
            System.setProperty("config.server.puerto", prop.getProperty("server.puerto"));
            // System.setProperty("config.server.pass", prop.getProperty("server.pass"));
            System.setProperty("config.server.path", prop.getProperty("server.path"));

            log.info("Ip del servidor: " + this.prop.getProperty("server.ip"));
            log.info("Puerto del servidor: " + this.prop.getProperty("server.puerto"));
            log.info("Contraseña: " + this.prop.getProperty("server.pass"));
            log.info("Path: " + this.prop.getProperty("server.path"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public String getIP() {
        return this.prop.getProperty("server.ip");
    };
    public String getPuerto() {
        return this.prop.getProperty("server.puerto");
    };
    public String getPass() {
        return this.prop.getProperty("server.pass");
    };
    public String getPath() {
        return this.prop.getProperty("server.path");
    }
}
