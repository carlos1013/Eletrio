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


@WebServlet(name = "Editar", urlPatterns = {"/Editar"})
public class Editar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nome = request.getParameter("tabela");
            switch(nome){
                case "ADMINISTRADOR":
                    (new Administrador_DAO()).editar((new Administrador(Integer.parseInt(request.getParameter("id")),request.getParameter("LOGIN"),request.getParameter("SENHA"))));
                    break;
                case "CATEGORIA":
                    (new Categoria_DAO()).editar((new Categoria(Integer.parseInt(request.getParameter("id")),request.getParameter("DESCRICAO"))));
                    break;
                case "CLIENTES":
                    (new Clientes_DAO()).editar((new Clientes(Integer.parseInt(request.getParameter("id")),request.getParameter("NOME"),request.getParameter("ENDERECO"),request.getParameter("REFERENCIA"),request.getParameter("BAIRRO"),request.getParameter("CIDADE"),request.getParameter("CEP"),request.getParameter("ESTADO"),request.getParameter("CPF"),request.getParameter("IDENTIDADE"),request.getParameter("FIXO"),request.getParameter("CELULAR"),request.getParameter("CARTAO"),request.getParameter("BANDEIRA"))));
                    break;
                case "COMPRAS":
                    (new Compras_DAO()).editar((new Compras(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("ID_CLIENTE")),Integer.parseInt(request.getParameter("ID_PRODUTO")))));
                    break;
                case "PRODUTO":
                    (new Produto_DAO()).editar((new Produto(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("ID_CATEGORIA")),request.getParameter("NOME"),request.getParameter("DESCRICAO"),Float.parseFloat(request.getParameter("VALOR")))));
                    break;
                default:
                    //erro
            }
        } 
        catch (Exception ex) {
            Logger.getLogger(Editar.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("selecionar.jsp").forward(request,response);
    }
}
