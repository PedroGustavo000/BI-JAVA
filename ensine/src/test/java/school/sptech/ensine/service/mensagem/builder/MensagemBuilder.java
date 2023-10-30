package school.sptech.ensine.service.mensagem.builder;

import school.sptech.ensine.domain.Chat;
import school.sptech.ensine.domain.Mensagem;
import school.sptech.ensine.service.aula.builder.AulaBuilder;
import school.sptech.ensine.service.usuario.builder.UsuarioBuilder;
import school.sptech.ensine.service.usuario.dto.MensagemPresentationDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MensagemBuilder {

    public MensagemBuilder(){

        throw new IllegalStateException("Classe Utilitária");
    }

    public static Chat criarChat(){

        Chat chat = new Chat();

        chat.setId(10);
        chat.setAula(AulaBuilder.criarAula());
        chat.setParticipantes(UsuarioBuilder.criarListaUsuario());

        return chat;
    }

    public static Mensagem criarMensagem(){

        Mensagem mensagem = new Mensagem();

        mensagem.setId(10);
        mensagem.setChat(criarChat());
        mensagem.setUsuario(UsuarioBuilder.criarUsuarioAluno());
        mensagem.setConteudo("Mensagem");
        mensagem.setDataEnvio(LocalDateTime.now());

        return mensagem;
    }

    public static MensagemPresentationDto criarMensagemPresentationDto(){

        MensagemPresentationDto dto = new MensagemPresentationDto();

        dto.setId(10);
        dto.setTexto("MensagemPresentatiom");
        dto.setNomeUsuario("Usuario01");
        dto.setDataEnvio(LocalDateTime.now());

        return dto;
    }

    public static List<MensagemPresentationDto> criarListaMensagemPresentation(){

        List<MensagemPresentationDto> listaMensagem = new ArrayList<>();
        listaMensagem.add(criarMensagemPresentationDto());
        listaMensagem.add(criarMensagemPresentationDto());

        return listaMensagem;
    }

    public static List<Mensagem> criarListaMensagem(){

        List<Mensagem> listaMensagem = new ArrayList<>();
        listaMensagem.add(criarMensagem());
        listaMensagem.add(criarMensagem());

        return listaMensagem;
    }
}
