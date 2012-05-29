package ricordo.rdfstore.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: Sarala Wimalaratne
 * Date: 21/05/12
 * Time: 12:00
 */
@XmlRootElement(name = "query")
public class Query {
    private String query;

    public Query() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
