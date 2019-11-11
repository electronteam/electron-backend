package md.electron.electronbackend.service;

import md.electron.electronbackend.data.OrderData;

public interface OrderService
{
    void addProductToOrder(String productCode);

    OrderData getCurrentOrder();
}
