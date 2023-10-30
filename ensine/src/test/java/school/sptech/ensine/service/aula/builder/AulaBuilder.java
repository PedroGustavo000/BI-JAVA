package school.sptech.ensine.service.aula.builder;

import school.sptech.ensine.domain.Aula;
import school.sptech.ensine.domain.Materia;
import school.sptech.ensine.enumeration.Status;
import school.sptech.ensine.service.usuario.builder.UsuarioBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AulaBuilder {

    private AulaBuilder(){

        throw new IllegalStateException("Classe Utilitária");
    }

    public static Materia criarMateria() {

        Materia materia = new Materia();
        materia.setId(1);
        materia.setNome("Matematica");

        return materia;
    }

    public static List<Materia> criarListaMateria(){

        List<Materia> listaMateria = new ArrayList<>();

        listaMateria.add(criarMateria());
        listaMateria.add(criarMateria());

        return listaMateria;
    }

    public static Aula criarAula(){

        Aula aula = new Aula();

        aula.setId(10);
        aula.setProfessor(UsuarioBuilder.criarUsuarioProfessor());
        aula.setTitulo("Aula");
        aula.setDescricao("Descrição");
        aula.setDuracaoSegundos(5);
        aula.setPreco(5.0);
        aula.setMateria(criarMateria());
        aula.setDataHora(LocalDateTime.now());
        aula.setLimiteParticipantes(5);
        aula.setAlunos(UsuarioBuilder.criarListaUsuario());
        aula.setStatus(Status.CONCLUIDA);

        return aula;
    }

    public static List<Aula> criarListaAula(){

        List<Aula> aulas = new ArrayList<>();
        aulas.add(criarAula());
        aulas.add(criarAula());

        return aulas;
    }

    public static Aula[] criarListaObjAula() {

        Aula[] aulas = new Aula[2];
        aulas[0] = criarAula();
        aulas[1] = criarAula();
        return aulas;
    }
}
