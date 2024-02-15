package prompt.manageResources.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prompt.manageResources.model.dto.EquipmentOwnershipHistDto;
import prompt.manageResources.repository.EquipmentOwnershipHistRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentOwnershipService {
    private final EquipmentOwnershipHistRepository equipmentOwnershipHistRepository;

    public List<EquipmentOwnershipHistDto> findAllByConditions(EquipmentOwnershipHistDto equipmentOwnershipHistDto) {
        return equipmentOwnershipHistRepository.findAllByConditions(equipmentOwnershipHistDto);
    }
}
