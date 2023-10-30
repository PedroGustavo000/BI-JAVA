package school.sptech.ensine.service.formacao.builder;

import school.sptech.ensine.domain.Formacao;
import school.sptech.ensine.enumeration.TiposFormacao;
import school.sptech.ensine.service.usuario.builder.UsuarioBuilder;

import java.time.LocalDate;

public class FormacaoBuilder {

    private FormacaoBuilder(){
    }

    public static Formacao criarFormacao(){

        Formacao formacao = new Formacao();

        formacao.setId(10);
        formacao.setDtInicio(LocalDate.now());
        formacao.setDtTermino(LocalDate.now());
        formacao.setInstituicao("Instituicao");
        formacao.setNomeCurso("Curso");
        formacao.setTipoFormacao(TiposFormacao.SEQUENCIAL);
        formacao.setProfessor(UsuarioBuilder.criarUsuarioProfessor());

        return formacao;
    }
}
