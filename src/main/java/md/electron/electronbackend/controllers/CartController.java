package md.electron.electronbackend.controllers;

import md.electron.electronbackend.constants.RequestMappings;
import md.electron.electronbackend.data.OrderData;
import md.electron.electronbackend.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController
{
    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping(value = RequestMappings.CURRENT_CART)
    public OrderData getCurrentCart()
    {
        return orderService.getCurrentOrder();
    }
}
