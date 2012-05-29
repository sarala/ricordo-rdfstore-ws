package ricordo.rdfstore.rest.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Sarala Wimalaratne
 * Date: 02/03/12
 * Time: 04:34
 */
@XmlRootElement(name = "resource")
public class Resource {
    private ArrayList<String> value;

    public Resource() {}

    @XmlElement(name="value")
    public ArrayList<String> getValue() {
        return value;
    }

    public void setValue(ArrayList<String> value) {
        this.value = value;
    }


}
