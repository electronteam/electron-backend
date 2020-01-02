package md.electron.electronbackend.service.impl;

import md.electron.electronbackend.converters.OrderConverter;
import md.electron.electronbackend.data.CheckoutData;
import md.electron.electronbackend.data.OrderData;
import md.electron.electronbackend.persistence.model.Order;
import md.electron.electronbackend.persistence.model.OrderEntry;
import md.electron.electronbackend.persistence.model.Product;
import md.electron.electronbackend.persistence.repositories.OrderRepository;
import md.electron.electronbackend.persistence.repositories.ProductRepository;
import md.electron.electronbackend.service.CalculationService;
import md.electron.electronbackend.service.OrderService;
import md.electron.electronbackend.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private SessionService sessionService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void addProductToOrder(final String productCode)
    {
        final Product product = productRepository.getProductByCode(productCode);
        final Order order = sessionService.getSessionOrder();

        final OrderEntry orderEntry = getEntryContainingProduct(product, order);
        if (orderEntry == null)
        {
            addNewEntryToOrder(product, order);
        }
        else
        {
            updateEntryQuantity(orderEntry);
        }
        System.out.println("Added product [" + product.getName() + "] to order");

        calculationService.calculate(order);
        sessionService.setSessionOrder(order);
    }

    @Override
    public OrderData getCurrentOrder()
    {
        return orderConverter.convert(sessionService.getSessionOrder());
    }

    @Override
    public void placeOrder(final CheckoutData checkoutData)
    {
        final Order order = sessionService.getSessionOrder();
        if (order != null)
        {
            orderRepository.save(order);
            //TODO - remove order from session, add user to order
        }
    }

    private OrderEntry getEntryContainingProduct(final Product product, final Order order)
    {
        final List<OrderEntry> entries = order.getEntries();
        for (OrderEntry entry : entries)
        {
            if (entry.getProduct().getCode().equals(product.getCode()))
            {
                return entry;
            }
        }

        return null;
    }

    private void updateEntryQuantity(final OrderEntry orderEntry)
    {
        orderEntry.setQuantity(orderEntry.getQuantity() + 1);
    }

    private void addNewEntryToOrder(final Product product, final Order order)
    {
        final OrderEntry entry = new OrderEntry();
        entry.setProduct(product);
        entry.setOrder(order);
        entry.setQuantity(1L);
        order.getEntries().add(entry);
    }
}
