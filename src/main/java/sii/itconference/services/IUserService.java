package sii.itconference.services;

import sii.itconference.model.User;
import sii.itconference.model.dto.ReservationDto;
import sii.itconference.model.dto.UserDto;

import java.util.List;

public interface IUserService {
    void updateEmail(UserDto userDto);

    List<UserDto> getAllUsers();

    User getUserByUsername(String username);

    void saveUser(ReservationDto ReservationDto);

    boolean isUserExistByUsername(String username);
}