package prompt.manageResources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prompt.manageResources.model.entity.Mileage;

@Repository
public interface MileageRepository extends JpaRepository<Mileage, Long> {

}
