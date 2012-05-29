package ricordo.rdfstore.rest.service;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import ricordo.rdfstore.rest.bean.Resource;
import ricordo.rdfstore.rest.bean.ResourceList;
import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoQueryExecution;
import virtuoso.jena.driver.VirtuosoQueryExecutionFactory;

import java.util.ArrayList;


/**
 * Created by IntelliJ IDEA.
 * User: Sarala Wimalaratne
 * Date: 02/03/12
 * Time: 14:22
 */
public class RdfStoreServiceImpl implements RdfStoreService {

    private VirtGraph virtGraph;
    private SparqlQueryTemplateParser queryTemplateParser;

    public RdfStoreServiceImpl(VirtGraph virtGraph, SparqlQueryTemplateParser queryTemplateParser){
        this.virtGraph = virtGraph;
        this.queryTemplateParser = queryTemplateParser;
    }

     /**
     * Queries for all models
      * @param query search input
      * @param command
      * @param resourceList
     */
    public void search(String query, String command, ResourceList resourceList) {
        SparqlQuery sparqlQuery = queryTemplateParser.getSparqlQuery(command);
        resourceList.setSelect(sparqlQuery.getSelectList());

        ArrayList<Resource> idList = new ArrayList<Resource>();
        ResultSet resultSet = executeQuery(sparqlQuery.getQuery(query));
        while(resultSet.hasNext()){
            Resource resource = new Resource();
            QuerySolution solution = resultSet.next();
            ArrayList<String> valueList = new ArrayList<String>();
            for(String select :sparqlQuery.getSelectList()) {
                if (solution.getResource(select) != null) {
                    valueList.add(solution.getResource(select).getURI());
                    resource.setValue(valueList);
                }
            }
            idList.add(resource);
        }

        resourceList.setResources(idList);
    }


    private ResultSet executeQuery(String query){
        VirtuosoQueryExecution vqe = VirtuosoQueryExecutionFactory.create(query, virtGraph);
        return vqe.execSelect();    
    }


}
