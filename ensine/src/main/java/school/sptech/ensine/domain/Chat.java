package school.sptech.ensine.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany
    private List<Mensagem> mensagens;
    @OneToOne
    private Aula aula;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "chat_usuario",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn)
    private List<Usuario> participantes;

    public Chat() {
    }

    public Chat(int id, List<Mensagem> mensagens, Aula aula, List<Usuario> participantes) {
        this.id = id;
        this.mensagens = mensagens;
        this.aula = aula;
        this.participantes = participantes;
    }

    public int getId() {
        return id;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public Aula getAula() {
        return aula;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }
}
