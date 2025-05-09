package persistence;

import domain.Evento;

import java.time.LocalDate;
import java.util.List;

public interface EventoRepository {
    void salvar(Evento evento);
    List<Evento> listarTodos();
    List<Evento> buscarPorData(LocalDate data);
}
