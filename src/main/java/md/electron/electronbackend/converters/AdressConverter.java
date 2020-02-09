package md.electron.electronbackend.converters;

import md.electron.electronbackend.data.AddressData;
import md.electron.electronbackend.persistence.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AdressConverter {
    public AddressData convert(final Address address) {
        final AddressData addressData = new AddressData();
        addressData.setCity(address.getCity());
        addressData.setStreet(address.getStreet());

        return addressData;
    }
}
