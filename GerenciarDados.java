import java.sql.SQLException;

import javax.swing.JOptionPane;

public class GerenciarDados {
    Hotel hotel = new Hotel();
    Quarto quarto = new Quarto();
    Reserva reserva = new Reserva();
    String gerenciar[] = {"Registar", "Remover", "Listar"};
    void ClasseProjeto(String conteudoJoin, String TituloJoin,Integer IntaciaEscolhida) throws SQLException {
        while(true) {
            var Use = JOptionPane.showOptionDialog(null, "- Registrar " + conteudoJoin + "\n- Remover " + conteudoJoin + "\n- Listar " + TituloJoin, "Gerenciar " + TituloJoin, 0, 3, null, gerenciar, gerenciar[0]);
            if (Use == 0) {
                if (IntaciaEscolhida == 0) {
                    hotel.Registrar();
                }else if (IntaciaEscolhida == 1) {
                    quarto.Registrar();
                } else {
                    reserva.Registrar();
                }
            }else if (Use == 1) {
                if (IntaciaEscolhida == 0) {
                    hotel.Remover();
                }else if (IntaciaEscolhida == 1) {
                    quarto.Remover();
                } else {
                    reserva.Remover();
                }
            }else if (Use == 2) {
                if (IntaciaEscolhida == 0) {
                    hotel.Listar();
                }else if (IntaciaEscolhida == 1) {
                    quarto.Listar();
                } else {
                    reserva.Listar();
                }
            }else {
                break;
            }
        }
    }
}
