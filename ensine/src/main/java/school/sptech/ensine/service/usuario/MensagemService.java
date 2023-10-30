package school.sptech.ensine.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import school.sptech.ensine.api.security.jwt.GerenciadorTokenJwt;
import school.sptech.ensine.domain.Chat;
import school.sptech.ensine.domain.ListaObj;
import school.sptech.ensine.domain.Mensagem;
import school.sptech.ensine.repository.AulaRepository;
import school.sptech.ensine.repository.ChatRepository;
import school.sptech.ensine.repository.MateriaRepository;
import school.sptech.ensine.repository.MensagemRepository;
import school.sptech.ensine.service.usuario.dto.MensagemPresentationDto;
import school.sptech.ensine.util.FilaObj;
import school.sptech.ensine.util.PilhaObj;

import java.util.ArrayList;
import java.util.List;

@Service
public class MensagemService {
    @Autowired
    private MensagemRepository mensagemRepository;
    @Autowired
    private ChatRepository chatRepository;

    private final int qtdMaxMensagens = 20;
    public Mensagem enviarMensagem(Mensagem mensagem){
        mensagem = mensagemRepository.save(mensagem);
        return mensagem;
    }

    public List<MensagemPresentationDto> listarMensagens(Integer aulaId){
        List<Mensagem> mensagens = mensagemRepository.findByChat_Id(aulaId);
        List<MensagemPresentationDto> mensagensDto = new ArrayList<>();
        PilhaObj<MensagemPresentationDto> pilha = new PilhaObj(qtdMaxMensagens);
        FilaObj<MensagemPresentationDto> fila = new FilaObj(qtdMaxMensagens);
        int contador = mensagens.size() - qtdMaxMensagens;
        if(contador < 0){
            contador = 0;
        }
        while(contador < mensagens.size() && !pilha.isFull()){
            pilha.push(new MensagemPresentationDto(mensagens.get(contador)));
            contador ++;
        }
        while(!pilha.isEmpty()){
            fila.insert(pilha.pop());
        }
        while(!fila.isEmpty()){
            mensagensDto.add(fila.poll());
        }
        return mensagensDto;
    }
    public Chat criarChat(Chat chat){
        Chat newChat = chatRepository.save(chat);
        return newChat;
    }
}
