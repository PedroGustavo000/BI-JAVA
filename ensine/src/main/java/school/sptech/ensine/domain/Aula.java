package school.sptech.ensine.domain;

import jakarta.persistence.*;
import school.sptech.ensine.enumeration.Privacidade;
import school.sptech.ensine.enumeration.Status;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Professor professor;

    private String titulo;

    private String descricao;

    private int duracaoSegundos;

    private Double preco;

    @OneToOne
    private Materia materia;

    private LocalDateTime dataHora;

    // limiteParticipantes
    private int limiteParticipantes;

    @ManyToMany
    private List<Usuario> alunos;
    @OneToMany
    private List<Mensagem> mensagems;
    @ManyToMany
    private List<Avaliacao> avaliacoes;
    // Status
    private Status status;

    private Privacidade privacidade;

    @OneToOne
    private Chat chat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Get e set professor aqui __________________________________________________
    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    // limiteParticipantes
    public int getLimiteParticipantes() {
        return limiteParticipantes;
    }
    // limiteParticipantes
    public void setLimiteParticipantes(int limiteParticipantes) {
        this.limiteParticipantes = limiteParticipantes;
    }

    public void setAlunos(List<Usuario> alunos) {
        this.alunos = alunos;
    }

    public List<Usuario> getAlunos() {
        return alunos;
    }
    // Status
    public Status getStatus() {
        return status;
    }
    // Status
    public void setStatus(Status status) {
        this.status = status;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public void setDuracaoSegundos(int duracaoSegundos) {
        this.duracaoSegundos = duracaoSegundos;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Privacidade getPrivacidade() {
        return privacidade;
    }

    public void setPrivacidade(Privacidade privacidade) {
        this.privacidade = privacidade;
    }
}