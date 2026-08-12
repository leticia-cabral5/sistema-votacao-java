import java.util.Scanner;

public class SistemaVotacao {

    static Scanner scanner = new Scanner(System.in);

    static final int MAX_CANDIDATOS = 5;
    static final int TOTAL_TURMAS = 3;
    static final int MAX_VOTANTES_POR_TURMA = 10;

    static int[] numerosCandidatos = new int[MAX_CANDIDATOS];
    static String[] nomesCandidatos = new String[MAX_CANDIDATOS];
    static int[] votosCandidatos = new int[MAX_CANDIDATOS];

    static int[][] votosPorTurma =
            new int[TOTAL_TURMAS][MAX_VOTANTES_POR_TURMA];

    static int[] quantidadeVotosTurma =
            new int[TOTAL_TURMAS];

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
                    cadastrarCandidatos();
                    break;

                case 2:
                    iniciarVotacao();
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

    static void cadastrarCandidatos() {

        if (quantidadeCandidatos > 0) {
            System.out.println("Os candidatos já foram cadastrados.");
            return;
        }

        int quantidade;

        do {

            quantidade = lerInteiro(
                    "Quantidade de candidatos entre 1 e 5: "
            );

            if (quantidade < 1 || quantidade > MAX_CANDIDATOS) {
                System.out.println("Quantidade inválida.");
            }

        } while (quantidade < 1 || quantidade > MAX_CANDIDATOS);

        for (int i = 0; i < quantidade; i++) {

            int numero;

            while (true) {

                numero = lerInteiro(
                        "\nNúmero do candidato " + (i + 1) + ": "
                );

                if (numero <= 0) {
                    System.out.println(
                            "O número deve ser maior que zero."
                    );
                    continue;
                }

                boolean numeroRepetido = false;

                for (int j = 0; j < i; j++) {

                    if (numerosCandidatos[j] == numero) {
                        numeroRepetido = true;
                        break;
                    }
                }

                if (numeroRepetido) {
                    System.out.println(
                            "Esse número já está cadastrado."
                    );
                    continue;
                }

                break;
            }

            String nome;

            do {

                System.out.print("Nome do candidato: ");

                nome = scanner.nextLine().trim();

                if (nome.isEmpty()) {
                    System.out.println(
                            "O nome não pode ficar vazio."
                    );
                }

            } while (nome.isEmpty());

            numerosCandidatos[i] = numero;
            nomesCandidatos[i] = nome;
            votosCandidatos[i] = 0;
        }

        quantidadeCandidatos = quantidade;

        System.out.println(
                "\nCandidatos cadastrados com sucesso!"
        );

        mostrarCandidatos();
    }

    static int buscarCandidato(int numero) {

        int indiceEncontrado = -1;

        for (int i = 0; i < quantidadeCandidatos; i++) {

            if (numerosCandidatos[i] == numero) {
                indiceEncontrado = i;
                break;
            }
        }

        return indiceEncontrado;
    }

    static void mostrarCandidatos() {

        System.out.println("\nCandidatos disponíveis:");

        for (int i = 0; i < quantidadeCandidatos; i++) {

            System.out.println(
                    numerosCandidatos[i]
                            + " - "
                            + nomesCandidatos[i]
            );
        }
    }

    static void iniciarVotacao() {

        if (quantidadeCandidatos == 0) {
            System.out.println(
                    "Cadastre os candidatos primeiro."
            );
            return;
        }

        int turma;

        do {

            turma = lerInteiro(
                    "Informe a turma de 1 a 3: "
            );

            if (turma < 1 || turma > TOTAL_TURMAS) {
                System.out.println("Turma inválida.");
            }

        } while (turma < 1 || turma > TOTAL_TURMAS);

        int indiceTurma = turma - 1;

        if (quantidadeVotosTurma[indiceTurma]
                >= MAX_VOTANTES_POR_TURMA) {

            System.out.println(
                    "Essa turma já atingiu o limite de votos."
            );
            return;
        }

        mostrarCandidatos();

        int numeroCandidato = lerInteiro(
                "Digite o número do candidato: "
        );

        int indiceCandidato =
                buscarCandidato(numeroCandidato);

        if (indiceCandidato == -1) {

            System.out.println(
                    "Candidato não encontrado."
            );

            return;
        }

        int posicaoVoto =
                quantidadeVotosTurma[indiceTurma];

        votosPorTurma[indiceTurma][posicaoVoto]
                = numeroCandidato;

        quantidadeVotosTurma[indiceTurma]++;

        votosCandidatos[indiceCandidato]++;

        System.out.println(
                "Voto registrado com sucesso!"
        );
    }

    static int lerInteiro(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            if (scanner.hasNextInt()) {

                int valor = scanner.nextInt();

                scanner.nextLine();

                return valor;
            }

            System.out.println(
                    "Entrada inválida. Digite um número."
            );

            scanner.nextLine();
        }
    }
}