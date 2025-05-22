package entidades;

public class Zona {
    private String nome;
    private int geracaoMin;
    private int geracaoMax;

    public Zona(String nome, int geracaoMin, int geracaoMax) {
        this.nome = nome;
        this.geracaoMin = geracaoMin;
        this.geracaoMax = geracaoMax;
    }

    public int gerarLixoDiario() {
        int gerado = geracaoMin + (int)(Math.random() * (geracaoMax - geracaoMin + 1));
        System.out.println("🏙️ Zona " + nome + " gerou " + gerado + " toneladas de lixo hoje.");
        return gerado;
    }

    public String getNome() {
        return nome;
    }
}
