package uk.ac.ebi.ricordo.rdfstore.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.ebi.ricordo.rdfstore.bean.Qualifier;
import uk.ac.ebi.ricordo.rdfstore.bean.QualifierList;
import uk.ac.ebi.ricordo.rdfstore.service.QualifierService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sarala Wimalaratne
 * Date: 30/07/12
 * Time: 11:32
 */
@Controller
public class QualifierController {

    private QualifierService qualifierService;

    private static final String XML_VIEW_NAME = "qualifiers";

    @RequestMapping(method= RequestMethod.GET, value="/bioQualifiers")
    public ModelAndView getBioQualifiers() {
        List<Qualifier> queryList = qualifierService.getQualifierList();
        QualifierList list = new QualifierList(queryList);
        return new ModelAndView(XML_VIEW_NAME, "qualifiers", list);
    }


    public void setQualifierService(QualifierService qualifierService) {
        this.qualifierService = qualifierService;
    }
}
