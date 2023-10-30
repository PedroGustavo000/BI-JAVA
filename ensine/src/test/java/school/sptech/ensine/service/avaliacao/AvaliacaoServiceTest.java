package school.sptech.ensine.service.avaliacao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.ensine.domain.Avaliacao;
import school.sptech.ensine.domain.Professor;
import school.sptech.ensine.repository.AvaliacaoRepository;
import school.sptech.ensine.service.avaliacao.builder.AvaliacaoBuilder;
import school.sptech.ensine.service.usuario.AvaliacaoService;
import school.sptech.ensine.service.usuario.builder.UsuarioBuilder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AvaliacaoServiceTest {

    @Mock
    private AvaliacaoRepository repository;

    @InjectMocks
    private AvaliacaoService service;

//    @Test
//    @DisplayName("Deve retornar avalicao quando os dados forem válidos")
//    void deveRetornarAvaliacao(){
//
//        // given
//        Avaliacao avaliacao = AvaliacaoBuilder.criarAvaliacao();
//
//        // when
//        Mockito.when(repository.save(avaliacao)).
//                thenReturn(avaliacao);
//
//        // then
//        Avaliacao resultado = service.criarAvaliacao(avaliacao);
//
//        // assert
//        assertEquals(resultado.getId(), avaliacao.getId());
//        assertEquals(resultado.getProfessor(), avaliacao.getProfessor());
//        assertEquals(resultado.getUsuario(), avaliacao.getUsuario());
//        assertEquals(resultado.getNota(), avaliacao.getNota());
//    }

    @Test
    @DisplayName("Deve retornar a média de notas de acordo com um professor")
    void deveRetornarMediaNotas(){

        // given
        Professor professor = UsuarioBuilder.criarUsuarioProfessor();
        double mediaEsperada = 4;

        // when
        Mockito.when(repository.findMeanNotaByProfessor(professor)).
                thenReturn(mediaEsperada);

        // then
        double resultado = service.getNotaByProfessor(professor);

        // assert
        assertEquals(resultado, mediaEsperada);
    }
}
