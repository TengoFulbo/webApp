package turismouy.webapp.auth;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Ejemplo:
// @WebFilter(urlPatterns = {"/pagina1.jsp", "/pagina2.jsp", "/pagina3.jsp", "/pagina4.jsp"})

@WebFilter(urlPatterns = {
                        "/resultado",
                        "/resultado2",
                    })
public class AuthFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            // Usuario autenticado, permite el acceso a las páginas protegidas
            chain.doFilter(request, response);
        } else {
            // Usuario no autenticado, redirige al formulario de inicio de sesión
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }
}