import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JOptionPane;

public class Quarto{
    Connection conexao = ConexaoBanco.geConnection();
    void Registrar () throws SQLException {
        var continuar = true;
        String pegarValor = "SELECT * FROM Hotel";
        
        PreparedStatement Stat = conexao.prepareStatement(pegarValor);
        ResultSet resultado = Stat.executeQuery();
        StringBuilder NomesHoteis = new StringBuilder();
        while (resultado.next()) {
            String idHotel = resultado.getString("id");
            String nomeHotel = resultado.getString("nome");
            NomesHoteis.append(idHotel).append(" - ").append(nomeHotel).append("\n");
        }
        var Verificar = NomesHoteis;
        if (Verificar.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não a hoteis cadastrados","Sem Hoteis", 1);
            continuar = false;
        }
        while (continuar) {
            try {
               
                var formatarNumero = "";
                var formatarIndice = "";
                while(true) {
                    var receberNumero = JOptionPane.showInputDialog(null, "Informe o Numero do Quarto: ", "Registrar novo Quarto",3);
                    if (receberNumero == null) {
                        continuar = false;
                        break;
                    }
                    formatarNumero = receberNumero.trim().replaceAll("\\s+", " ");
                    if (formatarNumero.equals("")) JOptionPane.showMessageDialog(null, "Erro: É necessario Digitar algo, Por favor tente novamente", "Valor Vazio", 0);
    
                    else if(formatarNumero.contains(" ")) JOptionPane.showMessageDialog(null, "Erro: Não suporta espaços entre palavras, Por favor tente novamente", "Espaço Adicionado", 0);
                     
                    else break;
                }
                if (!continuar) break; 
                
                while(true) {
                    var receberIndice = JOptionPane.showInputDialog(null, "Hoteis: \n"+ NomesHoteis + "Informe o indice do Hotel: ", "Selecionar Hotel",3);

                    if (receberIndice == null) {
                            continuar = false;
                            break;
                    }
                    formatarIndice = receberIndice.trim().replaceAll("\\s+", " ");
                    if (formatarIndice.equals("")) JOptionPane.showMessageDialog(null, "Erro: É necessario Digitar algo, Por favor tente novamente", "Valor Vazio", 0);
    
                    else if(formatarIndice.contains(" ")) JOptionPane.showMessageDialog(null, "Erro: Não suporta espaços entre palavras, Por favor tente novamente", "Espaço Adicionado", 0);

                    else break;
                }
                if (!continuar) break; 
                String PegarNome = "SELECT nome FROM hotel WHERE id = ?";
                PreparedStatement Consultar = conexao.prepareStatement(PegarNome);
                Consultar.setString(1, formatarIndice);
                ResultSet Valor = Consultar.executeQuery();

                if (Valor.next()) PegarNome = Valor.getString("nome");
                Random random = new Random();
                String codQuarto;
                while (true) {
                    codQuarto = "";
                    var cont = 0;
                    while (cont < 3) {
                        int randomNum = random.nextInt(122 - 48 + 1) + 48; 
                        var pegarChar = (char) randomNum;
                        if ((pegarChar >= '0' && pegarChar <= '9') ||
                            (pegarChar >= 'A' && pegarChar <= 'Z')
                        ) {
                            codQuarto += pegarChar;

                            cont += 1;
                        }
                    }  
                    String pegarCods = "SELECT codigo FROM Quarto";
                    PreparedStatement Conectar = conexao.prepareStatement(pegarCods);
                    ResultSet executar = Conectar.executeQuery();
                    var Repetido = false;
                    while (resultado.next()) {
                        String codigoDoBanco = executar.getString("codigo");
                        if (codigoDoBanco.equals(codQuarto)) Repetido = true;
                    }
                    if (Repetido == false) break;
                }
                String sql = "INSERT INTO Quarto (Codigo, Numero, hotel_nome) VALUES (?, ?, ?)";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, codQuarto);
                stmt.setString(2, formatarNumero);
                stmt.setString(3, PegarNome);
                stmt.execute(); 
                break;  
                }catch (Exception e) {
                    String mensagemErro = e.getMessage();
                    if (mensagemErro.contains("Duplicate entry")) {
                        JOptionPane.showMessageDialog(null, "Erro: O hotel ja possui um quarto com este nome, Por favor tente novamente", "Dado Repetido", 0);
                    } 
                    if (mensagemErro.contains("Cannot add or update a child row:")) {
                        JOptionPane.showMessageDialog(null, "Erro: Este Hotel não estar presente", "Valor Errado", 0);
                    }
                    if (mensagemErro.contains("Data truncation:")) {
                        JOptionPane.showMessageDialog(null, "Erro:  Caracteres acima do limite. Registre um número menor para o quarto.", "Numero do quarto grande", 0);
                    }
            }
        }
    }
    void Remover ()  throws SQLException {
        String ListarQuartosReservados = "SELECT * FROM Quarto Where Reservado = 'DISPONIVEL'";
        PreparedStatement Listar = conexao.prepareStatement(ListarQuartosReservados);
        ResultSet resultado = Listar.executeQuery();
        StringBuilder NomesQuartos = new StringBuilder();
        while (resultado.next()) {
            String codigoQuarto = resultado.getString("codigo");

            String nomeQuarto = resultado.getString("Numero");
            String nomeHotel = resultado.getString("hotel_nome");
            NomesQuartos.append(codigoQuarto + " - ").append("Quarto: (Nº" + nomeQuarto + ")").append(" Hotel: ").append(nomeHotel).append("\n\n");
            
        }
        var PegarIndice = "";
        if (NomesQuartos.isEmpty()) JOptionPane.showMessageDialog(null, "Sem Quartos Disponiveis", "Quartos", 1);
        
        else {
            JOptionPane.showMessageDialog(null, "AVISO: Apenas quartos Não reservados poderão ser Removidos", "Apenas Disponiveis", 2);
            PegarIndice = JOptionPane.showInputDialog(null," ".repeat(30) + "Quartos\n" + "-".repeat(60) + "\n" + NomesQuartos + "Informe o codigo do quarto", "Quartos", 1);
            if (PegarIndice != null) {
                var Format = PegarIndice.trim();
                var DeletarQuarto = "Delete from Quarto where codigo = ?";
                PreparedStatement Deletar = conexao.prepareStatement(DeletarQuarto);
                Deletar.setString(1, Format);
                Integer LinhasDeletadas = Deletar.executeUpdate();
                if (LinhasDeletadas.equals(1)) JOptionPane.showMessageDialog(null, "Quarto Deletado Com Sucesso");
                else  JOptionPane.showMessageDialog(null, "Erro: Valor Invalido", "Quarto Inexistente", 1);
            } 
        }
    }
    void Listar () throws SQLException {
        String ListarQuartos = "SELECT * FROM Quarto";
        PreparedStatement Exec = conexao.prepareStatement(ListarQuartos);
        ResultSet resultado = Exec.executeQuery();
        StringBuilder NomesQuartos = new StringBuilder();
        while (resultado.next()) {
            String nomeQuarto = resultado.getString("Numero");
            String nomeHotel = resultado.getString("hotel_nome");
            String Status = resultado.getString("Reservado");
            NomesQuartos.append("Quarto: (Nº").append(nomeQuarto).append(")").append(" Hotel: ").append(nomeHotel).append(" | Status: ").append(Status).append("\n\n");
        }
        if (NomesQuartos.isEmpty()) JOptionPane.showMessageDialog(null, "Sem Quartos Cadastrados", "Quartos", 1);
        
        else JOptionPane.showMessageDialog(null," ".repeat(30) + "Quartos\n" + "-".repeat(60) + "\n" + NomesQuartos, "Quartos", 1);
    }
}
