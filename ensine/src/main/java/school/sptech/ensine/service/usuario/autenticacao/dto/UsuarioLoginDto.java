package school.sptech.ensine.service.usuario.autenticacao.dto;

public class UsuarioLoginDto {
    private String email;
    private String senha;

    public UsuarioLoginDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsuarioLoginDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
