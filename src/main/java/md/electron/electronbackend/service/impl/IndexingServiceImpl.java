package md.electron.electronbackend.service.impl;

import md.electron.electronbackend.persistence.model.Product;
import md.electron.electronbackend.persistence.repositories.ProductRepository;
import md.electron.electronbackend.search.model.ProductSolrData;
import md.electron.electronbackend.search.repositories.ProductSolrDataRepository;
import md.electron.electronbackend.service.IndexingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndexingServiceImpl implements IndexingService
{
    @Autowired
    private ProductSolrDataRepository productSolrDataRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void loadProductDataIntoSolr()
    {
        final List<ProductSolrData> solrProducts = new ArrayList<>();
        final List<Product> productsDB = productRepository.findAll();

        productsDB.forEach(product -> {
            final ProductSolrData productSolrData = new ProductSolrData();
            productSolrData.setId(product.getId());
            productSolrData.setCode(product.getCode());
            productSolrData.setName(product.getName());
            productSolrData.setDescription(product.getDescription());
            solrProducts.add(productSolrData);
        });

        productSolrDataRepository.saveAll(solrProducts);
    }
}
