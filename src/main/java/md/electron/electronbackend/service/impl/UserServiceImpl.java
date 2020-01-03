package md.electron.electronbackend.service.impl;

import md.electron.electronbackend.data.CheckoutData;
import md.electron.electronbackend.persistence.model.User;
import md.electron.electronbackend.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
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
}
