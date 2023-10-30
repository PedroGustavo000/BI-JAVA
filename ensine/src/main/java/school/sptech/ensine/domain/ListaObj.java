package school.sptech.ensine.domain;

import java.util.Arrays;
import java.util.List;

public class ListaObj<T> {
    private T[] vetor;
    private int nroElem;
    public ListaObj(int tamanho) {
        vetor = (T[]) new Object[tamanho];
        nroElem = 0;
    }
    public ListaObj() {
        vetor = (T[]) new Object[0];
        nroElem = 0;
    }

    public T[] getVetor() {
        T[] vetorNovo = (T[]) new Object[nroElem];
        for(int i = 0; i < nroElem; i++){
            vetorNovo[i] = this.vetor[i];
        }
        return vetorNovo;
    }

    public int size() {
        return nroElem;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void imprimir(){
        for(int i = 0; i < nroElem; i++){
            System.out.println(this.vetor[i]);
        }
    }

    public void adiciona(T elem) {
        if(nroElem == vetor.length){
            //throw new IllegalStateException("Lista cheia");
            vetor = Arrays.copyOf(vetor, vetor.length + 1);
        }
        vetor[nroElem] = elem;
        nroElem++;
    }

    public void adiciona(T[] novosObjetos) {
        for(T elem : novosObjetos) {
            adiciona(elem);
        }
    }
    public void adiciona(List<T> novosObjetos) {
        for(T elem : novosObjetos) {
            adiciona(elem);
        }
    }

    public int busca(T elem){
        for (int i = 0; i < nroElem; i++){
            if(vetor[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    public T get(int indice){
        return isIndiceValido(indice) ? this.vetor[indice] : null;
    }

    public void limpa() {
        for (int i = 0; i < nroElem; i++){
            this.vetor[i] = null;
        }
        this.nroElem = 0;
    }

    public boolean remove(int indice) {
        if(isIndiceValido(indice)) {
            for(int i = indice; i < nroElem - 1; i++){
                vetor[i] = vetor[i + 1];
            }
            this.nroElem--;
            return true;
        } else {
            return false;
        }
    }

    public boolean removeElemento(T elemento){
        for(int i = 0; i < nroElem; i++){
            if(this.vetor[i] == elemento){
                remove(i);
                return true;
            }
        }
        return false;
    }

    private boolean isIndiceValido(int indice) {
        return indice >= 0 && indice < nroElem;
    }
}
