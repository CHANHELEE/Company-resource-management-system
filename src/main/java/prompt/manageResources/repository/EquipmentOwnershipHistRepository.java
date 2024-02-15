package prompt.manageResources.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prompt.manageResources.model.entity.EquipmentOwnershipHist;
import prompt.manageResources.repository.customRepositroy.EquipmentOwnershipHistCustomRepository;

import java.util.Optional;

@Repository
public interface EquipmentOwnershipHistRepository extends JpaRepository<EquipmentOwnershipHist, Long>, EquipmentOwnershipHistCustomRepository {

    @EntityGraph(attributePaths = {"equipment", "account"})
    Optional<EquipmentOwnershipHist> findAllById(Long id);
}
