package school.sptech.ensine.service.fila;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import school.sptech.ensine.service.fila.builder.FilaObjBuilder;
import school.sptech.ensine.util.FilaObj;

import static org.junit.jupiter.api.Assertions.*;

public class FilaObjTest {

    @Test
    @DisplayName("Retorna fila com 3 dados cadastrados0")
    void deveRetornarFilaComTresDados(){

        // given
        FilaObj<Integer> filaObj = FilaObjBuilder.criarFilaIncompleta();
        int tamanhoEsperado = 3;

        // then
        int resultado = filaObj.getTamanho();

        // assert
        assertEquals(resultado, tamanhoEsperado);
    }

    @Test
    @DisplayName("Deve retornar true caso a fila esteja cheia")
    void deveRetornarTrueQuandoFilaCheia(){

        // given
        FilaObj<Integer> filaObj = FilaObjBuilder.criarFilaCheia();

        // assert
        assertTrue(filaObj.isFull());
    }

    @Test
    @DisplayName("Deve retornar false caso a fila não esteja cheia")
    void deveRetornarFalseQuandoFilaNaoCheia(){

        // given
        FilaObj<Integer> filaObj = FilaObjBuilder.criarFilaIncompleta();

        // assert
        assertFalse(filaObj.isFull());
    }

    @Test
    @DisplayName("Deve retornar true quando lista estiver vazia")
    void deveRetornarTrueQuandoFilaVazia(){

        // given
        FilaObj<Integer> filaObj = new FilaObj<>(5);

        // assert
        assertTrue(filaObj.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar false quando lista não estiver vazia")
    void deveRetornarFalseQuandoFilaNaoVazia(){

        // given
        FilaObj<Integer> filaObj = FilaObjBuilder.criarFilaIncompleta();

        // assert
        assertFalse(filaObj.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar 1 como ultimo elemento")
    void deveRetornarUmComoUltimo(){

        // given
        FilaObj<Integer> filaObj = FilaObjBuilder.criarFilaCheia();
        int numEsperado = 1;

        // then
        Integer resultado = filaObj.peek();

        // assert
        assertEquals(numEsperado, resultado);
    }

    @Test
    @DisplayName("Deve retornar excessao quando tentar inserir em uma lista cheia")
    void deveRetornarExcessaoQuandoListaCheia(){

        // given
        FilaObj<Integer> filaObj = FilaObjBuilder.criarFilaCheia();
        String resultEsperado = "Fila cheia!";

        // then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            filaObj.insert(7);
        });

        // assert
        assertEquals(resultEsperado, exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar cinco como tamanho da fila e o antigo primeiro elemento")
    void deveRetornarTamanhoNovoUltimoElemento(){

        // given
        FilaObj<Integer> filaObj = FilaObjBuilder.criarFilaCheia();
        int numEsperado = 1;
        int tamanhoEsperado = 5;

        // then
        int resultado = filaObj.poll();

        // assert
        assertEquals(numEsperado, resultado);
        assertEquals(tamanhoEsperado, filaObj.getTamanho());
    }

    @Test
    @DisplayName("Deve retornar excessao quando tenta exibir e fila estiver vazia")
    void deveRetornarExcessaoQuandoExibeEFilaVazia(){

        // given
        FilaObj<Integer> filaObj = new FilaObj<>(5);
        String resultEsperado = "Fila vazia!";

        // then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            filaObj.exibe();
        });

        // assert
        assertEquals(resultEsperado, exception.getMessage());
    }
}
