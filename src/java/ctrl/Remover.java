package ctrl;

import DAO.Administrador;
import DAO.Administrador_DAO;
import DAO.Categoria;
import DAO.Categoria_DAO;
import DAO.Clientes;
import DAO.Clientes_DAO;
import DAO.Compras;
import DAO.Compras_DAO;
import DAO.Produto;
import DAO.Produto_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Remover", urlPatterns = {"/Remover"})
public class Remover extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nome = request.getParameter("tabela");
            switch(nome){
                case "ADMINISTRADOR":
                    (new Administrador_DAO()).remover((new Administrador(Integer.parseInt(request.getParameter("id")))));
                    break;
                case "CATEGORIA":
                    (new Categoria_DAO()).remover((new Categoria(Integer.parseInt(request.getParameter("id")))));
                    break;
                case "CLIENTES":
                    (new Clientes_DAO()).remover((new Clientes(Integer.parseInt(request.getParameter("id")))));
                    break;
                case "COMPRAS":
                    (new Compras_DAO()).remover((new Compras(Integer.parseInt(request.getParameter("id")))));
                    break;
                case "PRODUTO":
                    (new Produto_DAO()).remover((new Produto(Integer.parseInt(request.getParameter("id")))));
                    break;
                default:
                    //erro
            }
        } 
        catch (Exception ex) {
            request.getRequestDispatcher("erroBD.jsp").forward(request,response);
        }
        request.getRequestDispatcher("selecionar.jsp").forward(request,response);
    }
}
