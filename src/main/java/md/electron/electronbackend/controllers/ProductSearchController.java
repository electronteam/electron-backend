package md.electron.electronbackend.controllers;

import md.electron.electronbackend.constants.RequestMappings;
import md.electron.electronbackend.search.model.ProductSolrData;
import md.electron.electronbackend.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductSearchController
{
    @Autowired
    private ProductSearchService productSearchService;

    @GetMapping(value = RequestMappings.SOLR_PRODUCTS)
    public List<ProductSolrData> getAllProducts()
    {
        return productSearchService.getAllProducts();
    }
}
