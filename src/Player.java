public class Player {
    private String nome;
    private int aposta;

    public Player(String nome, int aposta) {
        this.nome = nome;
        this.aposta = aposta;
    }

    public String getNome() {
        return nome;
    }

    public int getAposta() {
        return aposta;
    }
}
