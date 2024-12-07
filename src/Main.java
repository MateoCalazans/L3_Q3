import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        // Exibe o ranking
        System.out.println("=== Bem-vindo ao Jogo de Dados! ===");
        System.out.println("Ranking Atual:");
        game.exibirRanking();

        System.out.println("\nPressione ENTER para iniciar o jogo...");
        scanner.nextLine();

        // Entrada de jogadores
        System.out.println("Quantos jogadores irão participar? (Máx: 11)");
        int totalJogadores = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        while (totalJogadores < 1 || totalJogadores > 11) {
            System.out.println("Número inválido. Insira um valor entre 1 e 11:");
            totalJogadores = scanner.nextInt();
            scanner.nextLine();
        }

        for (int i = 0; i < totalJogadores; i++) {
            System.out.println("Jogador " + (i + 1) + ":");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Número apostado (1 a 12): ");
            int aposta = scanner.nextInt();
            scanner.nextLine();

            while (aposta < 1 || aposta > 12 || !game.adicionarJogador(nome, aposta)) {
                System.out.println("Aposta inválida ou já escolhida! Tente novamente:");
                aposta = scanner.nextInt();
                scanner.nextLine();
            }
        }

        // Lançamento dos dados
        System.out.println("\nLançando os dados...");
        int resultado = game.lancarDados();
        System.out.println("O número sorteado foi: " + resultado);

        // Determinação do vencedor
        Player vencedor = game.verificarVencedor(resultado);
        if (vencedor != null) {
            System.out.println("Parabéns, " + vencedor.getNome() + "! Você venceu!");
            game.registrarVitoria(vencedor);
        } else {
            System.out.println("A máquina venceu!");
        }

        // Exibição do ranking atualizado
        System.out.println("\nRanking Atualizado:");
        game.exibirRanking();

        scanner.close();
    }
}
