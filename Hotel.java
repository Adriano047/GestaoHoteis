import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

public class Hotel  {
    Connection conexao = ConexaoBanco.geConnection();

    void Registrar () throws SQLException {
        while(true) {
           try {
            
            var InformarNome = JOptionPane.showInputDialog(null, "Informe O nome Do Hotel: ", "Registrar novo hotel",3);
            if (InformarNome == null) {
                break;
            }
            String NomeHotel = InformarNome.trim().replaceAll("\\s+", " ");
            if (NomeHotel.equals("")) {
                NomeHotel = null;
            }
            String sql = "INSERT INTO hotel (nome) VALUES (?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, NomeHotel);
            stmt.execute(); 
            JOptionPane.showMessageDialog(null, "Adicionado com sucesso");
            break;
        } catch (SQLIntegrityConstraintViolationException e) {
            String mensagemErro = e.getMessage();
            if (mensagemErro.contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Erro: Nome Já utilizado, Por favor tente novamente", "Dado Repetido", 0);
            } 
            if (mensagemErro.contains("cannot be null")) {
                JOptionPane.showMessageDialog(null, "Erro: É necessario Digitar algo, Por favor tente novamente", "Valor Vazio", 0);
            }
        }  
        }
    }

    void Remover () throws SQLException {
        String ListarHoteis = "SELECT * FROM Hotel";
        PreparedStatement Exec = conexao.prepareStatement(ListarHoteis);
        ResultSet resultado = Exec.executeQuery();
        StringBuilder NomesHoteis = new StringBuilder();
        while (resultado.next()) {
            String idHotel = resultado.getString("id");

            String nomeHotel = resultado.getString("nome");
            NomesHoteis.append("id - " + idHotel).append(" Nome: ").append(nomeHotel).append("\n");
        }
        if (NomesHoteis.isEmpty()) JOptionPane.showMessageDialog(null, "Sem Hoteis Cadastrados", "Hoteis", 1);
        
        else {
            var Valor = JOptionPane.showInputDialog(null," ".repeat(10) + "Hoteis\n" + "-".repeat(30) + "\n" + NomesHoteis + "\nInforme a id do hotel para deleta-lo:", "Hoteis", 1);
            if (Valor != null) {
                String Cont[] = {"Sim", "Não"};
                var Continuar = JOptionPane.showOptionDialog(null, "AVISO: Ao excluir um hotel,todas as reservas e quartos\n associados a ele serão removidos.\n Quer Continuar?", "Deletar tudo", 0, 2, null, Cont, Cont[0]);
                if (Continuar == 0) {
                    var HotelEscolhido = "SELECT * FROM Hotel where id = ?";
                    PreparedStatement preparar = conexao.prepareStatement(HotelEscolhido);
                    preparar.setString(1, Valor.trim());
                    ResultSet pegarResultado = preparar.executeQuery();
                    if (pegarResultado.next()) {
                        String NomeHotel = pegarResultado.getString("nome");
                        
                        // Apagando Hotel
                        var ApagarHotel = "DELETE FROM hotel where nome = ?";
                        PreparedStatement apagarFinal = conexao.prepareStatement(ApagarHotel);
                        apagarFinal.setString(1, NomeHotel);
                        apagarFinal.execute();

                        JOptionPane.showMessageDialog(null, "Hotel removido com sucesso");
                    } 
                    else JOptionPane.showMessageDialog(null, "Hotel não encontrado", "Valor Invalido", 0);
                }
            }
        }   
    }   

    void Listar () throws SQLException{
        String ListarHoteis = "SELECT * FROM Hotel";
        
        PreparedStatement Exec = conexao.prepareStatement(ListarHoteis);
        ResultSet resultado = Exec.executeQuery();
        StringBuilder NomesHoteis = new StringBuilder();
        while (resultado.next()) {
            String nomeHotel = resultado.getString("nome");
            NomesHoteis.append("   Nome: ").append(nomeHotel).append("\n");

            String numero = "select * from (quarto) where hotel_nome = ?";
            PreparedStatement prep = conexao.prepareStatement(numero);
            prep.setString(1, nomeHotel);
            ResultSet exe = prep.executeQuery();
            var semQuarto = true;
            while (exe.next()) {
                String NumeroQuarto = exe.getString("Numero");
                semQuarto = false;
                NomesHoteis.append("       - Quarto: (nº ").append(NumeroQuarto).append(")").append("\n");
            }
            if (semQuarto == true) NomesHoteis.append("       Não Há Quartos\n");
            NomesHoteis.append("\n");
        }
        if (NomesHoteis.isEmpty()) JOptionPane.showMessageDialog(null, "Sem Hoteis Cadastrados", "Hoteis", 1);
        
        else JOptionPane.showMessageDialog(null," ".repeat(10) + "Hoteis\n" + "-".repeat(30) + "\n" + NomesHoteis, "Hoteis", 1);
    }
}
