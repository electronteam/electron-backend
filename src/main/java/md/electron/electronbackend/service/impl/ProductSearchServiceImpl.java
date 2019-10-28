package md.electron.electronbackend.service.impl;

import md.electron.electronbackend.search.model.ProductSolrData;
import md.electron.electronbackend.search.repositories.ProductSolrDataRepository;
import md.electron.electronbackend.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSearchServiceImpl implements ProductSearchService
{
    @Autowired
    private ProductSolrDataRepository productSolrDataRepository;

    @Override
    public List<ProductSolrData> getAllProducts()
    {
        return productSolrDataRepository.getProducts();
    }
}
