package prompt.manageResources.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import prompt.manageResources.model.dto.EquipmentDto;
import prompt.manageResources.model.dto.EquipmentOwnershipHistDto;
import prompt.manageResources.model.mapper.EquipmentMapper;
import prompt.manageResources.service.EquipmentOwnershipService;
import prompt.manageResources.service.EquipmentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/resources")
@Slf4j
public class AdminResourcesController {
    private final EquipmentService equipmentService;
    private final EquipmentOwnershipService equipmentOwnershipService;

    @GetMapping("")
    public String index(@PageableDefault(page = 0, size = 10) Pageable pageable, EquipmentDto equipmentDto, Model model, HttpServletRequest request) {
        Page<EquipmentDto> results = equipmentService.findAllByConditions(equipmentDto, pageable);

        model.addAttribute("results", results.getContent());
        model.addAttribute("resultCnt", results.getTotalElements());
        model.addAttribute("pagination", results);
        model.addAttribute("request", request);
        return "/apps/admin/resources/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, EquipmentOwnershipHistDto equipmentOwnershipHistDto, Model model) {
        EquipmentDto result = EquipmentMapper.INSTANCE.toDto(equipmentService.findById(id));
        equipmentOwnershipHistDto.setEquipmentId(id);
        List<EquipmentOwnershipHistDto> results = equipmentOwnershipService.findAllByConditions(equipmentOwnershipHistDto);

        model.addAttribute("result", result);
        model.addAttribute("histResults", results);
        return "/apps/admin/resources/show";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute EquipmentDto equipmentDto) {
        equipmentService.update(id, equipmentDto);
        return "redirect:/admin/resources/".concat(id.toString());
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            equipmentService.delete(id);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }
}
