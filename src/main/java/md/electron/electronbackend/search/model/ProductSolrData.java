package md.electron.electronbackend.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "products")
public class ProductSolrData
{

    @Id
    @Indexed(name = "id", type = "String")
    private String id;

    @Indexed(name = "code", type = "String")
    private String code;

    @Indexed(name = "name", type = "String")
    private String name;

    @Indexed(name = "description", type = "String")
    private String description;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
