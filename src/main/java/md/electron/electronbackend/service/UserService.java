package md.electron.electronbackend.service;

import md.electron.electronbackend.data.CheckoutData;
import md.electron.electronbackend.data.UserData;
import md.electron.electronbackend.persistence.model.User;

import java.util.List;

public interface UserService
{
    User createUser(CheckoutData checkoutData);

    void createUser(UserData userData);

    List<UserData> getAllUsers();
}
