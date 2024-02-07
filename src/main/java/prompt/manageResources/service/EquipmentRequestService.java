package prompt.manageResources.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import prompt.manageResources.model.dto.EquipmentRequestDto;
import prompt.manageResources.model.entity.Equipment;
import prompt.manageResources.model.entity.EquipmentRequest;
import prompt.manageResources.repository.EquipmentRequestRepository;

@Service
@RequiredArgsConstructor
public class EquipmentRequestService {
    private final EquipmentRequestRepository equipmentRequestRepository;

    public Page<EquipmentRequestDto> findAllByConditions(EquipmentRequestDto equipmentRequestDto, Pageable pageable) {
        return equipmentRequestRepository.findAllByConditions(equipmentRequestDto, pageable);
    }

    public EquipmentRequest findById(Long id) {
        return equipmentRequestRepository.findById(id).orElse(null);
    }
}
