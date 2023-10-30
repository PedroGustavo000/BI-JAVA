package school.sptech.ensine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.ensine.domain.Materia;
import school.sptech.ensine.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {

    Optional<Materia> findByNomeContainingIgnoreCase(String nome);

    List<Materia> findMateriaByUsuariosContains(Usuario usuario);
}
