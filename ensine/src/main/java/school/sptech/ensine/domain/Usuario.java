package school.sptech.ensine.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import school.sptech.ensine.observer.ObserverInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements ObserverInterface
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isProfessor;

    @NotBlank
    @Size(min = 2, max = 80)
    private String nome;

    @NotBlank
    @Email
    @Size(min = 9, max = 40)
    private String email;

    @NotBlank
    @Size(min = 8)
    private String senha;

    @PastOrPresent
    private LocalDate dataNasc;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "usuario_materia",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn)
    private List<Materia> materias;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "usuario_aula",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn)
    private List<Aula> aulas;

    @OneToMany
    private List<Mensagem> mensagens;

    @ManyToMany(mappedBy = "participantes")
    private List<Chat> chats;

    @OneToMany(mappedBy = "usuario")
    private List<Avaliacao> avaliacoes;

    private String foto;

//    @ManyToMany(cascade=CascadeType.PERSIST)
//    @JoinTable(name = "usuario_materia",
//        joinColumns = @JoinColumn,
//        inverseJoinColumns = @JoinColumn)

    public static int converterEmAnos(Usuario usuario) {
        LocalDate dataNasc = usuario.getDataNasc();
        LocalDate dataAtual = LocalDate.now();

        Period periodo = Period.between(dataNasc, dataAtual);
        return periodo.getYears();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isProfessor() {
        return isProfessor;
    }

    public void setProfessor(boolean professor) {
        isProfessor = professor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public List<Materia> getMaterias() {

        return materias;
    }

    public void setMaterias(List<Materia> materias) {

        this.materias = materias;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    @Transient // Indica que o seguinte atributo não será utilizado no banco de dados
    List<Usuario> observers = new ArrayList<>();
    public List<Usuario> notifyObservers(Aula aula, String message) { //O que vai fazer quando for ativado
        observers.forEach(usuario -> notificar(message));
        List<Usuario> observersIds = new ArrayList<>();
        for (Usuario observer : observers) {
            observersIds.add(observer);
            System.out.println("Notificando o usuário de ID " + observer.getId() + " sobre a aula " + aula.getTitulo());
        }
        return observersIds;
    }

    public void addObserver(Usuario observer) {
        observers.add(observer);
    }

    public void removeObserver(Usuario observer) {
        observers.remove(observer);
    }


    @Override
    public void notificar(String message) {
        System.out.println(message);
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return nome;
    }
}
