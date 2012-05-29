package ricordo.rdfstore.rest.controller;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ricordo.rdfstore.rest.bean.ResourceList;
import ricordo.rdfstore.rest.bean.Query;
import ricordo.rdfstore.rest.service.RdfStoreService;

/**
 * Created by IntelliJ IDEA.
 * User: Sarala Wimalaratne
 * Date: 05/03/12
 * Time: 13:07
 */

@Controller
public class ResourceController {
    private RdfStoreService rdfStoreService;
    private Jaxb2Marshaller jaxb2Mashaller;

    private static final String XML_VIEW_NAME = "resources";

    @RequestMapping(method=RequestMethod.GET, value="/search/{command}")
    public ModelAndView getResources(@PathVariable String command) {
        ResourceList resourceList = new ResourceList();
        rdfStoreService.search("",command,resourceList);
        return new ModelAndView(XML_VIEW_NAME, "resources", resourceList);
    }

    @RequestMapping(method=RequestMethod.POST, value="/search/{command}")
    public ModelAndView getSearch(@RequestBody Query query, @PathVariable String command) {
        ResourceList resourceList = new ResourceList();
        rdfStoreService.search(query.getQuery(), command, resourceList);
        return new ModelAndView(XML_VIEW_NAME, "resources", resourceList);
    }

    public void setRdfStoreService(RdfStoreService service) {
        this.rdfStoreService = service;
    }

    public void setJaxb2Mashaller(Jaxb2Marshaller jaxb2Mashaller) {
        this.jaxb2Mashaller = jaxb2Mashaller;
    }
}
