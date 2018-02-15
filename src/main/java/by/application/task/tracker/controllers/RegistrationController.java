package by.application.task.tracker.controllers;


import by.application.task.tracker.Constants;
import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import by.application.task.tracker.validation.exception.LoginExistsException;
import by.application.task.tracker.validation.exception.WorkEmailExistsException;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

import static by.application.task.tracker.Constants.*;

@Controller
public class RegistrationController {

    @Autowired private UserService userService;
    @Autowired private ProjectService projectService;
    @Autowired private PositionService positionService;
    @Autowired private QualificationService qualificationService;
    @Autowired private ProjectRoleService projectRoleService;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired private EmailService emailService;
    @Autowired private UserStatusService userStatusService;
    @Autowired private DataConverterService dataConverterService;

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationPage(@RequestParam(value = "registrationSuccess", required = false) String registrationSuccess) {
        ModelAndView view = new ModelAndView("registration");
        if (registrationSuccess.equals("ok")) {
            view.addObject("confirmationMessage", "A confirmation e-mail has been sent.");
        }
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(currentUser.getProject().getProjectId()));
        view.addObject("qualification", qualificationService.findQualificationById(currentUser.getQualification()
                .getQualificationId()));

        view.addObject("positions", positionService.getAllPositions());
        view.addObject("projects", projectService.getAllProjects());
        view.addObject("qualifications", qualificationService.getAllQualifications());
        view.addObject("projectRoles", projectRoleService.getAllProjectRoles());
        UserDTO userForCreation = new UserDTO();
        view.addObject("user", userForCreation);
        return view;
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam(value = "registrationSuccess", required = false) String registrationSuccess,
                                     @Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        if (result.hasErrors()) {
            view.setViewName("registration");
            view.addObject("currentUser", currentUser);
            view.addObject("position", positionService.findPositionById(currentUser
                    .getPosition().getPositionId()));
            view.addObject("qualification", qualificationService.findQualificationById(currentUser
                    .getQualification().getQualificationId()));
            view.addObject("positions", positionService.getAllPositions());
            view.addObject("projects", projectService.getAllProjects());
            view.addObject("qualifications", qualificationService.getAllQualifications());
            view.addObject("projectRoles", projectRoleService.getAllProjectRoles());
        } else {
            view.addObject("currentUser", currentUser);
            userDTO.setEnabled(false);
            userDTO.setConfirmationToken(UUID.randomUUID().toString());
            createUserAccount(userDTO, result);
            String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(userDTO.getWorkEmail());
            registrationEmail.setSentDate(dataConverterService.generateTodayDateDay());
            registrationEmail.setSubject(REGISTRATION_CONFIRM_NOTIFICATION);
            registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                    + appUrl + "/confirmation?token=" + userDTO.getConfirmationToken());
            registrationEmail.setFrom(Constants.from_email);
            emailService.sendEmail(registrationEmail);
            view.setViewName("redirect:/registration?registrationSuccess=ok");

        }
        return view;
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.GET)
    public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {
        User user = userService.findByConfirmationToken(token);
        if (user == null) {
            modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
        } else {
            modelAndView.addObject("confirmationToken", user.getConfirmationToken());
        }
        modelAndView.setViewName("confirmation");
        return modelAndView;
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.POST)
    public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map requestParams, RedirectAttributes redir) {
        modelAndView.setViewName("confirmation");
        Zxcvbn passwordCheck = new Zxcvbn();
        Strength strength = passwordCheck.measure((String) requestParams.get("password"));
        if (strength.getScore() < 3) {
            bindingResult.reject("password");
            redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");
            modelAndView.setViewName("redirect:confirmation?token=" + requestParams.get("token"));
            return modelAndView;
        }
        User user = userService.findByConfirmationToken((String) requestParams.get("token"));
        user.setPassword(bCryptPasswordEncoder.encode((CharSequence) requestParams.get("password")));
        user.setEnabled(true);
        if (user.getProject() != null) {
            user.setUserStatus(userStatusService.findByStatusName(USER_ASSIGNED));
        } else {
            user.setUserStatus(userStatusService.findByStatusName(USER_ACTIVATED));
        }
        userService.saveUser(user);
        modelAndView.addObject("successMessage", "Your password has been set!");
        return modelAndView;
    }

    private void createUserAccount(UserDTO userDto, BindingResult result) {
        try {
            userService.createUser(userDto);
        } catch (LoginExistsException e) {
            result.rejectValue("login", "message", "Username already exists");
        } catch (WorkEmailExistsException e) {
            result.rejectValue("workEmail", "message", "Email already exists");
        }
    }

}

