package school.sptech.ensine.util;

public class PilhaObj <T>{
    // 01) Atributos
    private T[] pilha;
    private int topo;

    // 02) Construtor
    public PilhaObj(int capacidade) {
        pilha = (T[]) new Object[capacidade];
        topo = -1;
    }

    // 03) MÃ©todo isEmpty
    public Boolean isEmpty() {
        if(topo < 0){
            return true;
        }else{
            return false;
        }
    }

    // 04) MÃ©todo isFull
    public Boolean isFull() {
        if(topo >= pilha.length -1){
            return true;
        }else{
            return false;
        }
    }

    // 05) MÃ©todo push
    public void push(T info) {
        if(isFull()){
            throw new IllegalStateException("Pilha Cheia!");
        }else{
            pilha[++topo] = info;
        }
    }

    // 06) MÃ©todo pop
    public T pop() {
        if(!isEmpty()){
            T valorTopo = pilha[topo--];
            return valorTopo;
        }else{
            throw new IllegalStateException();
        }
    }

    // 07) MÃ©todo peek
    public T peek() {
        if(!isEmpty()){
            T valorTopo = pilha[topo];
            return valorTopo;
        }else{
            return null;
        }
    }

    // 08) MÃ©todo exibe
    public void exibe() {
        System.out.println(pilha.toString());
    }

    //Getters & Setters (manter)
    public int getTopo() {
        return topo;
    }


}
