package school.sptech.ensine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.ensine.domain.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
}
