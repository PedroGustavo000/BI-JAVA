package school.sptech.ensine.service.usuario.dto;

import jakarta.persistence.ManyToOne;
import school.sptech.ensine.domain.Professor;
import school.sptech.ensine.enumeration.DiasDaSemana;
import school.sptech.ensine.service.usuario.dto.mapper.ProfessorMapper;

import java.time.LocalTime;

public class DisponibilidadeResumoDto {
    private DiasDaSemana diaDaSemana;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private ProfessorResumoDto professor;

    public DisponibilidadeResumoDto() {
    }

    public DisponibilidadeResumoDto(DiasDaSemana diaDaSemana, LocalTime horarioInicio, LocalTime horarioFim, Professor professor) {
        this.diaDaSemana = diaDaSemana;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.professor = ProfessorMapper.mapProfessorToProfessorResumoDto(professor);
    }

    public DiasDaSemana getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(DiasDaSemana diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public ProfessorResumoDto getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = ProfessorMapper.mapProfessorToProfessorResumoDto(professor);
    }
}
