package md.electron.electronbackend.converters;

import md.electron.electronbackend.data.AddressData;
import md.electron.electronbackend.data.UserData;
import md.electron.electronbackend.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    @Autowired
    private AdressConverter adressConverter;

    public UserData convert(final User user) {
        final UserData userData = new UserData();
        userData.setName(user.getName());
        userData.setLastName(user.getLastName());
        userData.setEmail(user.getEmail());
        final AddressData addressData = adressConverter.convert(user.getUserAddress());
        userData.setAddress(addressData);
        userData.setPhone(user.getPhone());
        //userData.setPassword(user.getPassword()); I think we don't need this to be displayed
        userData.setRole(user.getRole());

        return userData;
    }
}
