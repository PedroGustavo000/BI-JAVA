package school.sptech.ensine.service.usuario.dto.mapper;

import school.sptech.ensine.domain.Professor;
import school.sptech.ensine.domain.Usuario;
import school.sptech.ensine.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.ensine.service.usuario.dto.ProfessorCriacaoDto;
import school.sptech.ensine.service.usuario.dto.ProfessorResumoDto;

import java.util.Objects;

public class ProfessorMapper{
    public static Professor of(ProfessorCriacaoDto professorCriacaoDto) {
        Professor professor = new Professor();

        professor.setEmail(professorCriacaoDto.getEmail());
        professor.setNome(professorCriacaoDto.getNome());
        professor.setSenha(professorCriacaoDto.getSenha());
        professor.setDataNasc(professorCriacaoDto.getDataNasc());
        professor.setProfessor(professorCriacaoDto.isProfessor());
        professor.setDescricao(professorCriacaoDto.getDescricao());
        professor.setMaterias(professorCriacaoDto.getMaterias());
        professor.setFoto(professorCriacaoDto.getFoto());
        professor.setPrecoHoraAula(professorCriacaoDto.getPrecoHoraAula());

        return professor;
    }

    public static ProfessorResumoDto mapProfessorToProfessorResumoDto(Professor professor) {
        if(Objects.isNull(professor)) {
            return null;
        }
        return new ProfessorResumoDto(professor.getId(), professor.getNome(), professor.getEmail());
    }

    public static UsuarioTokenDto of(Usuario usuario, String token) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId((long) usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }
}
