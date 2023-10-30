package school.sptech.ensine.service.usuario.dto.mapper;

import school.sptech.ensine.domain.Usuario;
import school.sptech.ensine.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.ensine.service.usuario.dto.UsuarioCriacaoDto;

public class UsuarioMapper {

    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setDataNasc(usuarioCriacaoDto.getDataNasc());
        usuario.setProfessor(usuarioCriacaoDto.isProfessor());
        usuario.setMaterias(usuarioCriacaoDto.getMaterias());
        usuario.setFoto(usuarioCriacaoDto.getFoto());
        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId((long) usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);
        usuarioTokenDto.setFoto(usuario.getFoto());
        usuarioTokenDto.setDisciplinas(usuario.getMaterias());

        return usuarioTokenDto;
    }
}

