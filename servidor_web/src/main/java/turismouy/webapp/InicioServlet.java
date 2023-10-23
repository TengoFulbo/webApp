package turismouy.webapp;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import turismouy.svcentral.EMFactory;

@WebListener
public class InicioServlet implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Inicia la conexión al iniciar la app.
        EMFactory.getEntityManagerFactory();
    }
}
