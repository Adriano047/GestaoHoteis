import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco { 
    private static Connection conexao = null;
    // A variável url se conecta com a porta padrão 3306 caso que usar outra porta mude o valor;
    // o valor das variáveis usuario e senha irá de acordo com o que foi definido por você;
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
            String mensagemErro = e.getMessage();
            if (mensagemErro.contains("Access denied for user")) {
                System.out.println("Não estar sendo possivel acessar o usuario: " + usuario + " Verifique se o usuario e senha estão corretos.");
            } 
            
        }
        }
        return conexao;
    }
    }
