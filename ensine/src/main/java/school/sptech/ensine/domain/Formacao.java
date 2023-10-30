package school.sptech.ensine.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import school.sptech.ensine.enumeration.TiposFormacao;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Formacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate dtInicio;
    private LocalDate dtTermino;
    private String instituicao;
    private String nomeCurso;
    private TiposFormacao tipoFormacao;
    @ManyToOne
    @JsonBackReference
    private Professor professor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(LocalDate dtInicio) {
        this.dtInicio = dtInicio;
    }

    public LocalDate getDtTermino() {
        return dtTermino;
    }

    public void setDtTermino(LocalDate dtTermino) {
        this.dtTermino = dtTermino;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public TiposFormacao getTipoFormacao() {
        return tipoFormacao;
    }

    public void setTipoFormacao(TiposFormacao tipoFormacao) {
        this.tipoFormacao = tipoFormacao;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
