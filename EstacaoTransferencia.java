package entidades;

import estruturas.Fila;

public class EstacaoTransferencia {
    private String nome;
    private Fila<CaminhaoPequeno> filaPequenos;
    private Fila<CaminhaoGrande> filaGrandes;
    private int contadorGrandes;

    public EstacaoTransferencia() {
        this.nome = "Estação Central";  // Ou outro nome que preferir
        filaPequenos = new Fila<>();
        filaGrandes = new Fila<>();
        contadorGrandes = 1;
    }

    public String getNome() {
        return nome;
    }

    public void receberCaminhao(CaminhaoPequeno pequeno) {
        filaPequenos.enfileirar(pequeno);
        System.out.println("📦 Caminhão pequeno da zona " + pequeno.getZona().getNome() + " chegou à estação com " +
                pequeno.getCapacidade() + " toneladas.");
    }

    public void processarCaminhoes() {
        while (!filaPequenos.estaVazia()) {
            CaminhaoPequeno pequeno = filaPequenos.desenfileirar();
            CaminhaoGrande grandeAtual = filaGrandes.espiar();

            if (grandeAtual == null || grandeAtual.estaCheio()) {
                grandeAtual = new CaminhaoGrande("Caminhão Grande " + contadorGrandes++);
                filaGrandes.enfileirar(grandeAtual);
            }

            grandeAtual.adicionarCarga(pequeno.getCapacidade());
        }

        // Ao final, forçamos descarregamento de todos os caminhões restantes com carga.
        while (!filaGrandes.estaVazia()) {
            CaminhaoGrande grande = filaGrandes.desenfileirar();
            if (grande.getCargaAtual() > 0) {
                grande.descarregarNoLixao();
            }
        }
    }

    public int getTotalGrandesUsados() {
        return contadorGrandes - 1;
    }

    public void receberLixo(int quantidade) {
    System.out.println("🏭 Estação recebeu " + quantidade + " toneladas de lixo.");
    // Aqui você pode armazenar esse lixo em uma variável interna, se quiser
    }

}
