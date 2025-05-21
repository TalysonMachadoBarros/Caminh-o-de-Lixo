package entidades;

public class CaminhaoPequeno {
    private static final int CAPACIDADE = 10;
    private int cargaAtual;
    private int viagensFeitas;
    private Zona zona;
    private String nome;

    public CaminhaoPequeno(Zona zona, String nome) {
        this.zona = zona;
        this.viagensFeitas = 0;
        this.cargaAtual = 0;
        this.nome = nome;
    }

    public int getCapacidade() {
        return CAPACIDADE;
    }

    public Zona getZona() {
        return zona;
    }

    public int getCargaAtual() {
        return cargaAtual;
    }

    public int getViagensFeitas() {
        return viagensFeitas;
    }

    public String getNome() {
        return nome;
    }

    public void coletarLixo(int quantidade, EstacaoTransferencia estacao) {
        System.out.println("🟢 " + nome + " coletando " + quantidade + " toneladas na zona " + zona.getNome());
        cargaAtual += quantidade;

        if (cargaAtual >= CAPACIDADE) {
            System.out.println("🔄 " + nome + " atingiu capacidade máxima (" + cargaAtual + "t) e está indo para a estação.");
            descarregarNaEstacao(estacao);
        }
    }

    public void descarregarNaEstacao(EstacaoTransferencia estacao) {
        System.out.println("⬇️ " + nome + " descarregando " + cargaAtual + " toneladas na estação.");
        estacao.receberLixo(cargaAtual);  // Este método deve existir na EstacaoTransferencia
        cargaAtual = 0;
        viagensFeitas++;
    }
}
