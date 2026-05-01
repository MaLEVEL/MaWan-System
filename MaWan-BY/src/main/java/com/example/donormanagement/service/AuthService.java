package com.example.donormanagement.service;

import com.example.donormanagement.dto.CreateUserRequest;
import com.example.donormanagement.dto.DonorRegisterRequest;
import com.example.donormanagement.dto.DonorRegisterResponse;
import com.example.donormanagement.dto.LoginRequest;
import com.example.donormanagement.dto.LoginResponse;
import com.example.donormanagement.dto.UserResponse;
import com.example.donormanagement.entity.Donor;
import com.example.donormanagement.entity.Role;
import com.example.donormanagement.entity.User;
import com.example.donormanagement.exception.BusinessException;
import com.example.donormanagement.exception.ResourceNotFoundException;
import com.example.donormanagement.mapper.UserMapper;
import com.example.donormanagement.repository.DonorRepository;
import com.example.donormanagement.repository.RoleRepository;
import com.example.donormanagement.repository.UserRepository;
import com.example.donormanagement.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Authentication Service
 */
@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DonorRepository donorRepository;

    @Transactional
    public LoginResponse login(LoginRequest request) {
        logger.info("User login attempt: {}", request.getUsername());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = tokenProvider.generateToken(authentication);

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        logger.info("User logged in successfully: {}", request.getUsername());

        // Look up donorId for donor users
        User user = userRepository.findByUsername(request.getUsername())
                .orElse(null);
        Long donorId = (user != null) ? user.getDonorId() : null;

        return new LoginResponse(token, request.getUsername(), roles, donorId);
    }

    @Transactional
    public UserResponse register(CreateUserRequest request) {
        logger.info("Creating new user: {}", request.getUsername());

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException("USERNAME_EXISTS", "Username already exists: " + request.getUsername());
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setEnabled(true);

        Set<Role> roles = new HashSet<>();
        for (String roleName : request.getRoles()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new ResourceNotFoundException("Role", "name", roleName));
            roles.add(role);
        }
        user.setRoles(roles);

        User savedUser = userRepository.save(user);
        logger.info("User created successfully: {}", savedUser.getUsername());

        return userMapper.toResponse(savedUser);
    }

    @Transactional
    public DonorRegisterResponse registerDonor(DonorRegisterRequest request) {
        logger.info("Donor self-registration: {}", request.getUsername());

        // Check username uniqueness
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException("USERNAME_EXISTS", "用户名已存在: " + request.getUsername());
        }

        // Check passport number uniqueness
        if (donorRepository.existsByPassportNumber(request.getPassportNumber())) {
            throw new BusinessException("PASSPORT_EXISTS", "该证件号已注册");
        }

        // Create Donor record
        Donor donor = new Donor();
        donor.setFirstName(request.getFirstName());
        donor.setLastName(request.getLastName());
        donor.setDateOfBirth(request.getDateOfBirth());
        donor.setGender(request.getGender());
        donor.setPassportNumber(request.getPassportNumber());
        donor.setPhone(request.getPhone());
        donor.setEmail(request.getEmail());
        donor.setAddress(request.getAddress());
        donor.setBloodType(request.getBloodType());
        donor.setHlaType(request.getHlaType());
        donor.setActive(true);
        Donor savedDonor = donorRepository.save(donor);

        // Create User with ROLE_DONOR
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFirstName() + " " + request.getLastName());
        user.setEmail(request.getEmail());
        user.setEnabled(true);
        user.setDonorId(savedDonor.getId());

        Role donorRole = roleRepository.findByName("ROLE_DONOR")
                .orElseThrow(() -> new ResourceNotFoundException("Role", "name", "ROLE_DONOR"));
        user.setRoles(Set.of(donorRole));

        User savedUser = userRepository.save(user);

        // Auto-login: generate JWT
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        String token = tokenProvider.generateToken(authentication);

        logger.info("Donor registered successfully: {} (donorId={})", savedUser.getUsername(), savedDonor.getId());

        return new DonorRegisterResponse(savedUser.getId(), savedDonor.getId(),
                savedUser.getUsername(), token);
    }
}

