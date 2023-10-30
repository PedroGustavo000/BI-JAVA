package school.sptech.ensine.service.usuario.dto.mapper;

import school.sptech.ensine.domain.Formacao;
import school.sptech.ensine.service.usuario.dto.FormacaoResumoDto;

import java.util.Objects;

public class FormacaoMapper {
    public static FormacaoResumoDto mapFormacaoToFormacaoResumoDto(Formacao formacao) {
        if(Objects.isNull(formacao)) {
            return null;
        }
        return new FormacaoResumoDto(
                formacao.getDtInicio(),
                formacao.getDtTermino(),
                formacao.getInstituicao(),
                formacao.getNomeCurso(),
                formacao.getTipoFormacao(),
                formacao.getProfessor());
    }
}
