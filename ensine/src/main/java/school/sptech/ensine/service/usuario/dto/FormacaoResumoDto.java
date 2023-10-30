package school.sptech.ensine.service.usuario.dto;

import jakarta.persistence.ManyToOne;
import school.sptech.ensine.domain.Formacao;
import school.sptech.ensine.domain.Professor;
import school.sptech.ensine.enumeration.TiposFormacao;
import school.sptech.ensine.service.usuario.dto.mapper.ProfessorMapper;

import java.time.LocalDate;

public class FormacaoResumoDto {
    private LocalDate dtInicio;
    private LocalDate dtTermino;
    private String instituicao;
    private String nomeCurso;
    private TiposFormacao tipoFormacao;
    private ProfessorResumoDto professor;

    public FormacaoResumoDto() {
    }

    public FormacaoResumoDto(LocalDate dtInicio, LocalDate dtTermino, String instituicao, String nomeCurso, TiposFormacao tipoFormacao, Professor professor) {
        this.dtInicio = dtInicio;
        this.dtTermino = dtTermino;
        this.instituicao = instituicao;
        this.nomeCurso = nomeCurso;
        this.tipoFormacao = tipoFormacao;
        this.professor = ProfessorMapper.mapProfessorToProfessorResumoDto(professor);
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

    public ProfessorResumoDto getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = ProfessorMapper.mapProfessorToProfessorResumoDto(professor);
    }
}
