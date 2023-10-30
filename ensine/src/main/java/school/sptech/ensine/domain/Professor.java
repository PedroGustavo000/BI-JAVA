package school.sptech.ensine.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import school.sptech.ensine.service.usuario.AvaliacaoService;

import java.util.ArrayList;
import java.util.List;


@Entity
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Professor extends Usuario {

    @Size(max = 900)
    private String descricao;


    @OneToMany(mappedBy = "professor")
    private List<Aula> aulas;

    private Double precoHoraAula;

    @OneToMany(mappedBy = "professor")
    @JsonManagedReference
    private List<Disponibilidade> disponibilidades;

    @OneToMany(mappedBy = "professor")
    @JsonManagedReference
    private List<Formacao> formacoes;
    @OneToMany(mappedBy = "professor")
    private List<Avaliacao> avaliacoes;

    @Override
    public int getId() {
        return super.getId();
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDisponibilidades(List<Disponibilidade> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }

    public List<Disponibilidade> getDisponibilidades() {
        return disponibilidades;
    }

    public void setFormacoes(List<Formacao> formacoes) {
        this.formacoes = formacoes;
    }

    public void addDisponibilidade(Disponibilidade disponibilidade) {
        if(this.disponibilidades == null) {
            this.disponibilidades = new ArrayList<>();
        }
        this.disponibilidades.add(disponibilidade);
    }

    public void addFormacao(Formacao formacao) {
        if(this.formacoes == null) {
            this.formacoes = new ArrayList<>();
        }
        this.formacoes.add(formacao);
    }

    public Double getPrecoHoraAula() {
        return precoHoraAula;
    }

    public void setPrecoHoraAula(Double precoHoraAula) {
        this.precoHoraAula = precoHoraAula;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Formacao> getFormacoes() {
        return formacoes;
    }

}
