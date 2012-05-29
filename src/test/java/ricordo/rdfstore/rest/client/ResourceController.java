package ricordo.rdfstore.rest.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import ricordo.rdfstore.rest.bean.ResourceList;
import ricordo.rdfstore.rest.bean.Query;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: sarala
 * Date: 08/03/12
 * Time: 11:57
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations={"client-context.xml"})
public class ResourceController {

    private static String URL_STRING = "http://localhost:8080/service/";

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testGetResourcesInJson() throws Exception {
        ResponseEntity response = restTemplate.exchange( URL_STRING+"search/getResources.json",
                HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), String.class);
        assertEquals("{\"resources\":{\"count\":3,\"resources\":[{\"value\":[\"http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000001\"]},{\"value\":[\"http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000003\"]},{\"value\":[\"http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000002\"]}],\"select\":[\"?s\"]}}"
                ,response.getBody());

    }

    @Test
    public void testGetResourcesInXML() throws Exception {
        ResponseEntity response = restTemplate.exchange( URL_STRING+"search/getResources.xml",
                HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class);
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><resources><count>3</count><resource><value>http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000001</value></resource><resource><value>http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000003</value></resource><resource><value>http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000002</value></resource><select>?s</select></resources>"
                ,response.getBody());

    }

    @Test
    public void testGetTermsInAsTermObjects()throws Exception {
        ResponseEntity<ResourceList> response = restTemplate.exchange( URL_STRING+"search/getResources",
                HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), ResourceList.class);

        ResourceList resourceList = response.getBody();
        assertEquals(3, resourceList.getCount());
    }

    @Test
    public void testPostQueryGetResourcesByType() {
        Query query = new Query();
        query.setQuery("http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#SBMLModel");
        HttpEntity<Query> entity = new HttpEntity<Query>(query);

        ResponseEntity response = restTemplate.exchange( URL_STRING+"search/getResourcesByType", HttpMethod.POST, entity, String.class);

        assertEquals("{\"resources\":{\"count\":3,\"resources\":[{\"value\":[\"http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000001\"]},{\"value\":[\"http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000003\"]},{\"value\":[\"http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000002\"]}],\"select\":[\"?s\"]}}"
                ,response.getBody());
    }

    @Test
    public void testPostQueryGetResourcesByTypeSearchTest() {
        String query = "<query><query>http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#SBMLModel</query></query>";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> entity = new HttpEntity<String>(query,headers);


        ResponseEntity response = restTemplate.exchange( URL_STRING+"search/getResourcesByType", HttpMethod.POST, entity, String.class);

        assertEquals("{\"resources\":{\"count\":3,\"resources\":[{\"value\":[\"http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000001\"]},{\"value\":[\"http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000003\"]},{\"value\":[\"http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000002\"]}],\"select\":[\"?s\"]}}"
                ,response.getBody());
    }

    @Test
    public void testPostQueryGetResourceForAnnotationOfElement() {
        Query query = new Query();
        query.setQuery("http://identifiers.org/obo.go/GO:0031594");
        HttpEntity<Query> entity = new HttpEntity<Query>(query);

        ResponseEntity response = restTemplate.exchange( URL_STRING+"search/getResourceForAnnotationOfElement", HttpMethod.POST, entity, String.class);

        assertEquals("{\"resources\":{\"count\":2,\"resources\":[{\"value\":[\"http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000001\"]},{\"value\":[\"http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000002\"]}],\"select\":[\"?r\"]}}"
                ,response.getBody());
    }

    @Test
    public void testPostQueryGetResourceForAnnotation() {
        Query query = new Query();
        query.setQuery("http://identifiers.org/obo.go/GO:0031594");
        HttpEntity<Query> entity = new HttpEntity<Query>(query);

        ResponseEntity response = restTemplate.exchange( URL_STRING+"search/getResourceForAnnotation", HttpMethod.POST, entity, String.class);

        assertEquals("{\"resources\":{\"count\":2,\"resources\":[{\"value\":[\"http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000001_comp1\"]},{\"value\":[\"http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000002_comp1\"]}],\"select\":[\"?r\"]}}"
                ,response.getBody());
    }

    @Test
    public void testPostQueryGetElementOfResource() {
        Query query = new Query();
        query.setQuery("http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000001");
        HttpEntity<Query> entity = new HttpEntity<Query>(query);

        ResponseEntity<ResourceList> response = restTemplate.exchange( URL_STRING+"search/getElementOfResource", HttpMethod.POST, entity, ResourceList.class);

        assertEquals(65,response.getBody().getCount());
    }

    @Test
    public void testPostQueryAnnotationOfResource() {
        Query query = new Query();
        query.setQuery("http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000001");
        HttpEntity<Query> entity = new HttpEntity<Query>(query);

        ResponseEntity response = restTemplate.exchange( URL_STRING+"search/getAnnotationOfResource", HttpMethod.POST, entity, String.class);

        assertEquals("{\"resources\":{\"count\":2,\"resources\":[{\"value\":[\"http://identifiers.org/obo.go/GO:0007166\"]},{\"value\":[\"http://identifiers.org/obo.go/GO:0007274\"]}],\"select\":[\"?a\"]}}"
                ,response.getBody());

    }

    @Test
    public void testPostQueryAnnotationOfElementOfResource() {
        Query query = new Query();
        query.setQuery("http://www.ebi.ac.uk/ricordo/toolbox/sbmlo#BIOMD0000000001");
        HttpEntity<Query> entity = new HttpEntity<Query>(query);

        ResponseEntity<ResourceList> response = restTemplate.exchange( URL_STRING+"search/getAnnotationOfElementOfResource.xml", HttpMethod.POST, entity, ResourceList.class);

        assertEquals(39, response.getBody().getCount());

    }

/*
    @Test
    public void testEquivalentTerms() {
        ResponseEntity response = restTemplate.exchange( URL_STRING+"eqterms/{query}",
                HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class,
                "RICORDO_2 and part-of some RICORDO_21");

        assertEquals("{\"terms\":{\"count\":1,\"terms\":[{\"id\":\"http://www.ricordo.eu/ricordo.owl#RICORDO_25\"}]}}",response.getBody());
    }

    @Test
    public void testPostQuery() {
        ResponseEntity response = restTemplate.exchange( URL_STRING+"addterm/{query}",
                HttpMethod.POST, new HttpEntity<String>(new HttpHeaders()), String.class,
                "RICORDO_2 and part-of some RICORDO_21");

        assertEquals("{\"terms\":{\"count\":1,\"terms\":[{\"id\":\"http://www.ricordo.eu/ricordo.owl#RICORDO_25\"}]}}",response.getBody());
    }

    @Test
    public void testDeleteTerm() {
        ResponseEntity response = restTemplate.exchange( URL_STRING+"deleteterm/{query}",
                HttpMethod.DELETE, new HttpEntity<String>(new HttpHeaders()), String.class,
                "RICORDO_1332909646667");

        assertEquals("{\"terms\":{\"count\":0,\"terms\":[]}}",response.getBody());
    }*/

}
