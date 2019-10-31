package md.electron.electronbackend.search.repositories;

import md.electron.electronbackend.search.model.ProductSolrData;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSolrDataRepository extends SolrCrudRepository<ProductSolrData, String>
{
    List<ProductSolrData> findByName(String name);

    @Query(value = "*:*")
    List<ProductSolrData> getProducts();

    @Query("name:*?0* OR description:*?0*")
    List<ProductSolrData> findByContaining(String word);
}
