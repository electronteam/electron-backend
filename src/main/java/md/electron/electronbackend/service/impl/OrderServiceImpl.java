package md.electron.electronbackend.service.impl;

import md.electron.electronbackend.persistence.model.Order;
import md.electron.electronbackend.persistence.model.OrderEntry;
import md.electron.electronbackend.persistence.model.Product;
import md.electron.electronbackend.persistence.repositories.ProductRepository;
import md.electron.electronbackend.service.OrderService;
import md.electron.electronbackend.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private SessionService sessionService;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProductToOrder(final String productCode)
    {
        final Product product = productRepository.getProductByCode(productCode);
        Order order;
        if (!sessionService.hasSessionOrder())
        {
            order = new Order();
        }
        else
        {
            order = sessionService.getSessionOrder();
        }

        final OrderEntry entry = new OrderEntry();
        entry.setProduct(product);
        entry.setOrder(order);
        entry.setQuantity(1L);
        order.getEntries().add(entry);
        System.out.println("Added product [" + product.getName() + "] to order");
        sessionService.setSessionOrder(order);
    }
}
