package md.electron.electronbackend.service;

import md.electron.electronbackend.data.CheckoutData;
import md.electron.electronbackend.data.OrderData;
import md.electron.electronbackend.data.OrderListViewData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderService
{
    void addProductToOrder(String productCode);

    OrderData getCurrentOrder();

    void placeOrder(CheckoutData checkoutData);

    Page<OrderListViewData> getAllOrders(Pageable page);

    Optional<OrderData> getOrderByCode(String code);

    void deleteOrderByCode(String code);

    Optional<OrderData> getLastPlacedOrder();
}
