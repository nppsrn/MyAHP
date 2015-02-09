/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noppasorn
 */
public class SeverityCriteria {
    private CriteriaCollection cc ;
    SeverityCriteria(){
        cc = new CriteriaCollection();
        cc.setCriteriaCollection();
    }
    public void setFactorComparation(){
        if(d1.severity > d2.severity){
            
        }
        else if(d1.severity < d2.severity){
            temp = d2.severity - d1.severity;
            level = Hash.getLevel(temp);
        }
        else {
            temp = 0;
            level = Hash.getLevel(temp);
        }
        
    }
    
    private void setSeverityCriteria(){
        int maxSeverityLevel = 5; 
        for(int i = 0; i < 9; i++){
            cc._LEVEL_CRITERIA.put(i, maxSeverityLevel-(maxSeverityLevel--));
        }
    }
}
