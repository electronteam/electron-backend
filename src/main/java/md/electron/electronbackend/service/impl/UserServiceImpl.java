package md.electron.electronbackend.service.impl;

import md.electron.electronbackend.data.CheckoutData;
import md.electron.electronbackend.data.UserData;
import md.electron.electronbackend.persistence.model.User;
import md.electron.electronbackend.persistence.repositories.UserRepository;
import md.electron.electronbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(final CheckoutData checkoutData)
    {
        final User user = new User();
        user.setName(checkoutData.getName());
        user.setLastName(checkoutData.getLastName());
        user.setEmail(checkoutData.getEmail());
        user.setAddress(checkoutData.getAddress());
        user.setPhone(checkoutData.getPhone());
        return user;
    }

    @Override
    public void createUser(final UserData userData)
    {
        final User user = new User();
        user.setName(userData.getName());
        user.setLastName(userData.getLastName());
        user.setEmail(userData.getEmail());
        user.setAddress(userData.getAddress());
        user.setPhone(userData.getPhone());
        user.setRole(userData.getRole());
        user.setPassword(userData.getPassword());

        userRepository.save(user);
    }
}
