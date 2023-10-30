package school.sptech.ensine.service.avaliacao.builder;

import school.sptech.ensine.domain.Avaliacao;
import school.sptech.ensine.service.avaliacao.AvaliacaoServiceTest;
import school.sptech.ensine.service.usuario.builder.UsuarioBuilder;

public class AvaliacaoBuilder {

    private AvaliacaoBuilder(){
    }

    public static Avaliacao criarAvaliacao(){

        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setId(10);
        avaliacao.setProfessor(UsuarioBuilder.criarUsuarioProfessor());
        avaliacao.setUsuario(UsuarioBuilder.criarUsuarioAluno());
        avaliacao.setNota(10.0);

        return avaliacao;
    }
}
