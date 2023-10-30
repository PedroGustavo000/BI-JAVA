package school.sptech.ensine.service.usuario.dto;

import school.sptech.ensine.domain.Usuario;

public class UsuarioDto {

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    public UsuarioDto(UsuarioDto usuarioDto) {
        this.id = usuarioDto.getId();
        this.nome = usuarioDto.getNome();
        this.email = usuarioDto.getEmail();
    }

    private int id;

    private String nome;

    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}

