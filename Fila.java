package estruturas;

public class Fila<T> {
    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    public Fila() {
        inicio = fim = null;
        tamanho = 0;
    }

    public void enfileirar(T valor) {
        No<T> novo = new No<>(valor);
        if (fim != null) fim.proximo = novo;
        fim = novo;
        if (inicio == null) inicio = novo;
        tamanho++;
    }

    public T desenfileirar() {
        if (estaVazia()) return null;
        T valor = inicio.valor;
        inicio = inicio.proximo;
        if (inicio == null) fim = null;
        tamanho--;
        return valor;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int tamanho() {
        return tamanho;
    }

    public T espiar() {
        return (inicio != null) ? inicio.valor : null;
    }
}
