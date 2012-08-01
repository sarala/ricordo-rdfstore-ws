package uk.ac.ebi.ricordo.rdfstore.rest.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Sarala Wimalaratne
 * Date: 01/08/12
 * Time: 11:30
 */
@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations={"client-context.xml"})
public class QualifierController {
    private static String URL_STRING = "http://localhost:8080/service/";

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testGetResourcesInJson() throws Exception {
        ResponseEntity response = restTemplate.exchange( URL_STRING+"/bioQualifiers.json",
                HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), String.class);
        assertEquals("",response.getBody());

    }
}
