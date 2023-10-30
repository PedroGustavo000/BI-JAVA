package school.sptech.ensine.service.pilha.builder;

import school.sptech.ensine.util.FilaObj;
import school.sptech.ensine.util.PilhaObj;

public class PilhaObjBuilder {


    private PilhaObjBuilder(){

        throw new IllegalStateException("Classe utilitária");
    }

    public static PilhaObj criarPilhaIncompleta(){

        PilhaObj<Integer> pilhaObj = new PilhaObj<>(6);

        pilhaObj.push(1);
        pilhaObj.push(2);
        pilhaObj.push(3);

        return pilhaObj;
    }

    public static PilhaObj criarPilhaCheia(){

        PilhaObj<Integer> pilhaObj= new PilhaObj<>(6);

        pilhaObj.push(1);
        pilhaObj.push(2);
        pilhaObj.push(3);
        pilhaObj.push(4);
        pilhaObj.push(5);
        pilhaObj.push(6);

        return pilhaObj;
    }
}
