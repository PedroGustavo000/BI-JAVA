package school.sptech.ensine.service.usuario.builder;

import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.ensine.domain.Avaliacao;
import school.sptech.ensine.domain.Materia;
import school.sptech.ensine.domain.Professor;
import school.sptech.ensine.domain.Usuario;
import school.sptech.ensine.service.aula.builder.AulaBuilder;
import school.sptech.ensine.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.ensine.service.usuario.dto.ProfessorCriacaoDto;
import school.sptech.ensine.service.usuario.dto.UsuarioCriacaoDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioBuilder {
    private static PasswordEncoder passwordEncoder;

    public static void setPasswordEncoder(PasswordEncoder encoder) {
        passwordEncoder = encoder;
    }

    private  UsuarioBuilder(){

        throw new IllegalStateException("Classe Utilitária");
    }

    public static Avaliacao criarAvaliacao(){

        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setId(10);
        avaliacao.setUsuario(criarUsuarioAluno());
        avaliacao.setNota(4.0);

        return avaliacao;
    }

    public static List<Avaliacao> criarListaAvaliacao(){

        List<Avaliacao> lista = new ArrayList<>();

        lista.add(criarAvaliacao());
        lista.add(criarAvaliacao());
        lista.add(criarAvaliacao());

        return lista;
    }

    public static Usuario criarUsuarioAluno() {

        Usuario usuario = new Usuario();

        usuario.setId(10);
        usuario.setProfessor(false);
        usuario.setNome("Aluno");
        usuario.setEmail("usuario@gmail.com");
        usuario.setSenha("senha1234");
        usuario.setDataNasc(LocalDate.of(1990, 1, 1));
        usuario.setMaterias(AulaBuilder.criarListaMateria());

        return usuario;
    }

    public static UsuarioCriacaoDto criarUsuarioAlunoCriacaoDto() {

        UsuarioCriacaoDto dto = new UsuarioCriacaoDto();

        dto.setProfessor(false);
        dto.setNome("Aluno");
        dto.setEmail("usuario@gmail.com");
        dto.setSenha("senha1234");
        dto.setDataNasc(LocalDate.of(1990, 1, 1));
        dto.setMaterias(AulaBuilder.criarListaMateria());
        return dto;
    }

    public static Professor criarUsuarioProfessor() {

        Professor professor = new Professor();

        professor.setId(10);
        professor.setProfessor(true);
        professor.setNome("Professor");
        professor.setEmail("usuario@gmail.com");
        professor.setSenha("senha1234");
        professor.setDataNasc(LocalDate.now());
        professor.setMaterias(AulaBuilder.criarListaMateria());
        professor.setAvaliacoes(criarListaAvaliacao());

        return professor;
    }


    public static UsuarioLoginDto criarUsuarioLoginDto(){

        UsuarioLoginDto dto = new UsuarioLoginDto();

        dto.setEmail("usuario@gmail.com");
        dto.setSenha("senha1234");

        return dto;
    }

    public static ProfessorCriacaoDto criarUsuarioProfessorCriacaoDto() {

        ProfessorCriacaoDto dto = new ProfessorCriacaoDto();

        dto.setProfessor(true);
        dto.setNome("Professor");
        dto.setEmail("usuario@gmail.com");
        dto.setSenha("senha1234");
        dto.setDataNasc(LocalDate.of(1990, 1, 1));
        dto.setMaterias(AulaBuilder.criarListaMateria());
        dto.setDescricao("Descrição Professor");

        return dto;
    }

    public static List<Professor> criarListaProfessor(){

        List<Professor> professor = new ArrayList<>();
        professor.add(criarUsuarioProfessor());
        professor.add(criarUsuarioProfessor());
        return professor;
    }

    public static List<Usuario> criarListaUsuario(){

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(criarUsuarioAluno());
        usuarios.add(criarUsuarioAluno());
        return usuarios;
    }
}
