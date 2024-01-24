package prompt.manageResources.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class CustomExceptionController implements ErrorController {
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Integer statusCode;
        String errorMsg = null;

        if(status != null){
            statusCode = Integer.valueOf(status.toString());
            errorMsg = "Error : ".concat(statusCode.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                log.error(errorMsg);
                return "apps/common/error";
            } else {
                log.error(errorMsg);
                return "apps/common/error";
            }
        }
        log.error(errorMsg);
        return "apps/common/error";
    }
}
