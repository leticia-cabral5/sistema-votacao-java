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
                    exibirResultado();
                    break;

                case 4:
                    exibirMatriz();
                    break;

                case 5:
                    System.out.println("Sistema encerrado.");
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
            quantidade = lerInteiro("Quantidade de candidatos (1 a 5): ");

            if (quantidade < 1 || quantidade > MAX_CANDIDATOS)
                System.out.println("Quantidade inválida.");

        } while (quantidade < 1 || quantidade > MAX_CANDIDATOS);

        for (int i = 0; i < quantidade; i++) {

            int numero;

            while (true) {
                numero = lerInteiro("Número do candidato: ");

                if (numero <= 0) {
                    System.out.println("O número deve ser maior que zero.");
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
                System.out.print("Nome do candidato: ");
                nome = scanner.nextLine().trim();

                if (nome.isEmpty())
                    System.out.println("O nome não pode ficar vazio.");

            } while (nome.isEmpty());

            numeros[i] = numero;
            nomes[i] = nome;
            votos[i] = 0;
        }

        quantidadeCandidatos = quantidade;

        System.out.println("Candidatos cadastrados!");
        mostrarCandidatos();
    }

    static void mostrarCandidatos() {

        System.out.println("\nCandidatos:");

        for (int i = 0; i < quantidadeCandidatos; i++)
            System.out.println(numeros[i] + " - " + nomes[i]);
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
            System.out.println("Cadastre os candidatos primeiro.");
            return;
        }

        int turma;

        do {
            turma = lerInteiro("Turma (1 a 3): ");

            if (turma < 1 || turma > TOTAL_TURMAS)
                System.out.println("Turma inválida.");

        } while (turma < 1 || turma > TOTAL_TURMAS);

        int t = turma - 1;

        if (votosTurma[t] >= MAX_VOTANTES) {
            System.out.println("Essa turma já atingiu 10 votos.");
            return;
        }

        mostrarCandidatos();

        System.out.println("Digite 0 para encerrar.");

        while (votosTurma[t] < MAX_VOTANTES) {

            int numero = lerInteiro("Número do candidato: ");

            if (numero == 0)
                break;

            int candidato = buscarCandidato(numero);

            if (candidato == -1) {
                System.out.println("Candidato inexistente.");
                continue;
            }

            matrizVotos[t][votosTurma[t]] = numero;
            votosTurma[t]++;
            votos[candidato]++;

            System.out.println("Voto registrado!");
        }

        if (votosTurma[t] == MAX_VOTANTES)
            System.out.println("Limite de 10 votos atingido.");
    }

    static void exibirMatriz() {

        System.out.println("\n===== MATRIZ DE VOTOS =====");

        for (int i = 0; i < TOTAL_TURMAS; i++) {

            System.out.print("Turma " + (i + 1) + ": ");

            for (int j = 0; j < MAX_VOTANTES; j++) {

                if (j < votosTurma[i])
                    System.out.print(matrizVotos[i][j] + " ");
                else
                    System.out.print("- ");
            }

            System.out.println();
        }
    }

    static void exibirResultado() {

        if (quantidadeCandidatos == 0) {
            System.out.println("Nenhum candidato cadastrado.");
            return;
        }

        int total = 0;

        for (int i = 0; i < quantidadeCandidatos; i++) {
            total += votos[i];
        }

        if (total == 0) {
            System.out.println("Nenhum voto foi registrado.");
            return;
        }

        System.out.println("\n===== RESULTADO =====");

        int maior = votos[0];

        for (int i = 0; i < quantidadeCandidatos; i++) {

            double porcentagem = (votos[i] * 100.0) / total;

            System.out.printf(
                    "%s -> %d voto(s) - %.2f%%%n",
                    nomes[i],
                    votos[i],
                    porcentagem
            );

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
            System.out.println("\nVencedor:");

            for (int i = 0; i < quantidadeCandidatos; i++) {
                if (votos[i] == maior) {
                    System.out.println(nomes[i]);
                }
            }

        } else {
            System.out.println("\nEmpate entre:");

            for (int i = 0; i < quantidadeCandidatos; i++) {
                if (votos[i] == maior) {
                    System.out.println("- " + nomes[i]);
                }
            }
        }

        System.out.println("Total de votos: " + total);
    }

    static int lerInteiro(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            if (scanner.hasNextInt()) {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            }

            System.out.println("Digite apenas números.");
            scanner.nextLine();
        }
    }
}