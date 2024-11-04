import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarBanco {
    void bancoCriado() throws SQLException  {
        Connection conexao = ConexaoBanco.geConnection();
        Statement stmt = conexao.createStatement();
            try {
                stmt.execute("CREATE DATABASE IF NOT EXISTS Gestao_Hoteis");
                stmt.execute("USE Gestao_Hoteis");
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Hotel (
                        id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                        nome VARCHAR(255) NOT NULL UNIQUE
                    )
                    """);
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Quarto (
                        id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                        Numero VARCHAR(10) NOT NULL,
                        Reservado ENUM('DISPONIVEL', 'RESERVADO') DEFAULT 'DISPONIVEL',
                        hotel_nome VARCHAR(255) NOT NULL,
                        FOREIGN KEY (hotel_nome) REFERENCES Hotel(nome) ON DELETE CASCADE,
                        UNIQUE (Numero, hotel_nome)
                    )
                    """);
                stmt.execute("""
                        CREATE TABLE IF NOT EXISTS Reserva (
                            id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                            hotel_nome VARCHAR(255) NOT NULL,
                            Quarto_Num VARCHAR(10) NOT NULL,
                            FOREIGN KEY (hotel_nome) REFERENCES Hotel(nome) ON DELETE CASCADE,
                            FOREIGN KEY (Quarto_Num) REFERENCES Quarto(Numero) ON DELETE CASCADE,
                            UNIQUE (hotel_nome, Quarto_Num)
                        )
                        """);
            } catch (SQLException e) {
                System.out.println("Ocorreu algum erro!!" + e.getMessage());
            }
            
    }
}
