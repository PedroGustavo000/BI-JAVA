package school.sptech.ensine.service.mensagem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.ensine.domain.Aula;
import school.sptech.ensine.domain.Chat;
import school.sptech.ensine.domain.Mensagem;
import school.sptech.ensine.repository.ChatRepository;
import school.sptech.ensine.repository.MensagemRepository;
import school.sptech.ensine.service.aula.builder.AulaBuilder;
import school.sptech.ensine.service.mensagem.builder.MensagemBuilder;
import school.sptech.ensine.service.usuario.MensagemService;
import school.sptech.ensine.service.usuario.dto.MensagemPresentationDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MensagemServiceTest {

    @Mock
    private MensagemRepository repository;

    @Mock
    private ChatRepository repositoryChat;

    @InjectMocks
    private MensagemService service;

    @Test
    @DisplayName("Deve retornar uma mensagem após informar a mesma")
    void deveRetornarNovaMensagem(){

        // given
        Mensagem mensagem = MensagemBuilder.criarMensagem();

        // when
        Mockito.when(repository.save(mensagem)).thenReturn(mensagem);

        // then
        Mensagem resultado = service.enviarMensagem(mensagem);

        // assert
        assertEquals(mensagem.getId(), resultado.getId());
        assertEquals(mensagem.getChat(), resultado.getChat());
        assertEquals(mensagem.getUsuario(), resultado.getUsuario());
        assertEquals(mensagem.getConteudo(), resultado.getConteudo());
        assertEquals(mensagem.getDataEnvio(), resultado.getDataEnvio());
    }

    @Test
    @DisplayName("Deve retornar lista com duas mensagens quando o ID da aula for válido")
    void deveRetornarListaComDuasMensagensQuandoIdAulaValido() {
        // given
        List<Mensagem> listaMensagens = MensagemBuilder.criarListaMensagem();
        Aula aula = AulaBuilder.criarAula();
        int qtdMensagens = 2;

        // when
        Mockito.when(repository.findByChat_Id(aula.getId())).thenReturn(listaMensagens);

        // then
        List<MensagemPresentationDto> resultado = service.listarMensagens(aula.getId());

        // assert
        assertFalse(resultado.isEmpty());
        assertFalse(listaMensagens.isEmpty());
        assertEquals(qtdMensagens, resultado.size());
        assertEquals(qtdMensagens, listaMensagens.size());
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando o id da aula for inválido")
    void deveRetornarListaVaziaQuandoIdInvalido(){

        // given
        int qtdAulas = 0;

        // when
        Mockito.when(repository.findByChat_Id(99)).thenReturn(new ArrayList<>());

        // then
        List<MensagemPresentationDto> resultado = service.listarMensagens(99);

        // assert
        assertEquals(qtdAulas, resultado.size());
    }

    @Test
    @DisplayName("Deve criar novo chat")
    void deveCriarNovoChat(){

        // given
        Chat chat = MensagemBuilder.criarChat();

        // when
        Mockito.when(repositoryChat.save(chat)).thenReturn(chat);

        // then
        Chat resultado = service.criarChat(chat);

        // assert
        assertEquals(chat.getId(), resultado.getId());
        assertEquals(chat.getAula(), resultado.getAula());
        assertEquals(chat.getParticipantes(), resultado.getParticipantes());
    }
}
