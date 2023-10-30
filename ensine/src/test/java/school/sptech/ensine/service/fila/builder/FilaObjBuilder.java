package school.sptech.ensine.service.fila.builder;

import school.sptech.ensine.util.FilaObj;

public class FilaObjBuilder {

    private FilaObjBuilder(){

        throw new IllegalStateException("Classe utilitária");
    }

    public static FilaObj criarFilaIncompleta(){

        FilaObj<Integer> filaObj= new FilaObj<>(6);

        filaObj.insert(1);
        filaObj.insert(2);
        filaObj.insert(3);

        return filaObj;
    }

    public static FilaObj criarFilaCheia(){

        FilaObj<Integer> filaObj= new FilaObj<>(6);

        filaObj.insert(1);
        filaObj.insert(2);
        filaObj.insert(3);
        filaObj.insert(4);
        filaObj.insert(5);
        filaObj.insert(6);

        return filaObj;
    }
}
