package domain;

import java.time.LocalDate;

public class Evento {
    private int id;
    private String titulo;
    private LocalDate data;
    private String local;
    private String descricao;

    public Evento(String titulo, LocalDate data, String local, String descricao) {
        this.titulo = titulo;
        this.data = data;
        this.local = local;
        this.descricao = descricao;
    }

    public Evento(int id, String titulo, LocalDate data, String local, String descricao) {
        this(titulo, data, local, descricao);
        this.id = id;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public LocalDate getData() { return data; }
    public String getLocal() { return local; }
    public String getDescricao() { return descricao; }

    @Override
    public String toString() {
        return id + ": " + titulo + " em " + data + " - " + local + "\n" + descricao;
    }
}