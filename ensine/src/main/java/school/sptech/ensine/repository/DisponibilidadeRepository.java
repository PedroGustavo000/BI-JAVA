package school.sptech.ensine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.ensine.domain.Disponibilidade;
import school.sptech.ensine.domain.Formacao;

import java.util.List;

public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Integer> {
    List<Disponibilidade> findByProfessorId(int id);
}
