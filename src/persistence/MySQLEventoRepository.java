package persistence;

import domain.Evento;
import util.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySQLEventoRepository implements EventoRepository {
    private static final String INSERT = "INSERT INTO eventos (titulo, data, local, descricao) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM eventos";
    private static final String SELECT_BY_DATE = "SELECT * FROM eventos WHERE data = ?";

    @Override
    public void salvar(Evento evento) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT)) {
            stmt.setString(1, evento.getTitulo());
            stmt.setDate(2, Date.valueOf(evento.getData()));
            stmt.setString(3, evento.getLocal());
            stmt.setString(4, evento.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Evento> listarTodos() {
        List<Evento> eventos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL)) {
            while (rs.next()) {
                eventos.add(new Evento(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getDate("data").toLocalDate(),
                        rs.getString("local"),
                        rs.getString("descricao")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }

    @Override
    public List<Evento> buscarPorData(LocalDate data) {
        List<Evento> eventos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_DATE)) {
            stmt.setDate(1, Date.valueOf(data));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                eventos.add(new Evento(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getDate("data").toLocalDate(),
                        rs.getString("local"),
                        rs.getString("descricao")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }
}
