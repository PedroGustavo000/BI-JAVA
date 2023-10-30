package school.sptech.ensine.util;

import school.sptech.ensine.domain.Professor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListaLigadaProfessor {
    private NodeProfessor head;

    public ListaLigadaProfessor() {
        this.head = new NodeProfessor(null);
    }

    public NodeProfessor getHead() {
        return head;
    }

    private final char[] caracteres = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public void insereNode(Professor valor) {
        NodeProfessor novo = new NodeProfessor(valor);
        novo.setNext(head.getNext());
        this.head.setNext(novo);
    }

    public void exibe() {
        NodeProfessor atual = head.getNext();
        while (!Objects.isNull(atual)){
            System.out.println(atual.getInfo());
            atual = atual.getNext();
        }
    }

    public NodeProfessor buscaNode(String valor) {
        NodeProfessor atual = this.head.getNext();
        while(!Objects.isNull(atual)) {
            if (atual.getInfo().getNome().equalsIgnoreCase(valor.toUpperCase())) {
                return atual;
            }
            atual = atual.getNext();
        }
        return null;
    }

    /*public List<Professor> buscaListaNode(String valor) {
        List<Professor> list = new ArrayList<>();
        NodeProfessor atual = this.head.getNext();

        while(!Objects.isNull(atual)) {
            if (atual.getInfo().getNome().toUpperCase().startsWith(valor.toUpperCase())) {
                list.add(atual.getInfo());
            }
            atual = atual.getNext();
        }
        return list;
    }*/
    public List<Professor> buscaListaNode(String valor) {
        List<Professor> list = new ArrayList<>();
        buscaRecursivaNode(head.getNext(), valor.toUpperCase(), list);
        return list;
    }

    private void buscaRecursivaNode(NodeProfessor node, String valor, List<Professor> list) {
        if (node != null) {
            if (node.getInfo().getNome().toUpperCase().startsWith(valor)) {
                list.add(node.getInfo());
            }
            buscaRecursivaNode(node.getNext(), valor, list);
        }
    }

    public boolean removeNode(String valor) {
        NodeProfessor anterior = this.head;
        NodeProfessor atual = this.head.getNext();
        while(!Objects.isNull(atual)) {
            if (atual.getInfo().getNome().equals(valor)) {
                anterior.setNext(atual.getNext());
                return true;
            }
            anterior = atual;
            atual = atual.getNext();
        }
        return false;
    }

    public int getTamanho() {
        NodeProfessor atual = this.head.getNext();
        int tamanho = 0;
        while(!Objects.isNull(atual)) {
            tamanho++;
            atual = atual.getNext();
        }
        return tamanho;
    }
    public void inverte() {
        NodeProfessor atual = getHead().getNext();
        NodeProfessor anterior = null;
        NodeProfessor proximo;

        while (atual != null) {
            proximo = atual.getNext();
            atual.setNext(anterior);
            anterior = atual;
            atual = proximo;
        }

        getHead().setNext(anterior);
    }

}
