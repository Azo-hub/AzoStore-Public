package AzoStore.ControllerPackage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		model.addAttribute("classActiveLogin", true);
		
		return "myaccount";
	}

}
