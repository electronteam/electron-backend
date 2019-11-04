package md.electron.electronbackend.service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface IndexingService
{
    void loadProductDataIntoSolr() throws SolrServerException, IOException;
}
