package entidades;

import simulacao.LoggerSimulacao;

public class CaminhaoPequeno {
    private final int capacidade;
    private int cargaAtual;
    private int viagensFeitas;
    private Zona zona;
    private String nome;

    public CaminhaoPequeno(Zona zona, String nome, int capacidade) {
        this.zona = zona;
        this.nome = nome;
        this.capacidade = capacidade;
        this.cargaAtual = 0;
        this.viagensFeitas = 0;
    }

    public int getCapacidade() {
        return capacidade;
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

    public void coletarLixo(int quantidade, EstacaoTransferencia estacao) {
        LoggerSimulacao.log("🟢 " + nome + " coletando " + quantidade + " toneladas na zona " + zona.getNome());
        cargaAtual += quantidade;

        if (cargaAtual >= capacidade) {
            LoggerSimulacao.log("🔄 " + nome + " atingiu capacidade máxima (" + cargaAtual + "t) e está indo para a estação " + estacao.getNome());
            descarregarNaEstacao(estacao);
        }
    }

    public void descarregarNaEstacao(EstacaoTransferencia estacao) {
        LoggerSimulacao.log("⬇️ " + nome + " descarregando " + cargaAtual + " toneladas na estação " + estacao.getNome());
        estacao.receberLixo(cargaAtual);
        cargaAtual = 0;
        viagensFeitas++;
    }
}
