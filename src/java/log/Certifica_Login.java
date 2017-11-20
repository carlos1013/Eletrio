package log;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter(filterName = "Certifica_Login", urlPatterns = {"/Tabelas","/selecionar.jsp","/Consultar_Completo","/Editar","/Inserir","/Remover"})
public class Certifica_Login implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
        HttpSession sessao = ((HttpServletRequest) request).getSession();
        Object logado = sessao.getAttribute("logado");
        if (logado != null) {
            String aux = (String) logado;
            if (logado.equals("true")) {
                chain.doFilter(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("areaBloqueada.jsp");
                rd.forward(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("areaBloqueada.jsp");
            rd.forward(request, response);
        
        }
        
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    
    }

    @Override
    public void destroy() {
    
    }
}
