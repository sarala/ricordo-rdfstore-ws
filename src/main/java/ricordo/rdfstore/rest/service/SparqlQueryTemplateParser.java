package ricordo.rdfstore.rest.service;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Sarala Wimalaratne
 * Date: 18/05/12
 * Time: 14:27
 */
public class SparqlQueryTemplateParser {
    private HashMap<String,SparqlQuery> sparqlQueryHashMap = new HashMap<String, SparqlQuery>();     
    private String queryTemplatePath;

    public SparqlQueryTemplateParser(String queryTemplatePath) {
        this.queryTemplatePath = queryTemplatePath;
    }
    
    public SparqlQuery getSparqlQuery(String query){
        SparqlQuery sparqlQuery = sparqlQueryHashMap.get(query);
        if(sparqlQuery==null){
            String queryFile = queryTemplatePath + query + ".txt";
            sparqlQuery = new SparqlQuery(queryFile);
            sparqlQueryHashMap.put(query,sparqlQuery);
        }
        return  sparqlQuery;
    }
}
