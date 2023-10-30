package school.sptech.ensine.observer;

import school.sptech.ensine.domain.Usuario;

import java.util.List;

public interface ObserverInterface { // O que vai fazer todas as classes que implementarem essa interface
    void notificar(String message);
}
