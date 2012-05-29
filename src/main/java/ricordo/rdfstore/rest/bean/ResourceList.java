package ricordo.rdfstore.rest.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sarala Wimalaratne
 * Date: 02/03/12
 * Time: 04:36
 */

@XmlRootElement(name="resources")
public class ResourceList {

    private int count;
    private List<Resource> resources;
    private ArrayList<String> select;

    public ResourceList() {}

    public int getCount() {
        return resources.size();
    }

    public void setCount(int count) {
        this.count = count;
    }

    @XmlElement(name="resource")
    public List<Resource> getResources() {
        return resources;
    }
    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    @XmlElement(name="select")
    public ArrayList<String> getSelect() {
        return select;
    }

    public void setSelect(ArrayList<String> select) {
        this.select = select;
    }
}
