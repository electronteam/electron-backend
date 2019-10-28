package md.electron.electronbackend.config;


import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(basePackages = "md.electron.electronbackend.search")
@ComponentScan
public class SolrConfig
{
    private static final String SOLR_URL = "spring.data.solr.host";

    @Autowired
    private Environment environment;

    @Bean
    public SolrClient solrClient()
    {
        return new HttpSolrClient.Builder(environment.getProperty(SOLR_URL)).build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception
    {
        return new SolrTemplate(client);
    }
}
