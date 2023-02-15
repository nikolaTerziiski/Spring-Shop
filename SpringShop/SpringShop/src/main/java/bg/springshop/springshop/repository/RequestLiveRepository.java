package bg.springshop.springshop.repository;

import bg.springshop.springshop.model.entity.RequestLive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLiveRepository extends JpaRepository<RequestLive, Long> {
}
