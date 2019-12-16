package md.electron.electronbackend.service.impl;

import md.electron.electronbackend.persistence.model.Order;
import md.electron.electronbackend.persistence.model.OrderEntry;
import md.electron.electronbackend.service.CalculationService;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService
{
    @Override
    public void calculate(final Order order)
    {
        calculateEntries(order);
        calculateTotals(order);
    }

    private void calculateEntries(final Order order)
    {
        order.getEntries().forEach(orderEntry -> {
            final double entryTotalPrice = orderEntry.getQuantity() * orderEntry.getProduct().getPrice();
            orderEntry.setTotalPrice(entryTotalPrice);
        });
    }

    private void calculateTotals(final Order order)
    {
        final double totalPrice = order.getEntries().stream().mapToDouble(OrderEntry::getTotalPrice).sum();
        order.setTotalPrice(totalPrice);
    }
}
