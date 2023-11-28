package turismouy.webapp;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import turismouy.webapp.utils.log;

// import javax.servlet.annotation.WebListener;

@WebListener
public class MainServlet implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Este método se llama cuando la aplicación se inicia
        log.info("HOOOOOOOOOOOOOOOOOOOOOOOOLA");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Este método se llama cuando la aplicación se detiene
        log.info("ADIOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
    }
}