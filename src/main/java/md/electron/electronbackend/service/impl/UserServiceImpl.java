package md.electron.electronbackend.service.impl;

import md.electron.electronbackend.converters.UserConverter;
import md.electron.electronbackend.data.AddressData;
import md.electron.electronbackend.data.CheckoutData;
import md.electron.electronbackend.data.UserData;
import md.electron.electronbackend.persistence.model.Address;
import md.electron.electronbackend.persistence.model.User;
import md.electron.electronbackend.persistence.repositories.UserRepository;
import md.electron.electronbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public User createUser(final CheckoutData checkoutData) {
        final User user = new User();
        user.setName(checkoutData.getName());
        user.setLastName(checkoutData.getLastName());
        user.setEmail(checkoutData.getEmail());
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
        user.setPhone(userData.getPhone());
        user.setRole(userData.getRole());

        final AddressData addressData = userData.getAddress();
        if (addressData != null)
        {
            final Address address = new Address();
            address.setCity(addressData.getCity());
            address.setStreet(addressData.getStreet());
            user.setUserAddress(address);
        }

        if (userData.getPassword() != null)
        {
            user.setPassword(passwordEncoder.encode(userData.getPassword()));
        }

        userRepository.save(user);
    }

    @Override
    public List<UserData> getAllUsers() {
        final List<UserData> users = new ArrayList<>();
        final List<User> usersDB = userRepository.findAll();
        usersDB.forEach(user -> users.add(userConverter.convert(user)));
        return users;
    }
}
