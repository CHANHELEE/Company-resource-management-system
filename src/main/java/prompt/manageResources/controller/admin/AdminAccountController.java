package prompt.manageResources.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import prompt.manageResources.model.dto.AccountDto;
import prompt.manageResources.model.dto.PrivateAccountDto;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.model.response.CommonResponse;
import prompt.manageResources.service.AccountService;

import org.springframework.data.domain.Pageable;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/account")
public class AdminAccountController {

    private final AccountService accountService;

    @GetMapping("")
    public String index(@PageableDefault(page = 0, size = 10) Pageable pageable, @ModelAttribute AccountDto accountDto, Model model) {
        Page<AccountDto> results = accountService.findAllByConditions(accountDto, pageable);

        model.addAttribute("results", results.getContent());
        model.addAttribute("resultCnt", results.getTotalElements());
        model.addAttribute("pagination", results);
        return "/apps/admin/account/index";
    }

    @GetMapping("/new")
    public String newAccount() {
        return "apps/admin/account/new";
    }

    @PostMapping("/new")
    public String saveAccount(@ModelAttribute PrivateAccountDto privateAccountDto) {
        accountService.save(privateAccountDto);
        return "redirect:/admin/account";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id) {
        return "apps/admin/account/show";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            accountService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(new CommonResponse<>(true, null));
    }

    @PostMapping("/validate/id")
    @ResponseBody
    public ResponseEntity<?> validateId(@RequestParam(value = "userId") String useName) {
        Account account = accountService.findByUserName(useName);
        boolean isValidated = ObjectUtils.isEmpty(account);

        return isValidated ? ResponseEntity.ok(new CommonResponse<>(true, null))
                : ResponseEntity.ok(new CommonResponse<>(false, null));
    }


}
