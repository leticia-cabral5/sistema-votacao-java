import java.util.Scanner;

public class SistemaVotacao {

    static Scanner scanner = new Scanner(System.in);

    static final int MAX_CANDIDATOS = 5;
    static final int TOTAL_TURMAS = 3;
    static final int MAX_VOTANTES = 10;

    static int[] numeros = new int[MAX_CANDIDATOS];
    static String[] nomes = new String[MAX_CANDIDATOS];
    static int[] votos = new int[MAX_CANDIDATOS];

    static int[][] matrizVotos = new int[TOTAL_TURMAS][MAX_VOTANTES];
    static int[] votosTurma = new int[TOTAL_TURMAS];

    static int quantidadeCandidatos = 0;

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n================================");
            System.out.println("       SISTEMA DE VOTAÇÃO");
            System.out.println("================================");
            System.out.println("1 - Cadastrar candidatos");
            System.out.println("2 - Iniciar votação");
            System.out.println("3 - Exibir resultado");
            System.out.println("4 - Exibir matriz de votos");
            System.out.println("5 - Encerrar sistema");

            opcao = lerInteiro("\nEscolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarCandidatos();
                    break;

                case 2:
                    iniciarVotacao();
                    break;

                case 3:
                    exibirResultado();
                    break;

                case 4:
                    exibirMatriz();
                    exibirEstatisticasTurma();
                    break;

                case 5:
                    encerrarSistema();
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 5);

        scanner.close();
    }

    static void cadastrarCandidatos() {

        if (quantidadeCandidatos > 0) {
            System.out.println("Os candidatos já foram cadastrados.");
            return;
        }

        int quantidade;

        do {
            quantidade = lerInteiro(
                    "\nQuantos candidatos deseja cadastrar? "
            );

            if (quantidade < 1 || quantidade > MAX_CANDIDATOS)
                System.out.println("Quantidade inválida.");

        } while (quantidade < 1 || quantidade > MAX_CANDIDATOS);

        for (int i = 0; i < quantidade; i++) {

            System.out.println("\nCandidato " + (i + 1));

            int numero;

            while (true) {

                numero = lerInteiro("Número: ");

                if (numero <= 0) {
                    System.out.println(
                            "O número deve ser maior que zero."
                    );
                    continue;
                }

                boolean repetido = false;

                for (int j = 0; j < i; j++) {

                    if (numeros[j] == numero) {
                        repetido = true;
                        break;
                    }
                }

                if (repetido)
                    System.out.println("Número já cadastrado.");
                else
                    break;
            }

            String nome;

            do {

                System.out.print("Nome: ");

                nome = scanner.nextLine().trim();

                if (nome.isEmpty())
                    System.out.println(
                            "O nome não pode ficar vazio."
                    );

            } while (nome.isEmpty());

            numeros[i] = numero;
            nomes[i] = nome;
            votos[i] = 0;
        }

        quantidadeCandidatos = quantidade;

        System.out.println(
                "\nCadastro realizado com sucesso!"
        );

        System.out.println(
                "Pressione ENTER para continuar..."
        );

        scanner.nextLine();
    }

    static void mostrarCandidatos() {

        System.out.println("\nCandidatos disponíveis:");

        for (int i = 0; i < quantidadeCandidatos; i++) {

            System.out.println(
                    numeros[i] + " - " + nomes[i]
            );
        }
    }

    static int buscarCandidato(int numero) {

        for (int i = 0; i < quantidadeCandidatos; i++) {

            if (numeros[i] == numero)
                return i;
        }

        return -1;
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
                    "\nDigite o número da turma (1 a 3): "
            );

            if (turma < 1 || turma > TOTAL_TURMAS)
                System.out.println("Turma inválida.");

        } while (turma < 1 || turma > TOTAL_TURMAS);

        int t = turma - 1;

        if (votosTurma[t] >= MAX_VOTANTES) {

            System.out.println(
                    "Essa turma já atingiu 10 votos."
            );

            return;
        }

        System.out.println("\n================================");
        System.out.println("       INÍCIO DA VOTAÇÃO");
        System.out.println("================================");

        mostrarCandidatos();

        System.out.println(
                "\nDigite 0 para encerrar a votação."
        );

        while (votosTurma[t] < MAX_VOTANTES) {

            int numero = lerInteiro(
                    "\nDigite o número do candidato: "
            );

            if (numero == 0)
                break;

            int candidato = buscarCandidato(numero);

            if (candidato == -1) {

                System.out.println(
                        "ERRO! Candidato inexistente."
                );

                System.out.println(
                        "Digite novamente."
                );

                continue;
            }

            matrizVotos[t][votosTurma[t]] = numero;

            votosTurma[t]++;

            votos[candidato]++;

            System.out.println(
                    "Voto registrado com sucesso!"
            );
        }

        System.out.println("\n================================");
        System.out.println("      ENCERRAMENTO DA VOTAÇÃO");
        System.out.println("================================");

        System.out.println(
                "Votação encerrada com sucesso!"
        );

        int total = 0;

        for (int i = 0; i < TOTAL_TURMAS; i++) {
            total += votosTurma[i];
        }

        System.out.println(
                "Total de votos registrados: " + total
        );
    }

    static void exibirMatriz() {

        System.out.println("\n================================");
        System.out.println("       MATRIZ DE VOTOS");
        System.out.println("================================");

        for (int i = 0; i < TOTAL_TURMAS; i++) {

            System.out.println(
                    "\nTurma " + (i + 1) + ":"
            );

            for (int j = 0; j < MAX_VOTANTES; j++) {

                if (j < votosTurma[i]) {

                    System.out.print(
                            matrizVotos[i][j] + "  "
                    );

                } else {

                    System.out.print("-  ");
                }
            }

            System.out.println();
        }

        System.out.println(
                "\nLoops aninhados:"
        );

        System.out.println(
                "- loop externo percorre as turmas"
        );

        System.out.println(
                "- loop interno percorre os votos de cada turma"
        );
    }

    static void exibirEstatisticasTurma() {

        System.out.println("\n================================");
        System.out.println("     ESTATÍSTICAS POR TURMA");
        System.out.println("================================");

        for (int i = 0; i < TOTAL_TURMAS; i++) {

            System.out.println(
                    "\nTurma " + (i + 1) + ":"
            );

            for (int j = 0; j < quantidadeCandidatos; j++) {

                int quantidade = 0;

                for (int k = 0; k < votosTurma[i]; k++) {

                    if (matrizVotos[i][k] == numeros[j]) {
                        quantidade++;
                    }
                }

                System.out.println(
                        nomes[j] + ": " + quantidade + " votos"
                );
            }
        }
    }

    static void exibirResultado() {

        if (quantidadeCandidatos == 0) {

            System.out.println(
                    "Nenhum candidato cadastrado."
            );

            return;
        }

        int total = 0;

        for (int i = 0; i < quantidadeCandidatos; i++) {
            total += votos[i];
        }

        if (total == 0) {

            System.out.println(
                    "Nenhum voto foi registrado."
            );

            return;
        }

        System.out.println("\n================================");
        System.out.println("          RESULTADO");
        System.out.println("================================");

        int maior = votos[0];

        for (int i = 0; i < quantidadeCandidatos; i++) {

            double porcentagem =
                    (votos[i] * 100.0) / total;

            System.out.printf(
                    "%s -> %d votos%n",
                    nomes[i],
                    votos[i]
            );

            System.out.printf(
                    "Percentual: %.2f%%%n",
                    porcentagem
            );

            System.out.println();

            if (votos[i] > maior) {
                maior = votos[i];
            }
        }

        int quantidadeVencedores = 0;

        for (int i = 0; i < quantidadeCandidatos; i++) {

            if (votos[i] == maior) {
                quantidadeVencedores++;
            }
        }

        if (quantidadeVencedores == 1) {

            System.out.println(
                    "================================"
            );

            System.out.println("Vencedor:");

            for (int i = 0; i < quantidadeCandidatos; i++) {

                if (votos[i] == maior) {

                    System.out.println(
                            nomes[i]
                    );
                }
            }

        } else {

            System.out.println(
                    "================================"
            );

            System.out.println("EMPATE ENTRE:");

            for (int i = 0; i < quantidadeCandidatos; i++) {

                if (votos[i] == maior) {

                    System.out.println(
                            "- " + nomes[i]
                    );
                }
            }
        }

        System.out.println(
                "\nTotal de votos: " + total
        );
    }

    static void encerrarSistema() {

        System.out.println(
                "\n================================"
        );

        System.out.println(
                "    ENCERRAMENTO DO SISTEMA"
        );

        System.out.println(
                "================================"
        );

        System.out.println(
                "Deseja realmente encerrar?"
        );

        System.out.println("1 - Sim");
        System.out.println("2 - Não");

        int opcao = lerInteiro("\nOpção: ");

        if (opcao == 1) {

            System.out.println(
                    "\nSistema finalizado."
            );

            System.out.println(
                    "Obrigado por utilizar o programa."
            );

        } else {

            System.out.println(
                    "\nRetornando ao menu principal..."
            );
        }
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
                    "Digite apenas números."
            );

            scanner.nextLine();
        }
    }
}
