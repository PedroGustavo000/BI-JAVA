package school.sptech.ensine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.ensine.domain.Formacao;

import java.util.List;

public interface FormacaoRepository extends JpaRepository<Formacao, Integer> {
    List<Formacao> findByProfessorId(int id);
}
