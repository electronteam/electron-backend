package md.electron.electronbackend.converters;

import md.electron.electronbackend.data.AddressData;
import md.electron.electronbackend.data.UserData;
import md.electron.electronbackend.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter
{
    @Autowired
    private AddressConverter addressConverter;

    public UserData convert(final User user)
    {
        final UserData userData = new UserData();
        userData.setId(user.getId());
        userData.setName(user.getName());
        userData.setLastName(user.getLastName());
        userData.setEmail(user.getEmail());
        userData.setPhone(user.getPhone());
        userData.setRole(user.getRole());
        userData.setCreationTime(user.getCreationTime());

        if (user.getUserAddress() != null)
        {
            final AddressData addressData = addressConverter.convert(user.getUserAddress());
            userData.setAddress(addressData);
        }

        return userData;
    }
}
