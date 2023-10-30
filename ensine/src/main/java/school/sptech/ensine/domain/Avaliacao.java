package school.sptech.ensine.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Professor professor;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Aula aula;
    @DecimalMin("0.00")
    @DecimalMax("5.00")
    private Double nota;

    private List<Insignia> insignias;
    public Avaliacao() {
    }

    public enum Insignia {

        BONS_EXEMPLOS("Bons exemplos"),
        DIVERTIDO("Divertido"),
        DOMINA_ASSUNTO("Domina o assunto"),
        EXPLICACAO_COMPLETA("Explicação completa"),
        PACIENTE("Paciente"),
        RESPOSTAS_OBJETIVAS("Respostas objetivas"),
        EXEMPLOS_RUINS("Exemplos ruins"),
        GROSSEIRO("Grosseiro"),
        NAO_DOMINA_ASSUNTO("Não domina o assunto"),
        RESPOSTAS_RASAS("Respostas rasas"),
        IMPACIENTE("Impaciente"),
        FUGIU_ASSUNTO("Fugiu do assunto");

        @Override
        public String toString() {
            return displayName;
        }


        private final String displayName;

        Insignia(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public Avaliacao(int id, Aula aula, Professor professor, Usuario usuario, Double nota, List<Integer> insigniasInt) {
        this.id = id;
        this.aula = aula;
        this.professor = professor;
        this.usuario = usuario;
        this.nota = nota;
        this.insignias = new ArrayList<>();
        for (Integer i:
             insigniasInt) {
            if (i < Insignia.values().length) {
                insignias.add(Insignia.values()[i]);
            }
        }
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public List<Insignia> getInsignias() {
        return insignias;
    }

    public void setInsignias(List<Insignia> insignias) {
        this.insignias = insignias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }


}
