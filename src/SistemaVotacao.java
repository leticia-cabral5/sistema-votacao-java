import java.util.Scanner;

public class SistemaVotacao {

    static Scanner scanner = new Scanner(System.in);

    static final int MAX_CANDIDATOS = 5;
    static final int TOTAL_TURMAS = 3;
    static final int MAX_VOTANTES_POR_TURMA = 10;

    static int[] numerosCandidatos = new int[MAX_CANDIDATOS];
    static String[] nomesCandidatos = new String[MAX_CANDIDATOS];
    static int[] votosCandidatos = new int[MAX_CANDIDATOS];

    static int[][] votosPorTurma = new int[TOTAL_TURMAS][MAX_VOTANTES_POR_TURMA];

    static int[] quantidadeVotosTurma = new int[TOTAL_TURMAS];
    static int quantidadeCandidatos = 0;

    public static void main(String[] args) {
        System.out.println("Sistema de votação iniciado.");

        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE VOTAÇÃO =====");
            System.out.println("1 - Cadastrar candidatos");
            System.out.println("2 - Iniciar votação");
            System.out.println("3 - Exibir resultado");
            System.out.println("4 - Exibir matriz de votos");
            System.out.println("5 - Sair");

            opcao = lerInteiro("Opção: ");

            switch (opcao) {
                case 1:
                    System.out.println("Cadastro selecionado.");
                    break;
                case 2:
                    System.out.println("Votação selecionada.");
                    break;
                case 3:
                    System.out.println("Resultado selecionado.");
                    break;
                case 4:
                    System.out.println("Matriz selecionada.");
                    break;
                case 5:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 5);
    }

    static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);

            if (scanner.hasNextInt()) {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            }

            System.out.println("Entrada inválida. Digite um número.");
            scanner.nextLine();
        }
    }
}