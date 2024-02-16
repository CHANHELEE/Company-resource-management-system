package prompt.manageResources.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import prompt.manageResources.model.dto.AccountDto;
import prompt.manageResources.model.dto.EquipmentDto;
import prompt.manageResources.model.dto.PrivateAccountDto;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.model.response.CommonResponse;
import prompt.manageResources.service.AccountService;

import org.springframework.data.domain.Pageable;
import prompt.manageResources.service.EquipmentService;

import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/accounts")
public class AdminAccountsController {

    private final AccountService accountService;
    private final EquipmentService equipmentService;

    @GetMapping("")
    public String index(@PageableDefault(page = 0, size = 10) Pageable pageable, @ModelAttribute AccountDto accountDto, Model model, HttpServletRequest request) {
        Page<AccountDto> results = accountService.findAllByConditions(accountDto, pageable);
        model.addAttribute("results", results.getContent());
        model.addAttribute("resultCnt", results.getTotalElements());
        model.addAttribute("pagination", results);
        model.addAttribute("request", request);
        return "/apps/admin/account/index";
    }

    @GetMapping("/new")
    public String newAccount() {
        return "apps/admin/account/new";
    }

    @PostMapping("/new")
    public String saveAccount(@ModelAttribute PrivateAccountDto privateAccountDto) {
        accountService.initSave(privateAccountDto);
        return "redirect:/admin/accounts";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        AccountDto account = accountService.findById(id);
        model.addAttribute("account", account);
        return "apps/admin/account/show";
    }

    @PostMapping("/update")
    public String updateAccount(@ModelAttribute PrivateAccountDto privateAccountDto) {
        accountService.update(privateAccountDto);
        return "redirect:/admin/accounts";
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            accountService.deleteById(id);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return ResponseEntity.internalServerError().build();
        }
        String redirectUrl = "/admin/accounts";
        return ResponseEntity.ok(new CommonResponse<>(true, redirectUrl));
    }

    @PostMapping("/validate/id")
    @ResponseBody
    public ResponseEntity<?> validateId(@RequestParam(value = "userId") String useName) {
        Account account = accountService.findByUserName(useName);
        boolean isValidated = ObjectUtils.isEmpty(account);

        return isValidated ? ResponseEntity.ok(new CommonResponse<>(true, null))
                : ResponseEntity.ok(new CommonResponse<>(false, null));
    }

    @GetMapping("/resources")
    public String ownershipIndex(@PageableDefault(page = 0, size = 10) Pageable pageable, @ModelAttribute AccountDto accountDto, Model model, HttpServletRequest request) {
        Page<AccountDto> results = accountService.findAllByConditions(accountDto, pageable);
        model.addAttribute("results", results.getContent());
        model.addAttribute("resultCnt", results.getTotalElements());
        model.addAttribute("pagination", results);
        model.addAttribute("request", request);
        return "/apps/admin/resources-ownership/index";
    }

    @GetMapping("/{id}/resources")
    public String showOwnership(@PathVariable Long id, Model model) {
        AccountDto account = accountService.findById(id);
        List<EquipmentDto> equipments = equipmentService.findByAccountId(id);

        model.addAttribute("account", account);
        model.addAttribute("equipments", equipments);
        return "/apps/admin/resources-ownership/show";
    }

    @PostMapping("/{accountId}/resources/{equipmentId}")
    public String saveOwnership(@PathVariable Long accountId, @PathVariable Long equipmentId) {
        return "";
    }

    @PutMapping("/{accountId}/resources/{equipmentId}")
    @ResponseBody
    public ResponseEntity<?> updateOwnership(@PathVariable Long accountId, @PathVariable Long equipmentId) {
        try {
            equipmentService.updateAccountNull(accountId, equipmentId);
        } catch (Exception e) {
            ExceptionUtils.getStackTrace(e);
            ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }


}
