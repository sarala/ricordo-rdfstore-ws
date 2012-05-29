package ricordo.rdfstore.rest.service;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Sarala Wimalaratne
 * Date: 14/05/12
 * Time: 17:03
 */
public class SparqlQuery {
    private ArrayList<String> selectList = new ArrayList<String>();
    private final String SPACE = " ";
    private String queryFile;
    private String ruleClause = "";
    private String fromClause = "";
    private String selectClause = "";
    private String whereClause = "";
    

    public SparqlQuery(String queryFile) {
        this.queryFile = queryFile;
        passQueryFile();
        passSelectClause();
    }

    private void passQueryFile(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(queryFile));
            String stringLine;
            while((stringLine = bufferedReader.readLine()) != null){
                if(stringLine.startsWith("define")){
                    ruleClause = stringLine;
                }
                else if(stringLine.startsWith("SELECT")){
                    selectClause = stringLine;
                }
                else if(stringLine.startsWith("FROM")){
                    fromClause=stringLine;
                }
                else
                    whereClause = whereClause+stringLine;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void passSelectClause(){
        String [] selectArray = selectClause.split(SPACE);
        for (String select :selectArray){
            if(select.startsWith("?")){
                selectList.add(select);
            }
        }        
    }
    
    public String constructWhereClause(String input){
        return whereClause.replace("[]",input);
    }
    
    public String getQuery(String input){
        String query = ruleClause + SPACE +
                selectClause + SPACE +
                fromClause + SPACE;
        
        if(input.isEmpty())
            query += whereClause;
        else
            query += constructWhereClause(input);

        return query;
    }

    public ArrayList<String> getSelectList() {
        return selectList;
    }
}
