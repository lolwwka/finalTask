package com.example.finalproject.service.user;

import com.example.finalproject.dto.BetEventDto;
import com.example.finalproject.dto.UserDto;
import com.example.finalproject.dto.UserProfileDto;
import com.example.finalproject.entity.Bet;
import com.example.finalproject.entity.Role;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.RoleRepository;
import com.example.finalproject.repository.VisitorRepository;
import com.example.finalproject.service.email.CustomEmailService;
import com.example.finalproject.service.event.EventServiceImpl;
import com.example.finalproject.service.register.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final VisitorRepository visitorRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CustomEmailService emailService;
    private final RegisterService registerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    public UserServiceImpl(VisitorRepository visitorRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, CustomEmailService emailService, RegisterService registerService) {
        this.visitorRepository = visitorRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.registerService = registerService;
    }

    @Override
    public List<User> getUsers() {
        return visitorRepository.findAll();
    }

    @Override
    public boolean addUser(UserDto userDto) {
        User user = convertUser(userDto);
        if (visitorRepository.findByEmail(userDto.getEmail()) != null) {
            LOGGER.error("Someone tries to create user with existed login [{}]", userDto.getEmail());
            throw new RuntimeException("User is already exists");
        }
        visitorRepository.save(user);
        new Thread(() -> sendCodeEmail(user)).start();
        return true;
    }

    @Override
    public User getUserByEmail(String email) {
        return visitorRepository.findByEmail(email);
    }

    @Override
    public UserProfileDto getUserInfo(String login) {
        User user = visitorRepository.findByEmailContaining(login + "@");
        List<BetEventDto> betTeam = new ArrayList<>();
        List<Bet> bets = user.getBets();
        for (Bet bet : bets) {
            BetEventDto betEventDto = new BetEventDto();
            betEventDto.setFirstTeam(bet.getEvent().getTeams().get(0).getName());
            betEventDto.setSecondTeam(bet.getEvent().getTeams().get(1).getName());
            betEventDto.setTournament(bet.getEvent().getTournamentName());
            betEventDto.setBetAmount(bet.getAmount());
            betEventDto.setStatus(bet.getStatus());
            betTeam.add(betEventDto);
        }
        betTeam.sort(Comparator.comparing(BetEventDto::getStatus));
        return new UserProfileDto(user.getBalance(), betTeam);
    }

    @Override
    public Set<String> convertUserRoles(Set<Role> roles) {
        List<Role> result = new ArrayList<>(roles);
        Set<String> resultSet = new HashSet<>();
        for (Role role : result) {
            resultSet.add("ROLE_" + role.getName());
        }
        return resultSet;
    }

    public boolean sendCodeEmail(User user) {
        try {
            return emailService.sendCodeEmail(user.getEmail(), user.getKeyCode());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    private User convertUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.getById(2L);
        HashSet<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setKeyCode(registerService.generateUserCode(user.getEmail()));
        return user;
    }

}
