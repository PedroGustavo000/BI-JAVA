package school.sptech.ensine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.ensine.domain.Aula;
import school.sptech.ensine.domain.Professor;
import school.sptech.ensine.domain.Usuario;
import school.sptech.ensine.enumeration.Privacidade;
import school.sptech.ensine.service.usuario.dto.ContagemAula;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public interface AulaRepository extends JpaRepository<Aula, Integer> {
    List<Aula> findByProfessorNomeEqualsIgnoreCase(String nome);
    List<Aula> findByStatus(String status);
    List<Aula> findByPrivacidade(Privacidade privacidade);
    Long countByStatus(String status);
    Long countByProfessorNomeEqualsIgnoreCase(String nome);
    List<Aula> findByAlunosId(int id);
    @Query("SELECT COUNT(a) " +
            "FROM Aula a " +
            "WHERE FUNCTION('YEAR', a.dataHora) = FUNCTION('YEAR', CURRENT_DATE) " +
            "AND FUNCTION('MONTH', a.dataHora) = FUNCTION('MONTH', CURRENT_DATE) " +
            "AND FUNCTION('DAY', a.dataHora) = FUNCTION('DAY', CURRENT_DATE)")
    Long countAulasMarcadasParaHoje();
    @Query("SELECT new school.sptech.ensine.service.usuario.dto.ContagemAula(a.materia.nome, COUNT(a), MONTH(a.dataHora)) " +
            "FROM Aula a " +
            "WHERE a.professor = :professor " +
            "GROUP BY a.materia.nome")
    List<ContagemAula> contagemAulas(@Param("professor") Professor professor);
    @Query("SELECT a FROM Aula a WHERE LOWER(TRANSLATE(a.descricao, 'áàãâäéèẽêëíìĩîïóòõôöúùũûüçÁÀÃÂÄÉÈẼÊËÍÌĨÎÏÓÒÕÔÖÚÙŨÛÜÇ', 'aaaaaeeeeiiiiiooooouuuuucAAAAAEEEEIIIIIOOOOOUUUUUC')) LIKE LOWER(CONCAT('%', TRANSLATE(:descricao, 'áàãâäéèẽêëíìĩîïóòõôöúùũûüçÁÀÃÂÄÉÈẼÊËÍÌĨÎÏÓÒÕÔÖÚÙŨÛÜÇ', 'aaaaaeeeeiiiiiooooouuuuucAAAAAEEEEIIIIIOOOOOUUUUUC'), '%'))")
    List<Aula> findByDescricaoContainingIgnoreCaseAndNormalize(@Param("descricao") String descricao);
    @Query("SELECT a FROM Aula a WHERE LOWER(TRANSLATE(a.materia.nome, 'áàãâäéèẽêëíìĩîïóòõôöúùũûüçÁÀÃÂÄÉÈẼÊËÍÌĨÎÏÓÒÕÔÖÚÙŨÛÜÇ', 'aaaaaeeeeiiiiiooooouuuuucAAAAAEEEEIIIIIOOOOOUUUUUC')) LIKE LOWER(CONCAT('%', TRANSLATE(:materia, 'áàãâäéèẽêëíìĩîïóòõôöúùũûüçÁÀÃÂÄÉÈẼÊËÍÌĨÎÏÓÒÕÔÖÚÙŨÛÜÇ', 'aaaaaeeeeiiiiiooooouuuuucAAAAAEEEEIIIIIOOOOOUUUUUC'), '%'))")
    List<Aula> findByMateriaContainingIgnoreCaseAndNormalize(@Param("materia") String materia);
    @Query("SELECT a FROM Aula a WHERE LOWER(TRANSLATE(a.titulo, 'áàãâäéèẽêëíìĩîïóòõôöúùũûüçÁÀÃÂÄÉÈẼÊËÍÌĨÎÏÓÒÕÔÖÚÙŨÛÜÇ', 'aaaaaeeeeiiiiiooooouuuuucAAAAAEEEEIIIIIOOOOOUUUUUC')) LIKE LOWER(CONCAT('%', TRANSLATE(:titulo, 'áàãâäéèẽêëíìĩîïóòõôöúùũûüçÁÀÃÂÄÉÈẼÊËÍÌĨÎÏÓÒÕÔÖÚÙŨÛÜÇ', 'aaaaaeeeeiiiiiooooouuuuucAAAAAEEEEIIIIIOOOOOUUUUUC'), '%'))")
    List<Aula> findByTituloContainingIgnoreCaseAndNormalize(@Param("titulo") String titulo);
    @Query("SELECT a FROM Aula a WHERE :usuario MEMBER OF a.alunos " +
            "AND a.professor = :professor AND a.status = school.sptech.ensine.enumeration.Status.CONCLUIDA")
    List<Aula> findByUsuarioAndProfessorAndStatusConcluida(@Param("usuario") Usuario usuario,
                                                  @Param("professor") Professor professor);
    @Query("SELECT new school.sptech.ensine.service.usuario.dto.ContagemAula(a.materia.nome, COUNT(a), FUNCTION('MONTH', a.dataHora)) " +
            "FROM Aula a " +
            "WHERE a.materia.nome IN ('Matematica', 'Lingua Portuguesa', 'Geografia', 'Historia', 'Biologia') " +
            "AND a.dataHora BETWEEN :start AND :end " +
            "GROUP BY a.materia.nome, FUNCTION('YEAR', a.dataHora), FUNCTION('MONTH', a.dataHora)")
    List<ContagemAula> countAulasByMateriaAndMonth(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("SELECT MONTH(a.dataHora), SUM(p.precoHoraAula) " +
            "FROM Aula a " +
            "JOIN Professor p ON a.professor = p " +
            "WHERE a.dataHora >= :start AND a.dataHora <= :end " +
            "GROUP BY MONTH(a.dataHora)")
    List<Object[]> totalValorArrecadadoUltimosTresMeses(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
