package controller;

import domain.Evento;
import service.EventoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EventoController {
    private final EventoService service = new EventoService();
    private final Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n1. Criar evento");
            System.out.println("2. Listar eventos");
            System.out.println("3. Buscar por data");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> criarEvento();
                case 2 -> listarEventos();
                case 3 -> buscarPorData();
            }
        } while (opcao != 0);
    }

    private void criarEvento() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        System.out.print("Local: ");
        String local = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Evento evento = new Evento(titulo, data, local, descricao);
        service.salvar(evento);
        System.out.println("Evento salvo com sucesso!");
    }

    private void listarEventos() {
        List<Evento> eventos = service.listarTodos();
        eventos.forEach(System.out::println);
    }

    private void buscarPorData() {
        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        List<Evento> eventos = service.buscarPorData(data);
        eventos.forEach(System.out::println);
    }
}
