package md.electron.electronbackend.controllers;

import md.electron.electronbackend.constants.RequestMappings;
import md.electron.electronbackend.data.CheckoutData;
import md.electron.electronbackend.data.OrderData;
import md.electron.electronbackend.service.OrderService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CheckoutController
{
    @Autowired
    private OrderService orderService;

    @PostMapping(value = RequestMappings.PLACE_ORDER)
    public ResponseEntity<Void> placeOrder(@ModelAttribute CheckoutData checkoutData)
    {
        System.out.println("Placing order made by: " + checkoutData.getName() + " " + checkoutData.getLastName());

        try
        {
            orderService.placeOrder(checkoutData);
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = RequestMappings.LAST_PLACED_ORDER)
    public ResponseEntity<OrderData> getLastPlacedOrder()
    {
        final Optional<OrderData> lastPlacedOrder = orderService.getLastPlacedOrder();

        if (lastPlacedOrder.isPresent())
        {
            return ResponseEntity.ok(lastPlacedOrder.get());
        }

        return ResponseEntity.notFound().build();
    }
}
