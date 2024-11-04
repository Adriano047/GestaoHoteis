
import java.sql.Connection;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Menu {
    CriarBanco banco = new CriarBanco();
    GerenciarDados Selecionar = new GerenciarDados();
    void Iniciar () throws SQLException {
    
        Connection conexao = ConexaoBanco.geConnection();
        banco.bancoCriado();
        String op[] = {"Hoteis", "Quartos", "Reservas"};
        while(true) {
            var Escolha = JOptionPane.showOptionDialog(null, "- Gerenciar Hoteis\n- Gerenciar Quartos\n- Gerenciar Reservas", "Hotel Overlook", 0, 3, null, op, op[0]);
            if (Escolha == 0) {
                Selecionar.ClasseProjeto("Hotel", "Hoteis", Escolha);
                
            }else if (Escolha == 1) {
                
                Selecionar.ClasseProjeto("Quarto", "Quartos", Escolha);
                
            }else if (Escolha == 2) {
                Selecionar.ClasseProjeto("Reserva", "Reservas", Escolha);
                
            }else {
                conexao.close();     
                break;
            }
        }
        
    }
}
