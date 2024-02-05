package prompt.manageResources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prompt.manageResources.model.entity.MileageHist;

@Repository
public interface MileageHistRepository extends JpaRepository<MileageHist, Long> {
}
