package library.web.controllers;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestUtils;

class RequestUtils{
    public static List<Boolean> getSelectionList(HttpServletRequest request, 
        String propName){
        int[] clientSelection = 
            ServletRequestUtils.getIntParameters(request, propName);
        List<Boolean> selection = new ArrayList<Boolean>();
        for(int i = 0; i< clientSelection.length; i++) {
            
            if(clientSelection[i] == 0){
                selection.add(Boolean.TRUE);
                i++;
            } else {
                selection.add(Boolean.FALSE);
            }
            
        }
        
        return selection;
    }
}
