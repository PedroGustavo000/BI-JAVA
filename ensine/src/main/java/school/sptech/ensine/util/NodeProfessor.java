package school.sptech.ensine.util;

import school.sptech.ensine.domain.Professor;

public class NodeProfessor {
    private Professor info;
    private NodeProfessor next;

    public NodeProfessor(Professor info) {
        this.info = info;
        this.next = null;
    }

    public Professor getInfo() {
        return info;
    }

    public void setInfo(Professor info) {
        this.info = info;
    }

    public NodeProfessor getNext() {
        return next;
    }

    public void setNext(NodeProfessor next) {
        this.next = next;
    }

}
