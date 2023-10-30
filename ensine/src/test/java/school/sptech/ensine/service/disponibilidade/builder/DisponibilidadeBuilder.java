package school.sptech.ensine.service.disponibilidade.builder;

import school.sptech.ensine.domain.Disponibilidade;
import school.sptech.ensine.enumeration.DiasDaSemana;
import school.sptech.ensine.service.usuario.builder.UsuarioBuilder;

import java.time.LocalTime;

public class DisponibilidadeBuilder {

    private DisponibilidadeBuilder(){
    }

    public static Disponibilidade criarDisponibilidade(){

        Disponibilidade disponibilidade = new Disponibilidade();

        disponibilidade.setId(10);
        disponibilidade.setProfessor(UsuarioBuilder.criarUsuarioProfessor());
        disponibilidade.setDiaDaSemana(DiasDaSemana.SABADO);
        disponibilidade.setHorarioFim(LocalTime.MAX);
        disponibilidade.setHorarioFim(LocalTime.MIN);

        return disponibilidade;
    }
}
