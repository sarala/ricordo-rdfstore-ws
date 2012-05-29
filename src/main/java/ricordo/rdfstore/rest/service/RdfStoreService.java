package ricordo.rdfstore.rest.service;

import ricordo.rdfstore.rest.bean.ResourceList;

/**
 * Created by IntelliJ IDEA.
 * User: Sarala Wimalaratne
 * Date: 13/03/12
 * Time: 21:39
 */
public interface RdfStoreService {
    public void search(String query, String command, ResourceList resourceList);
}
