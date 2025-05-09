package service;

import domain.Evento;
import persistence.EventoRepository;
import persistence.MySQLEventoRepository;

import java.time.LocalDate;
import java.util.List;

public class EventoService {
    private final EventoRepository repository;

    public EventoService() {
        this.repository = new MySQLEventoRepository();
    }

    public void salvar(Evento evento) {
        if (evento.getTitulo().isBlank()) {
            throw new IllegalArgumentException("O título do evento não pode estar vazio.");
        }
        repository.salvar(evento);
    }

    public List<Evento> listarTodos() {
        return repository.listarTodos();
    }

    public List<Evento> buscarPorData(LocalDate data) {
        return repository.buscarPorData(data);
    }
}
