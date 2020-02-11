package md.electron.electronbackend.service;

import md.electron.electronbackend.data.CheckoutData;
import md.electron.electronbackend.data.OrderData;
import md.electron.electronbackend.data.OrderListViewData;

import java.util.List;
import java.util.Optional;

public interface OrderService
{
    void addProductToOrder(String productCode);

    OrderData getCurrentOrder();

    void placeOrder(CheckoutData checkoutData);

    List<OrderListViewData> getAllOrders();

    Optional<OrderData> getOrderByCode(String code);
}
