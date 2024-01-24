package prompt.manageResources.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import prompt.manageResources.model.dto.PrivateAccountDto;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.service.AccountService;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/account")
public class AdminAccountController {

    private final AccountService accountService;

    @GetMapping("")
    public String index() {
        return "";
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

    @PostMapping("/validate/id")
    @ResponseBody
    public ResponseEntity<?> validateId(@RequestParam(value = "userId") String useName) {
        log.error(useName);
        Account account = accountService.findByUserName(useName);

        if (account != null) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
