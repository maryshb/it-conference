package sii.itconference.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sii.itconference.model.User;
import sii.itconference.model.dto.ReservationDto;
import sii.itconference.model.dto.UserDto;
import sii.itconference.repository.IUserRepository;
import sii.itconference.services.IUserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {


    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void updateEmail(UserDto userDto) {
        User user = this.userRepository.findByUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();

        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(ReservationDto reservationDto) {
            User user = modelMapper.map(reservationDto, User.class);
            userRepository.save(user);
    }

    @Override
    public boolean isUserExistByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }
}