package school.sptech.ensine.service.usuario.dto;

import school.sptech.ensine.domain.Mensagem;

import java.time.LocalDateTime;

public class MensagemPresentationDto {
    private Integer id;
    private String texto;
    private String nomeUsuario;
    private LocalDateTime dataEnvio;

    public MensagemPresentationDto(Mensagem mensagem) {
        this.id = mensagem.getId();
        this.texto = mensagem.getConteudo();
        this.nomeUsuario = mensagem.getUsuario().getNome();
        this.dataEnvio = mensagem.getDataEnvio();
    }

    public MensagemPresentationDto(Integer id, String texto, String nomeUsuario, LocalDateTime dataEnvio) {
        this.id = id;
        this.texto = texto;
        this.nomeUsuario = nomeUsuario;
        this.dataEnvio = dataEnvio;
    }

    public MensagemPresentationDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
