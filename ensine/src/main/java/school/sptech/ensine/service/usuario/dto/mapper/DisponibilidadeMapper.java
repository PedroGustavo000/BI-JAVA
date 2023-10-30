package school.sptech.ensine.service.usuario.dto.mapper;

import school.sptech.ensine.domain.Disponibilidade;
import school.sptech.ensine.service.usuario.dto.DisponibilidadeResumoDto;

import java.util.Objects;

public class DisponibilidadeMapper {
    public static DisponibilidadeResumoDto mapDisponibilidadeToDisponibilidadeResumoDto(Disponibilidade disponibilidade) {
        if(Objects.isNull(disponibilidade)) {
            return null;
        }
        return new DisponibilidadeResumoDto(
                disponibilidade.getDiaDaSemana(),
                disponibilidade.getHorarioInicio(),
                disponibilidade.getHorarioFim(),
                disponibilidade.getProfessor()
        );
    }
}
