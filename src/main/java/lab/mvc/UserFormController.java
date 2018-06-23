package lab.mvc;

import lab.aop.Loggable;
import lab.model.UserImpl;
import lab.mvc.form.bean.UserFormBean;
import lab.service.UserService;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Controller
@RequestMapping("/adduser.form")
@FieldDefaults(level = PRIVATE)
public class UserFormController {

    @Setter(onMethod = @__(@Autowired))
    UserService userService;

    @ModelAttribute("userFormBean")
    public UserFormBean getUserFormBean() {
        return new UserFormBean();
    }

    @GetMapping
    public String get() {
        return "adduserform";
    }

    @Loggable
    @PostMapping
    public ModelAndView processSubmit(@Valid UserFormBean userFormBean,
                                      Errors errors) {
        if (errors.hasErrors())
            return new ModelAndView("adduserform");

        userService.saveUser(new UserImpl()
                .setFirstName(userFormBean.getFirstName())
                .setLastName(userFormBean.getLastName()));

        return new ModelAndView("userlistview")
                .addObject("userList", userService.loadAllUsers());

    }
}