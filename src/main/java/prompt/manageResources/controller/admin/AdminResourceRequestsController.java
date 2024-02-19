package prompt.manageResources.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import prompt.manageResources.model.dto.AccountEquipmentDto;
import prompt.manageResources.model.dto.EquipmentDto;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.model.helper.CurrentUser;
import prompt.manageResources.model.mapper.AccountEquipmentMapper;
import prompt.manageResources.model.response.SearchResponse;
import prompt.manageResources.service.AccountEquipmentService;
import prompt.manageResources.service.EquipmentService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/resource-requests")
public class AdminResourceRequestsController {

    private final AccountEquipmentService accountEquipmentService;
    private final EquipmentService equipmentService;

    @GetMapping("")
    public String index(@PageableDefault(page = 0, size = 10) Pageable pageable, AccountEquipmentDto accountEquipmentDto, Model model, HttpServletRequest request) {
        Page<AccountEquipmentDto> results = accountEquipmentService.findAllByConditions(accountEquipmentDto, pageable);

        model.addAttribute("results", results.getContent());
        model.addAttribute("resultCnt", results.getTotalElements());
        model.addAttribute("pagination", results);
        model.addAttribute("request", request);
        return "/apps/admin/resource-requests/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        AccountEquipmentDto accountEquipmentDto = AccountEquipmentMapper.INSTANCE.toDto(accountEquipmentService.findById(id));
        model.addAttribute("accountEquipment", accountEquipmentDto);
        return "/apps/admin/resource-requests/show";
    }

    @PostMapping("/update")
    public String update(AccountEquipmentDto accountEquipmentDto, @CurrentUser Account currentUser) {
        String equipmentId = accountEquipmentDto.getId().toString();
        accountEquipmentService.confirmEquipmentRequest(accountEquipmentDto, currentUser);
        return "redirect:/admin/resource-requests/".concat(equipmentId);
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?> search(@PageableDefault(page = 0, size = 1) Pageable pageable, @ModelAttribute EquipmentDto equipmentDto) {
        Page<EquipmentDto> results = equipmentService.findAllByConditions(equipmentDto, pageable);
        return ResponseEntity.ok(new SearchResponse<>(results));
    }

}
