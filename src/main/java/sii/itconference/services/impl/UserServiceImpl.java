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


    private IUserRepository userRepository;
    private ModelMapper modelMapper;

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
        //TODO 1. sprawdzic czy istnieje
        //TODO 2. zapisac

        if (!userRepository.existsUserByUsername(reservationDto.getUsername())) {
            User user = modelMapper.map(reservationDto, User.class);
            userRepository.save(user);
        } else {
            System.out.println("Juz istnieje taki uzytkownik");
        }
    }
}