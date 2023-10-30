package school.sptech.ensine.util;

import school.sptech.ensine.domain.Professor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TabelaHashProfessor {
    private ListaLigadaProfessor[] tab;
    private int localLetra;

    private char[] caracteres = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    //letras = quantidade de letras prédefinidas
    public TabelaHashProfessor(int localLetra) {
        int letras = 27; // alfabeto + "restante"
        this.tab = new ListaLigadaProfessor[letras];
        for (int i = 0; i < letras; i++) {
            tab[i] = new ListaLigadaProfessor();
        }
        this.localLetra = localLetra;
    }

    private Integer funcaoHashBusca(String professor) {
        if (localLetra < professor.toUpperCase().length()) {
            char letra = professor.toUpperCase().charAt(localLetra);
            for (int i = 0; i < caracteres.length; i++) {
                if (letra == caracteres[i]) {
                    return i;
                }
            }
            return caracteres.length;
        }
        return null;
    }

    public void insere(Professor x) {
        Integer indice = funcaoHashBusca(x.getNome());
        if (!Objects.isNull(indice)) {
            tab[indice].insereNode(x);
        }
    }

    public boolean busca(String nomeProfessor) {
        Integer indice = funcaoHashBusca(nomeProfessor);
        if(Objects.isNull(indice)) {
            return false;
        }
        NodeProfessor nodeProfessor = tab[indice].buscaNode(nomeProfessor);
        return nodeProfessor != null;
    }

    public List<Professor> buscaLista(String nomeProfessor) {
        Integer indice = funcaoHashBusca(nomeProfessor);
        if(Objects.isNull(indice)) {
            return new ArrayList<>();
        }
        return tab[indice].buscaListaNode(nomeProfessor);
    }

    public List<Professor> listAll() {
        List<Professor> listaProfessores = new ArrayList<>();

        for (ListaLigadaProfessor lista : tab) {
            NodeProfessor atual = lista.getHead().getNext();
            while (atual != null) {
                listaProfessores.add(atual.getInfo());
                atual = atual.getNext();
            }
        }

        return listaProfessores;
    }

    public void exibe() {
        for (int i = 0; i < tab.length; i++) {
            if (i != caracteres.length) {
                System.out.print("Entrada " + caracteres[i] + ": ");
            } else {
                System.out.print("Entrada restante: ");
            }
            if (tab[i].getTamanho() == 0) {
                System.out.println("Lista vazia");
            } else {
                tab[i].exibe();
            }
        }
    }
}
