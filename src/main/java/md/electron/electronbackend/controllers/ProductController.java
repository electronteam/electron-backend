package md.electron.electronbackend.controllers;

import md.electron.electronbackend.constants.RequestMappings;
import md.electron.electronbackend.data.ProductData;
import md.electron.electronbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController
{
    @Autowired
    private ProductService productService;

    @GetMapping(value = RequestMappings.PRODUCTS)
    public List<ProductData> getAllProducts()
    {
        return productService.getAllProducts();
    }
}
