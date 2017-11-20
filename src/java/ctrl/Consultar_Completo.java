package ctrl;

import DAO.Administrador_DAO;
import DAO.Categoria_DAO;
import DAO.Clientes_DAO;
import DAO.Compras_DAO;
import DAO.Produto_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Consultar_Completo", urlPatterns = {"/Consultar_Completo"})
public class Consultar_Completo extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("tabela");
        switch(nome){
            case "ADMINISTRADOR":
            request.setAttribute("r",(new Administrador_DAO()).consultar());
                break;
            case "CATEGORIA":
                request.setAttribute("r",(new Categoria_DAO()).consultar());
                break;
            case "CLIENTES":
                request.setAttribute("r",(new Clientes_DAO()).consultar());
                break;
            case "COMPRAS":
                request.setAttribute("r",(new Compras_DAO()).consultar());
                break;
            case "PRODUTO":
                request.setAttribute("r",(new Produto_DAO()).consultar());
                break;
            default:
                //erro
        }
        request.getRequestDispatcher("selecionar.jsp").forward(request,response);
    }
}
