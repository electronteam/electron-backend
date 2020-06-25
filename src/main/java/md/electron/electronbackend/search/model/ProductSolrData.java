package md.electron.electronbackend.search.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SolrDocument(collection = "products")
public class ProductSolrData
{
    @Id
    @Indexed(name = "id", type = "String")
    private Long id;

    @Indexed(name = "code", type = "String")
    private String code;

    @Indexed(name = "name", type = "String")
    private String name;

    @Indexed(name = "description", type = "String")
    private String description;

    @Indexed(name = "price", type = "double")
    private double price;

    @Indexed(name = "imageURL", type = "String")
    private String imageURL;
}
