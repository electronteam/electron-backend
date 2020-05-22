package md.electron.electronbackend.controllers;

import md.electron.electronbackend.constants.RequestMappings;
import md.electron.electronbackend.data.ProductData;
import md.electron.electronbackend.service.IndexingService;
import md.electron.electronbackend.service.OrderService;
import md.electron.electronbackend.service.ProductService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping(value = RequestMappings.ALL_PRODUCTS)
    public List<ProductData> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping(value = RequestMappings.PRODUCTS)
    public Page<ProductData> getProducts(final Pageable page)
    {
        return productService.getProducts(page);
    }

    @GetMapping(value = RequestMappings.PRODUCT_DETAILS)
    public ProductData viewProductDetails(@PathVariable String code)
    {
        return productService.getProductByCode(code);
    }

    @GetMapping(value = RequestMappings.PRODUCT_IMAGE_URL)
    public String getProductImageURL(@PathVariable String code)
    {
        return productService.getProductImageURL(code);
    }

    @RequestMapping(value = RequestMappings.ADD_TO_CART, method = RequestMethod.POST)
    public ResponseEntity<Void> addProductToCart(@RequestParam final String code)
    {
        orderService.addProductToOrder(code);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = RequestMappings.UPLOAD_PRODUCT_IMAGE)
    public ResponseEntity<Void> uploadProductImage(@PathVariable String code, @RequestParam("file") MultipartFile file)
    {
        try
        {
            productService.uploadProductImage(file, code);

            return ResponseEntity.ok().build();
        }
        catch (final Exception e)
        {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
        }
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
