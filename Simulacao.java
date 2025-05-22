package simulacao;

import entidades.*;
import estruturas.Lista;
import java.util.Random;

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

    int[] capacidadesFixas = {2, 4, 8, 10};

    for (int i = 1; i <= 30; i++) {
        LoggerSimulacao.log("=== DIA " + i + " ===");

        for (int j = 0; j < zonas.tamanho(); j++) {
            Zona zona = zonas.obter(j);
            int lixo = zona.gerarLixoDiario();
            LoggerSimulacao.log(zona.getNome() + " gerou " + lixo + " toneladas.");

            int capacidadeTotal = 0;
            int viagemNum = 1;
            int caminhaoIndex = 0;

            while (capacidadeTotal < lixo) {
                int capacidade = capacidadesFixas[caminhaoIndex];
                String nomeCaminhao = "Caminhao_" + zona.getNome() + "_Viagem_" + viagemNum + "_Cap_" + capacidade;
                CaminhaoPequeno caminhao = new CaminhaoPequeno(zona, nomeCaminhao, capacidade);

                int restante = lixo - capacidadeTotal;
                int coleta = Math.min(capacidade, restante);

                caminhao.coletarLixo(coleta, estacao);
                if (caminhao.getCargaAtual() > 0) {
                    caminhao.descarregarNaEstacao(estacao);
                }

                estacao.receberCaminhao(caminhao);
                capacidadeTotal += coleta;
                viagemNum++;

                caminhaoIndex = (caminhaoIndex + 1) % capacidadesFixas.length;
            }
        }

        estacao.processarCaminhoes();
        LoggerSimulacao.log("Caminhões grandes usados: " + estacao.getTotalGrandesUsados());
        LoggerSimulacao.log(" ");
    }

    LoggerSimulacao.log("Simulação finalizada.");


            estacao.processarCaminhoes();
            LoggerSimulacao.log("Caminhões grandes usados: " + estacao.getTotalGrandesUsados());
            LoggerSimulacao.log(" ");
            LoggerSimulacao.log("Simulação finalizada.");

    }
}

