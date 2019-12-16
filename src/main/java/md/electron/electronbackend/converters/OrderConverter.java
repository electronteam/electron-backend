package md.electron.electronbackend.converters;

import md.electron.electronbackend.data.OrderData;
import md.electron.electronbackend.data.OrderEntryData;
import md.electron.electronbackend.data.ProductData;
import md.electron.electronbackend.persistence.model.Order;
import md.electron.electronbackend.persistence.model.OrderEntry;
import md.electron.electronbackend.persistence.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverter
{
    @Autowired
    private ProductConverter productConverter;

    public OrderData convert(Order order)
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

        return orderData;
    }
}
