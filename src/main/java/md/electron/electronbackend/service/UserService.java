package md.electron.electronbackend.service;

import md.electron.electronbackend.data.CheckoutData;
import md.electron.electronbackend.data.UserData;
import md.electron.electronbackend.persistence.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService
{
    User createUser(CheckoutData checkoutData);

    void createUser(UserData userData);

    List<UserData> getAllUsers();

    Page<UserData> getUsers(Pageable page);

    Optional<UserData> getUserById(String id);
}
