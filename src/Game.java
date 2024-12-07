import java.io.*;
import java.util.*;

public class Game {
    private List<Player> jogadores;
    private Random random;
    private final String rankingFile = "ranking.txt";

    public Game() {
        jogadores = new ArrayList<>();
        random = new Random();
    }

    // Adiciona jogador à lista
    public boolean adicionarJogador(String nome, int aposta) {
        for (Player jogador : jogadores) {
            if (jogador.getAposta() == aposta) {
                return false; // Aposta duplicada
            }
        }
        jogadores.add(new Player(nome, aposta));
        return true;
    }

    // Lança dois dados e retorna o resultado
    public int lancarDados() {
        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        return dado1 + dado2;
    }

    // Verifica se há vencedor
    public Player verificarVencedor(int resultado) {
        for (Player jogador : jogadores) {
            if (jogador.getAposta() == resultado) {
                return jogador;
            }
        }
        return null; // Máquina vence
    }

    // Atualiza o ranking no arquivo
    public void registrarVitoria(Player vencedor) {
        Map<String, Integer> ranking = carregarRanking();
        ranking.put(vencedor.getNome(), ranking.getOrDefault(vencedor.getNome(), 0) + 1);
        salvarRanking(ranking);
    }

    // Carrega o ranking do arquivo
    public Map<String, Integer> carregarRanking() {
        Map<String, Integer> ranking = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rankingFile))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(":");
                ranking.put(partes[0], Integer.parseInt(partes[1]));
            }
        } catch (IOException e) {
            // Arquivo não encontrado, ranking vazio
        }
        return ranking;
    }

    // Salva o ranking no arquivo
    public void salvarRanking(Map<String, Integer> ranking) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rankingFile))) {
            for (Map.Entry<String, Integer> entrada : ranking.entrySet()) {
                writer.println(entrada.getKey() + ":" + entrada.getValue());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o ranking.");
        }
    }

    // Exibe o top 5 do ranking
    public void exibirRanking() {
        Map<String, Integer> ranking = carregarRanking();
        ranking.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> System.out.println(entry.getKey() + " - Vitórias: " + entry.getValue()));
    }
}

