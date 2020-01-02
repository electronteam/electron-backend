package md.electron.electronbackend.controllers;

import md.electron.electronbackend.constants.RequestMappings;
import md.electron.electronbackend.data.CheckoutData;
import md.electron.electronbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckoutController
{
    @Autowired
    private OrderService orderService;

    @PostMapping(value = RequestMappings.PLACE_ORDER)
    public ResponseEntity<Void> placeOrder(@ModelAttribute CheckoutData checkoutData)
    {
        System.out.println("Placing order made by: " + checkoutData.getName() + " " + checkoutData.getLastName());
        orderService.placeOrder(checkoutData);
        return ResponseEntity.noContent().build();
    }
}
