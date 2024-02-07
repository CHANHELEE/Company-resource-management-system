package prompt.manageResources.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import prompt.manageResources.model.dto.EquipmentDto;
import prompt.manageResources.repository.EquipmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public Page<EquipmentDto> findAllByConditions(EquipmentDto equipmentDto, Pageable pageable) {
        return equipmentRepository.findAllByConditions(equipmentDto, pageable);
    }
}
