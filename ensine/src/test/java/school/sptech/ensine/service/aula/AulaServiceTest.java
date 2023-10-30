package school.sptech.ensine.service.aula;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.ensine.domain.Aula;
import school.sptech.ensine.domain.ListaObj;
import school.sptech.ensine.domain.Professor;
import school.sptech.ensine.enumeration.Status;
import school.sptech.ensine.repository.AulaRepository;
import school.sptech.ensine.repository.MateriaRepository;
import school.sptech.ensine.repository.UsuarioRepository;
import school.sptech.ensine.service.aula.builder.AulaBuilder;
import school.sptech.ensine.service.usuario.AulaService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AulaServiceTest {

    @Mock
    private AulaRepository repository;

    @Mock
    private MateriaRepository materiaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AulaService service;

    @Test
    @DisplayName("Deve retornar a quantidade de aulas cadastradas")
    void deveRetornarUmaAulaQuandoTiverApenasUmaCadastrada(){

        // given
        Aula aula = AulaBuilder.criarAula();
        int qtdAulas = 1;

        // when
        Mockito.when(repository.count()).thenReturn((long) qtdAulas);

        // then
        int resultado = service.qtdeAulas();

        // assert
        assertEquals(qtdAulas, resultado);
    }

    @Test
    @DisplayName("Deve retornar excessao quando a lista de aulas estiver vazia")
    void deveRetornarListaComDuasAulasQuandoListaTiverAulas(){

        // given
        List<Aula> listaAula = AulaBuilder.criarListaAula();
        int qtdAulas = 2;

        // when
        Mockito.when(repository.findAll()).thenReturn(listaAula);

        // then
        List<Aula> resultado = service.aulas();

        // assert
        assertFalse(listaAula.isEmpty());
        assertFalse(resultado.isEmpty());
        assertEquals(qtdAulas, resultado.size());
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não houver aulas cadastradas")
    void deveRetornarListaVaziaQuandoNaoTiverAulasCadastradas(){

        // given
        int qtdAulas = 0;

        // when
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());

        // then
        List<Aula> resultado = service.aulas();

        // assert
        assertTrue(resultado.isEmpty());
        assertEquals(qtdAulas, resultado.size());
    }

    @Test
    @DisplayName("Deve retornar uma lista de aulas de acordo com o status")
    void deveRetornarListaDeDuasAulasPorStatus() {

        // given
        String status = "Teste";
        Aula[] listaAula = AulaBuilder.criarListaObjAula();

        // Mock
        Mockito.when(repository.countByStatus(status)).thenReturn((long) 2);
        Mockito.when(repository.findByStatus(Mockito.eq(status)))
                .thenReturn(Arrays.asList(listaAula));

        // when
        ListaObj<Aula> resultado = service.getAulasPorStatus(status);

        // then
        assertNotNull(resultado);
        assertArrayEquals(listaAula, resultado.getVetor());
    }

    @Test
    @DisplayName("Deve retornar excessao quando buscar por id inválido")
    void deveRetornarExecessaQuandoIdInvalido(){

        // given
        int idInexistente = 99;

        // when
        Mockito.when(repository.findById(idInexistente)).thenReturn(Optional.empty());

        // then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.encontraAulaId(idInexistente);
        });

        // asssert
        assertEquals("Essa aula não existe", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar Aula quando o id for válido")
    void deveRetornarAulaQuandoIdVálido(){

        // given
        Aula aula = AulaBuilder.criarAula();

        // when
        Mockito.when(repository.findById(aula.getId())).thenReturn(Optional.of(aula));

        // then
        Optional<Aula> resultado = service.encontraAulaId(aula.getId());

        // assert
        assertNotNull(resultado);
        assertEquals(resultado.get().getId(), aula.getId());
        assertEquals(resultado.get().getProfessor(), aula.getProfessor());
        assertEquals(resultado.get().getTitulo(), aula.getTitulo());
        assertEquals(resultado.get().getDescricao(), aula.getDescricao());
        assertEquals(resultado.get().getDuracaoSegundos(), aula.getDuracaoSegundos());
        assertEquals(resultado.get().getPreco(), aula.getPreco());
        assertEquals(resultado.get().getMateria(), aula.getMateria());
        assertEquals(resultado.get().getDataHora(), aula.getDataHora());
        assertEquals(resultado.get().getLimiteParticipantes(), aula.getLimiteParticipantes());
        assertEquals(resultado.get().getAlunos(), aula.getAlunos());
        assertEquals(resultado.get().getStatus(), aula.getStatus());
    }

    @Test
    @DisplayName("Deve retornar false quando o id do não for válido")
    void deveRetornarFalseQuandoIdNaoExistir() {

        // given
        int idInexistente = 99;

        // when
        Mockito.when(repository.existsById(idInexistente)).thenReturn(false);

        // then
        Boolean resultado = service.existePorId(idInexistente);

        // assert
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Deve retornar true quando o id for válido")
    void deveRetornarTrueQuandoIdExistir(){

        // given
        Aula aula = AulaBuilder.criarAula();

        // when
        Mockito.when(repository.existsById(aula.getId())).thenReturn(true);

        // then
        Boolean resultado = service.existePorId(aula.getId());

        // assert
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Deve retornar aula quando o id do aluno for válido")
    void deveRetornarListaComDuasAulasQuandoIdAlunoValido(){

        // given
        List<Aula> listaAulas = AulaBuilder.criarListaAula();
        int qtdAulas = 2;

        // when
        Mockito.when(repository.findByAlunosId(10)).thenReturn(listaAulas);

        // then
        List<Aula> resultado = service.encontraAulaPeloIdAluno(10);

        // assert
        assertFalse(resultado.isEmpty());
        assertFalse(listaAulas.isEmpty());
        assertEquals(qtdAulas, resultado.size());
        assertEquals(qtdAulas, listaAulas.size());
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando o id do aluno for inválido")
    void deveRetornarListaVaziaQuandoIdInvalido(){

        // given
        List<Aula> listaAula = AulaBuilder.criarListaAula();
        int qtdAulas = 0;

        // when
        Mockito.when(repository.findByAlunosId(99)).thenReturn(new ArrayList<>());

        // then
        List<Aula> resultado = service.encontraAulaPeloIdAluno(99);

        // assert
        assertFalse(listaAula.isEmpty());
        assertEquals(qtdAulas, resultado.size());
    }

    @Test
    @DisplayName("Deve retornar a quantidade de professores cadastradas")
    void deveRetornarDuasAulaQueTemNomeProfessor(){

        // given
        List<Aula> listaAulas = AulaBuilder.criarListaAula();
        long qtdAulas = 2;

        // when
        Mockito.when(repository.countByProfessorNomeEqualsIgnoreCase("Professor")).thenReturn((long) qtdAulas);

        // then
        long resultado = service.countProfessorNome("Professor");

        // assert
        assertEquals(qtdAulas, resultado);
    }

    @Test
    @DisplayName("Deve criar quando dados forém válidos")
    void deveCriarAulaDadosValidos(){

        // given
        Aula aula = AulaBuilder.criarAula();

        // when
        Mockito.when(usuarioRepository.findProfessorById(aula.getProfessor().getId())).
                thenReturn(Optional.of(aula.getProfessor()));

        Mockito.when(materiaRepository.findByNomeContainingIgnoreCase(aula.getMateria().getNome())).
                thenReturn(Optional.of(aula.getMateria()));

        Mockito.when(repository.save(aula)).thenReturn(aula);

        // then
        Aula resultado = service.aulaNova(aula);

        // assert
        assertNotNull(resultado);
        assertEquals(resultado.getId(), aula.getId());
        assertEquals(resultado.getProfessor(), aula.getProfessor());
        assertEquals(resultado.getTitulo(), aula.getTitulo());
        assertEquals(resultado.getDescricao(), aula.getDescricao());
        assertEquals(resultado.getDuracaoSegundos(), aula.getDuracaoSegundos());
        assertEquals(resultado.getPreco(), aula.getPreco());
        assertEquals(resultado.getMateria(), aula.getMateria());
        assertEquals(resultado.getDataHora(), aula.getDataHora());
        assertEquals(resultado.getLimiteParticipantes(), aula.getLimiteParticipantes());
        assertEquals(resultado.getAlunos(), aula.getAlunos());
        assertEquals(resultado.getStatus(), aula.getStatus());
    }

    @Test
    @DisplayName("Deve retornar aula com status atualizado quando id for válido")
    void deveRetornarAulaNovoStatusQuandoIdVálido(){

        // given
        Aula aula = AulaBuilder.criarAula();
        Status status = Status.CONCLUIDA;

        // when
        Mockito.when(repository.findById(aula.getId())).thenReturn(Optional.of(aula));

        // then
        Optional<Aula> resultado = service.atualizarStatusAula(aula.getId(), status);

        // assert
        assertEquals(resultado.get().getStatus(), status);
    }

    @Test
    @DisplayName("Deve retornar excessao quando o id informado for inválido")
    void deveRetornarExcessaoQuandIdInvalido(){

        // given
        int idInexistente = 99;

        // when
        Mockito.when(repository.findById(idInexistente)).thenReturn(Optional.empty());

        // then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.atualizarStatusAula(idInexistente, Status.CONCLUIDA);
        });

        // assert
        assertEquals("Essa aula não existe", exception.getMessage());
    }
}
