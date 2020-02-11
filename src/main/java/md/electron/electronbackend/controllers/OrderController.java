package md.electron.electronbackend.controllers;

import md.electron.electronbackend.constants.RequestMappings;
import md.electron.electronbackend.data.OrderData;
import md.electron.electronbackend.data.OrderListViewData;
import md.electron.electronbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController
{

    @Autowired
    private OrderService orderService;

    @GetMapping(value = RequestMappings.ADMIN_ORDERS)
    public List<OrderListViewData> getAllOrders()
    {
        return orderService.getAllOrders();
    }

    @GetMapping(value = RequestMappings.ORDER_DETAILS)
    public ResponseEntity<OrderData> viewOrderDetails(@PathVariable String code)
    {
        final Optional<OrderData> order = orderService.getOrderByCode(code);

        return ResponseEntity.of(order);
    }
}
