package entidades;

public class CaminhaoGrande {
    private static final int CAPACIDADE = 20;
    private int cargaAtual;
    private String nome;

    public CaminhaoGrande(String nome) {
        this.cargaAtual = 0;
        this.nome = nome;
    }

    public boolean adicionarCarga(int quantidade) {
        if (cargaAtual + quantidade <= CAPACIDADE) {
            cargaAtual += quantidade;
            System.out.println("🚛 " + nome + " coletou " + quantidade + " toneladas da estação. Carga atual: " + cargaAtual + "/" + CAPACIDADE + " toneladas.");
            if (estaCheio()) {
                System.out.println("⚠️ " + nome + " está cheio e indo para o lixão.");
                descarregarNoLixao();
            }
            return true;
        } else {
            System.out.println("❌ " + nome + ": não há espaço suficiente para coletar " + quantidade + " toneladas.");
            return false;
        }
    }

    public boolean estaCheio() {
        return cargaAtual >= CAPACIDADE;
    }

    public int getCargaAtual() {
        return cargaAtual;
    }

    public void descarregarNoLixao() {
        System.out.println("🗑️ " + nome + " descarregou " + cargaAtual + " toneladas no lixão.");
        cargaAtual = 0;
    }
}
