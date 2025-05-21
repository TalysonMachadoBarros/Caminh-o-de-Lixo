package simulacao;

import entidades.*;
import estruturas.Lista;

public class Simulacao {
    private Lista<Zona> zonas;

    public Simulacao() {
        zonas = new Lista<>();
        configurarZonas();
    }

    private void configurarZonas() {
        zonas.adicionar(new Zona("Centro", 20, 30));
        zonas.adicionar(new Zona("Leste", 10, 20));
        zonas.adicionar(new Zona("Sul", 15, 25));
        zonas.adicionar(new Zona("Norte", 5, 15));
    }

   public void iniciarSimulacao() {
    EstacaoTransferencia estacao = new EstacaoTransferencia();

    for (int i = 1; i <= 30; i++) {
        LoggerSimulacao.log("=== DIA " + i + " ===");

        for (int j = 0; j < zonas.tamanho(); j++) {
            Zona zona = zonas.obter(j);
            int lixo = zona.gerarLixoDiario();
            LoggerSimulacao.log(zona.getNome() + " gerou " + lixo + " toneladas.");

            int viagens = (int) Math.ceil(lixo / 10.0);
            for (int k = 0; k < viagens; k++) {
                String nomeCaminhao = "Caminhao_" + zona.getNome() + "_Viagem_" + (k + 1);
                CaminhaoPequeno caminhao = new CaminhaoPequeno(zona, nomeCaminhao);

                // Simula coleta do caminhão até capacidade máxima
                caminhao.coletarLixo(caminhao.getCapacidade(), estacao);
                // Caminhão vai descarregar automaticamente ao atingir capacidade

                estacao.receberCaminhao(caminhao);
            }
        }

        estacao.processarCaminhoes();
        LoggerSimulacao.log("Caminhões grandes usados: " + estacao.getTotalGrandesUsados());
        LoggerSimulacao.log(" ");
    }
}
}
