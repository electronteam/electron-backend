package md.electron.electronbackend.converters;

import md.electron.electronbackend.data.AddressData;
import md.electron.electronbackend.data.OrderData;
import md.electron.electronbackend.data.OrderEntryData;
import md.electron.electronbackend.data.ProductData;
import md.electron.electronbackend.data.UserData;
import md.electron.electronbackend.persistence.model.Address;
import md.electron.electronbackend.persistence.model.Order;
import md.electron.electronbackend.persistence.model.OrderEntry;
import md.electron.electronbackend.persistence.model.Product;
import md.electron.electronbackend.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverter
{
    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private UserConverter userConverter;

    public OrderData convert(final Order order)
    {
        final OrderData orderData = new OrderData();
        final List<OrderEntryData> orderDataEntries = new ArrayList<>();

        for (final OrderEntry entry : order.getEntries())
        {
            OrderEntryData orderEntryData = new OrderEntryData();
            orderEntryData.setQuantity(entry.getQuantity());
            orderEntryData.setTotalPrice(entry.getTotalPrice());

            final Product product = entry.getProduct();
            final ProductData productData = productConverter.convert(product);
            orderEntryData.setProduct(productData);

            orderDataEntries.add(orderEntryData);
        }

        orderData.setEntries(orderDataEntries);
        orderData.setTotalPrice(order.getTotalPrice());

        final Address deliveryAddress = order.getDeliveryAddress();
        if (deliveryAddress != null)
        {
            final AddressData addressData = addressConverter.convert(deliveryAddress);
            orderData.setDeliveryAddress(addressData);
        }

        final User user = order.getUser();
        if (user != null)
        {
            final UserData userData = userConverter.convert(user);
            orderData.setUserData(userData);
        }

        return orderData;
    }
}
