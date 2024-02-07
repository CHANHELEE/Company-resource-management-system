package prompt.manageResources.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceController {

    @GetMapping("/request/hist")
    public String index(Model model) {
        return "/apps/user/resource/index";
    }

    @GetMapping("/request/new")
    public String newRequest(Model model) {
        return "/apps/user/resource/new";
    }
}
