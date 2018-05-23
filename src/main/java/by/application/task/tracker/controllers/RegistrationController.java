package by.application.task.tracker.controllers;


import by.application.task.tracker.Constants;
import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import by.application.task.tracker.service.impl.DataConverterService;
import by.application.task.tracker.service.impl.EmailService;
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
import java.text.ParseException;
import java.util.Map;
import java.util.UUID;

import static by.application.task.tracker.Constants.*;

@Controller
public class RegistrationController{

    private final UserService userService;
    private final ProjectService projectService;
    private final PositionService positionService;
    private final QualificationService qualificationService;
    private final ProjectRoleService projectRoleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;
    private final UserStatusService userStatusService;
    private final DataConverterService dataConverterService;

    @Autowired
    public RegistrationController(UserService userService, ProjectService projectService,
                                  PositionService positionService, QualificationService qualificationService,
                                  ProjectRoleService projectRoleService, BCryptPasswordEncoder bCryptPasswordEncoder,
                                  EmailService emailService, UserStatusService userStatusService,
                                  DataConverterService dataConverterService) {
        this.userService = userService;
        this.projectService = projectService;
        this.positionService = positionService;
        this.qualificationService = qualificationService;
        this.projectRoleService = projectRoleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
        this.userStatusService = userStatusService;
        this.dataConverterService = dataConverterService;
    }

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationPage(@RequestParam(value = "registrationSuccess", required = false) String registrationSuccess) {
        ModelAndView view = new ModelAndView("registration");
        if ( registrationSuccess != null && "ok".equals(registrationSuccess)) {
            view.addObject("confirmationMessage", "A confirmation e-mail has been sent.");
        }
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());
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
                                     @Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, HttpServletRequest request) throws ParseException {
        ModelAndView view = new ModelAndView();
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        if (result.hasErrors()) {
            view.setViewName("registration");
            view.addObject("currentUser", currentUser);
            view.addObject("position", currentUser.getPosition());
            view.addObject("project", currentUser.getProject());
            view.addObject("qualification", currentUser.getQualification());
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
            registrationEmail.setText("Dear, " + userDTO.getUserName() +  " "+ userDTO.getUserSurname()+".\n We congratulate you," +
                    " with the acceptance in our company for work, and also send you an invitation to register in our " +
                    "TaskTracker system\n" + "To confirm your e-mail address, please click the link below:\n"
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
    public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult,
                                                @RequestParam Map requestParams, RedirectAttributes redir) {
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

