package school.sptech.ensine.service.disponibilidade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.ensine.domain.Disponibilidade;
import school.sptech.ensine.repository.DisponibilidadeRepository;
import school.sptech.ensine.service.disponibilidade.builder.DisponibilidadeBuilder;
import school.sptech.ensine.service.usuario.DisponibilidadeService;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DisponibilidadeServiceTest {

    @Mock
    private DisponibilidadeRepository repository;

    @InjectMocks
    private DisponibilidadeService service;

    @Test
    @DisplayName("Deve retornar lista de disponibilidade de acordo com o id do professor")
    void deveRetornarListaDisponilidadeQuandoIdProfessor(){

        // given
        Disponibilidade disponibilidade = DisponibilidadeBuilder.criarDisponibilidade();

        // when
        Mockito.when(repository.findByProfessorId(10)).
                thenReturn(List.of(disponibilidade));

        // then
        List<Disponibilidade> resultado = service.getDisponibilidadesByProfessorId(10);

        // assert
        assertFalse(resultado.isEmpty());
    }
}
