package md.electron.electronbackend.controllers;

import md.electron.electronbackend.constants.RequestMappings;
import md.electron.electronbackend.data.OrderData;
import md.electron.electronbackend.service.impl.OrderServiceImpl;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping(value = RequestMappings.CURRENT_CART_COUNT)
    public Long getCartItemsCount()
    {
        return orderService.getCurrentOrderItemsCount();
    }

    @PostMapping(value = RequestMappings.CART_DELETE_ENTRY)
    public ResponseEntity<Void> deleteCurrentCartEntry(@PathVariable final String productCode)
    {
        try
        {
            orderService.deleteEntryFromCurrentCart(productCode);
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok().build();
    }
}
