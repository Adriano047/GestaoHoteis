import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco { 
    private static Connection conexao = null;
    private static final String url = "jdbc:mysql://localhost?verifyServerCertificate=false&useSSL=true";
    private static final String usuario = "root";
    private static final String senha = "root";
    private ConexaoBanco() {}
    public static Connection geConnection() {
        if (conexao == null) {
             try {
        // adicionar nos parametro de Criar a url,usuario e senha;
            conexao = DriverManager.getConnection(url, usuario, senha);
     
        }catch (SQLException e)  {
            System.out.println("Ocorreu algum erro!!" + e.getMessage());
        }
        }
        return conexao;
    }
    }
