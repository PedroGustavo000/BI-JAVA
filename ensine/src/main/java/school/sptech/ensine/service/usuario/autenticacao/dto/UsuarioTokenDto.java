package school.sptech.ensine.service.usuario.autenticacao.dto;

import school.sptech.ensine.domain.Materia;

import java.util.List;

public class UsuarioTokenDto {
    private Long userId;
    private String nome;
    private String email;
    private String token;
    private String foto;
    private List<Materia> disciplinas;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Materia> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Materia> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
