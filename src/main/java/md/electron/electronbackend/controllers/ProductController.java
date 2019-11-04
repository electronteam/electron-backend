package md.electron.electronbackend.controllers;

import md.electron.electronbackend.constants.RequestMappings;
import md.electron.electronbackend.data.ProductData;
import md.electron.electronbackend.service.IndexingService;
import md.electron.electronbackend.service.OrderService;
import md.electron.electronbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController
{
    private static final String FAILURE = "FAILURE";
    private static final String SUCCESS = "SUCCESS";

    @Autowired
    private ProductService productService;

    @Autowired
    private IndexingService indexingService;

    @Autowired
    private OrderService orderService;

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

    @RequestMapping(value = RequestMappings.ADD_TO_CART, method = RequestMethod.POST)
    public ResponseEntity<Void> addProductToCart(@RequestParam final String code)
    {
        orderService.addProductToOrder(code);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = RequestMappings.SOLR_PRODUCTS_INDEXING)
    public String indexProductSolrData()
    {
        //TODO - make this method to be accessed only by admin
        try
        {
            indexingService.loadProductDataIntoSolr();
        }
        catch (Exception e)
        {
            return FAILURE;
        }

        return SUCCESS;
    }
}
