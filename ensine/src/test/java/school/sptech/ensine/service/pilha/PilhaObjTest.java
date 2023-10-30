package school.sptech.ensine.service.pilha;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import school.sptech.ensine.service.fila.builder.FilaObjBuilder;
import school.sptech.ensine.service.pilha.builder.PilhaObjBuilder;
import school.sptech.ensine.util.FilaObj;
import school.sptech.ensine.util.PilhaObj;

import static org.junit.jupiter.api.Assertions.*;

public class PilhaObjTest {

    @Test
    @DisplayName("Deve retornar true quando pilha estiver vazia")
    void deveRetornarTrueQuandoPilhaVazia(){

        // given
        PilhaObj<Integer> pilhaObj = new PilhaObj<>(5);

        // then
        boolean resultado = pilhaObj.isEmpty();

        // assert
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Deve retornar false quando pilha não estiver vazia")
    void deveRetornarFalseQuandoPilhaNaoVazia(){

        // given
        PilhaObj<Integer> pilhaObj = PilhaObjBuilder.criarPilhaIncompleta();

        // then
        boolean resultado = pilhaObj.isEmpty();

        // assert
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Deve retornar true caso a pilha esteja cheia")
    void deveRetornarTrueQuandoPilhaCheia(){

        // given
        PilhaObj<Integer> pilhaObj = PilhaObjBuilder.criarPilhaCheia();

        // assert
        assertTrue(pilhaObj.isFull());
    }

    @Test
    @DisplayName("Deve retornar false caso a pilha não esteja cheia")
    void deveRetornarFalseQuandoPilhaNaoCheia(){

        // given
        PilhaObj<Integer> pilhaObj = PilhaObjBuilder.criarPilhaIncompleta();

        // assert
        assertFalse(pilhaObj.isFull());
    }

    @Test
    @DisplayName("Deve retornar excessao quando tentar inserir em uma pilha cheia")
    void deveRetornarExcessaoQuandoInserirListaCheia(){

        // given
        PilhaObj<Integer> pilhaObj = PilhaObjBuilder.criarPilhaCheia();
        String resultEsperado = "Pilha Cheia!";

        // then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pilhaObj.push(7);
        });

        // assert
        assertEquals(resultEsperado, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar 6 como ultimo elemento na pilha depois de pop")
    void deveRetornarSeisComoUltimoElementoDepoisPop(){

        // given
        PilhaObj<Integer> pilhaObj = PilhaObjBuilder.criarPilhaCheia();
        int elementoEsperado = 6;

        // then
        int resultado = pilhaObj.pop();

        // asserts
        assertEquals(elementoEsperado, resultado);
    }

    @Test
    @DisplayName("Deve retornar 6 como ultimo elemento na pilha ")
    void deveRetornarSeisComoUltimoElemento(){

        // given
        PilhaObj<Integer> pilhaObj = PilhaObjBuilder.criarPilhaCheia();
        int elementoEsperado = 6;

        // then
        int resultado = pilhaObj.pop();

        // asserts
        assertEquals(elementoEsperado, resultado);
    }
}
