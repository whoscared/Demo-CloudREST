package whoscared.cloudrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whoscared.cloudrest.models.Data;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
}
