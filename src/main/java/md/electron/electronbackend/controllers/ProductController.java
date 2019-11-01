package md.electron.electronbackend.controllers;

import md.electron.electronbackend.constants.RequestMappings;
import md.electron.electronbackend.data.ProductData;
import md.electron.electronbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping(value = RequestMappings.PRODUCT_DETAILS)
    public ProductData viewProductDetails(@PathVariable String code)
    {
        return productService.getProductByCode(code);
    }
}
