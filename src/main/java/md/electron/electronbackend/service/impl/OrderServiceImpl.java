package md.electron.electronbackend.service.impl;

import md.electron.electronbackend.converters.OrderConverter;
import md.electron.electronbackend.converters.OrderListViewConverter;
import md.electron.electronbackend.data.AddressData;
import md.electron.electronbackend.data.CheckoutData;
import md.electron.electronbackend.data.OrderData;
import md.electron.electronbackend.data.OrderListViewData;
import md.electron.electronbackend.persistence.model.Address;
import md.electron.electronbackend.persistence.model.Order;
import md.electron.electronbackend.persistence.model.OrderEntry;
import md.electron.electronbackend.persistence.model.Product;
import md.electron.electronbackend.persistence.model.User;
import md.electron.electronbackend.persistence.repositories.OrderRepository;
import md.electron.electronbackend.persistence.repositories.ProductRepository;
import md.electron.electronbackend.service.CalculationService;
import md.electron.electronbackend.service.OrderService;
import md.electron.electronbackend.service.SessionService;
import md.electron.electronbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    private OrderListViewConverter orderListViewConverter;

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

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
            final User user = userService.createUser(checkoutData);
            order.setUser(user);

            final AddressData addressData = checkoutData.getAddress();
            if (addressData != null)
            {
                final Address address = new Address();
                address.setCity(addressData.getCity());
                address.setStreet(addressData.getStreet());
                order.setDeliveryAddress(address);
            }

            orderRepository.save(order);
            sessionService.removeSessionOrder();
            sessionService.setLastPlacedOrderId(order.getId());
        }
    }

    @Override
    public Page<OrderListViewData> getAllOrders(Pageable page)
    {
        Page<Order> orderPage = orderRepository.findAll(page);
        Page<OrderListViewData> ordersDB= orderPage.map(order->orderListViewConverter.convert(order));
        return ordersDB;
    }

    @Override
    public Optional<OrderData> getOrderByCode(final String code)
    {
        final Long id = Long.valueOf(code);

        return getOrderById(id);
    }

    @Override
    public void deleteOrderByCode(final String code) {
        final Long id = Long.valueOf(code);

        orderRepository.deleteById(id);
    }

    @Override
    public Optional<OrderData> getLastPlacedOrder()
    {
        final Long orderId = sessionService.getLastPlacedOrderId();

        if (orderId != null)
        {
            return getOrderById(orderId);
        }

        return Optional.empty();
    }

    private Optional<OrderData> getOrderById(final Long orderId)
    {
        final Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent())
        {
            final Order foundOrder = order.get();
            final OrderData orderData = orderConverter.convert(foundOrder);
            return Optional.of(orderData);
        }

        return Optional.empty();
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
