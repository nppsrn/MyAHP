
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noppasorn
 */
public class CriteriaCollection {
    private HashMap<Integer, Integer> _LEVEL_CRITERIA;
    
    public CriteriaCollection(){
        _LEVEL_CRITERIA = new HashMap<>();
    }
    public void setCriteriaCollection(){
        
        for(int i = 0; i < 9; i++){
            _LEVEL_CRITERIA.put(i, 5-5);
        }
    }
}
