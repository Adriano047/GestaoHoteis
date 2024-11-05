import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JOptionPane;

public class Reserva  {
    Connection conexao = ConexaoBanco.geConnection();
    void Registrar () throws SQLException {
        
        String pegarValor = "SELECT * FROM Hotel";
        PreparedStatement Stat = conexao.prepareStatement(pegarValor);
        ResultSet resultado = Stat.executeQuery();
        StringBuilder NomesHoteis = new StringBuilder();
        while (resultado.next()) {
            String idHotel = resultado.getString("id");

            String nomeHotel = resultado.getString("nome");
            NomesHoteis.append(idHotel).append(" - ").append(nomeHotel).append("\n");
        }
        var continuar = true;
        var Verificar = NomesHoteis.toString();
        if (Verificar.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não a hoteis cadastrados","Sem Hoteis", 1);
            continuar = false;
        }
        while (continuar) {
        try {
            var formatarHotel = "";
            var formatarNumero = "";
            while(formatarHotel.equals("")) {
                var receberHotel = JOptionPane.showInputDialog("Hoteis: \n"+ NomesHoteis + "Informe o indice do Hotel: ");
                if (receberHotel == null) {
                    continuar = false;
                    break;
                }
                formatarHotel = receberHotel.trim();
                if (formatarHotel.equals("")) JOptionPane.showMessageDialog(null, "Erro: É necessario Digitar algo, Por favor tente novamente", "Valor Vazio", 0);
            }
            if (!continuar) break;
            String PegarHotelValor = "SELECT nome FROM hotel WHERE id = ?";
            PreparedStatement Con = conexao.prepareStatement(PegarHotelValor);
            Con.setString(1, formatarHotel);
            ResultSet Valor = Con.executeQuery();

            if (Valor.next()) PegarHotelValor = Valor.getString("nome");
            String numero = "select * from (quarto) where hotel_nome = (?) AND Reservado = 'DISPONIVEL'";
            PreparedStatement prep = conexao.prepareStatement(numero);
            prep.setString(1, PegarHotelValor);
            ResultSet exe = prep.executeQuery();
            StringBuilder QuartoNumeros = new StringBuilder();
            while (exe.next()) {
                String NumeroQuarto = exe.getString("Numero");

                QuartoNumeros.append("nº ").append(NumeroQuarto).append("\n");
            }
            if (QuartoNumeros.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Este hotel não possui quarto disponiveis", "Não Há Vagas", 1);
            } else {
                while(formatarNumero.equals("")) {
                var receberNumero = JOptionPane.showInputDialog("Quartos: \n" + QuartoNumeros + "Informe o numero do quarto: ");
                if (receberNumero == null) {
                    continuar = false;
                    break;
                }
               
                formatarNumero = receberNumero.trim();
                var Format = QuartoNumeros.toString();
                if (formatarNumero.equals("")) JOptionPane.showMessageDialog(null, "Erro: É necessario Digitar algo, Por favor tente novamente", "Valor Vazio", 0);
                else if (!Format.contains(formatarNumero))  JOptionPane.showMessageDialog(null, "Erro: Informe um Quarto Valido", "Valor Invalido", 0);
                }
                if (!continuar) break;
                String PegarHotel = "SELECT nome FROM hotel WHERE id = ?";
                PreparedStatement Consultar = conexao.prepareStatement(PegarHotel);
                Consultar.setString(1, formatarHotel);
                ResultSet result = Consultar.executeQuery();

                if (result.next()) PegarHotel = result.getString("nome");
                Random random = new Random();
                String codReserva;
                while (true) {
                    codReserva = "";
                    var cont = 0;
                    while (cont < 3) {
                        int randomNum = random.nextInt(122 - 48 + 1) + 48; 
                        var pegarChar = (char) randomNum;
                        if ((pegarChar >= '0' && pegarChar <= '9') ||
                            (pegarChar >= 'A' && pegarChar <= 'Z')
                        ) {
                            codReserva += pegarChar;

                            cont += 1;
                        }
                    }  
                    String pegarCods = "SELECT codigo FROM Reserva";
                    PreparedStatement Conectar = conexao.prepareStatement(pegarCods);
                    ResultSet executar = Conectar.executeQuery();
                    var Repetido = false;
                    while (resultado.next()) {
                        String codigoDoBanco = executar.getString("codigo");
                        if (codigoDoBanco.equals(codReserva)) Repetido = true;
                    }
                    if (Repetido == false) break;
                }
                
                String sql = "INSERT INTO Reserva (Codigo, hotel_nome, Quarto_Num) VALUES (?, ?, ?)";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, codReserva);
                stmt.setString(2, PegarHotel);
                stmt.setString(3, formatarNumero);
                stmt.execute(); 
                
                String Update = "UPDATE Quarto SET Reservado = ? WHERE Numero = ? AND hotel_nome = ?";
                PreparedStatement Up = conexao.prepareStatement(Update);
                Up.setString(1, "RESERVADO");
                Up.setString(2, formatarNumero);
                Up.setString(3, PegarHotel);
                Up.execute(); 
                JOptionPane.showMessageDialog(null, "Valores Insiridos Com Sucesso");
                
            
            break; 
        }
        }catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }
    }
    void Remover ()  throws SQLException  {
        String ListarReservas = "SELECT * FROM Reserva";
        PreparedStatement Exec = conexao.prepareStatement(ListarReservas);
        ResultSet resultado = Exec.executeQuery();
        StringBuilder NomeReservas = new StringBuilder();
        while (resultado.next()) {
            String reservaCod = resultado.getString("codigo");

            String HotelNome = resultado.getString("hotel_nome");
            String QuartoNumero = resultado.getString("Quarto_Num");
            NomeReservas.append(reservaCod + " - ").append("Hotel: " + HotelNome).append(" (Nº" + QuartoNumero + ")\n\n");
        } 
        if (NomeReservas.isEmpty()) JOptionPane.showMessageDialog(null, "Sem Reservas Registradas", "Reservas", 1);
       
        else {
            var EscolherValor = JOptionPane.showInputDialog(null," ".repeat(10) + "Reservas\n" + "-".repeat(30) + "\n" + NomeReservas + "Informe o Codigo Para remoção:", "reservas", 1);
            if (EscolherValor != null) {
                var Format = EscolherValor.trim();
                String ValorDeletado = "select * from reserva where codigo = ?";
                PreparedStatement Executar = conexao.prepareStatement(ValorDeletado);
                Executar.setString(1, Format);
                ResultSet result = Executar.executeQuery();
                String NumeroQuarto = "";
                String NomeHotel = "";
                while (result.next()) {
                    NumeroQuarto = result.getString("Quarto_num");
                    NomeHotel = result.getString("hotel_nome");
                }
                String UpdateQuarto = "UPDATE Quarto SET Reservado = ? WHERE Numero = ? AND hotel_nome = ?";
                PreparedStatement Up = conexao.prepareStatement(UpdateQuarto);
                Up.setString(1, "DISPONIVEL");
                Up.setString(2, NumeroQuarto);
                Up.setString(3, NomeHotel);
                Up.execute(); 

                String Delete = "delete from reserva where codigo = ?";
                PreparedStatement Deletar = conexao.prepareStatement(Delete);
                Deletar.setString(1, Format);
                Integer LinhasDeletadas = Deletar.executeUpdate();
                if (LinhasDeletadas.equals(1)) JOptionPane.showMessageDialog(null, "Reserva Deletada Com Sucesso");
                else  JOptionPane.showMessageDialog(null, "Erro: Valor Invalido", "Quarto Inexistente", 0);
                
            }
            
        }
        
    }

    void Listar () throws SQLException {
        String ListarReservas = "SELECT * FROM Reserva";
        PreparedStatement Exec = conexao.prepareStatement(ListarReservas);
        ResultSet resultado = Exec.executeQuery();
        StringBuilder NomeReservas = new StringBuilder();
        while (resultado.next()) {
            String HotelNome = resultado.getString("hotel_nome");
            String QuartoNumero = resultado.getString("Quarto_Num");
            NomeReservas.append("Hotel: ").append(HotelNome).append(" (Nº").append(QuartoNumero).append(")").append("\n\n");
        }
        if (NomeReservas.isEmpty()) JOptionPane.showMessageDialog(null, "Sem Reservas Registradas", "Reservas", 1);
        
        else JOptionPane.showMessageDialog(null," ".repeat(10) + "Reservas\n" + "-".repeat(30) + "\n" + NomeReservas, "reservas", 1);
    }
}
