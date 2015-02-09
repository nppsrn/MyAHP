
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noppasorn
 */
public class FactorCriteria {
    static private int _TOTAL_FACTOR;
    static private String [] _FACTORS;
    static private double [][] _COMPARE_FACTORS; 
    static private int _NUMBER_COMPARISON;
    static private double [] _COMPARE_ARR;
    static private double [] _SUM_COMPARE_FACTOR;
    static private double [] _SUM_FACTOR_WEIGHT_ROW;
    static private double [] _SUM_FACTOR_WEIGHT_COL;
    static private double [][] _FACTORS_WEIGHT;
    DecimalFormat df = new DecimalFormat("#0.00"); 
    
    
    static private int[] set_of_defect_sev = new int[]{1,2,4,5,5};

    public FactorCriteria(int total_factor, String [] factors) {
        _TOTAL_FACTOR = total_factor;
        _FACTORS = factors;
        _COMPARE_FACTORS = new double[_TOTAL_FACTOR][_TOTAL_FACTOR];
        // _COMPARE_FACTORS = new double[][]{{1,9,3},{0.11,1,0.2},{0.33,5,1}};
        _NUMBER_COMPARISON = _TOTAL_FACTOR * _TOTAL_FACTOR;
        _COMPARE_ARR = new double[_NUMBER_COMPARISON];
        _SUM_COMPARE_FACTOR = new double[_TOTAL_FACTOR];
        _SUM_FACTOR_WEIGHT_ROW = new double[_TOTAL_FACTOR];
        _SUM_FACTOR_WEIGHT_COL = new double[_TOTAL_FACTOR];
        
        _FACTORS_WEIGHT = new double[_TOTAL_FACTOR][_TOTAL_FACTOR];
        
    }
    
    public int getTotalFactors(){
        return _TOTAL_FACTOR;
    }
    
    public String[] getFactors(){
        return _FACTORS;
    }
    
    private double getDefectCompareRate(int a, int b){
        
        double rate = 0.0;
        int gap = 0;
        double level = 0.0;
        gap = Math.abs(a - b);
        switch(gap){
            case 0: level = 1; break;
            case 1: level = 3; break;
            case 2: level = 5; break;
            case 3: level = 7; break;
            case 4: level = 9; break;
        }
        if(a < b){
            rate = 1/level;
        } else{
            rate = level;
        }
        return rate;
        
    }
    
    public void setFactorComparation(){
        for(int i = 0; i < _TOTAL_FACTOR ; i++){
            for(int j = 0; j<_TOTAL_FACTOR ; j++){
                double test;
                System.out.println("Compare "+_FACTORS[i]+" with "+_FACTORS[j]+":");
                test = getDefectCompareRate(set_of_defect_sev[i],set_of_defect_sev[j]);
                _COMPARE_FACTORS[i][j] = test;
                //_COMPARE_FACTORS[i][j] = keyboard.nextDouble();
            }
        }
        System.out.println();
        
       
    }
    
    public void calSumFactor(){
        double sum = 0.0;
        for(int i = 0; i< _TOTAL_FACTOR;i++){
            sum = 0.0;
            for(int j = 0; j<_TOTAL_FACTOR;j++){
                sum += _COMPARE_FACTORS[j][i];
            }
            _SUM_COMPARE_FACTOR[i] = sum;
        }
    }
    
    public void calImportantWeight(){
        
        double sumRow;
        double sumCol;
        
        
        for(int i = 0 ; i < _TOTAL_FACTOR ; i++ ){
            sumRow = 0.0;
            for(int j = 0 ; j < _TOTAL_FACTOR ; j++ ){
                 _FACTORS_WEIGHT[i][j] = _COMPARE_FACTORS[i][j] / _SUM_COMPARE_FACTOR[j];
                 sumRow += _FACTORS_WEIGHT[i][j];
            }
            _SUM_FACTOR_WEIGHT_ROW[i] = (sumRow / _TOTAL_FACTOR) * 100;
        }
        
        for(int i = 0; i< _TOTAL_FACTOR;i++){
            sumCol = 0.0;
            for(int j = 0; j<_TOTAL_FACTOR;j++){
                sumCol += _FACTORS_WEIGHT[j][i];
            }
            _SUM_FACTOR_WEIGHT_COL[i] = sumCol;
        }
    }
    
    public void printFactorCriteriaMetrix(){
        System.out.println("Decision Matrix");
        for(int i = 0; i< _TOTAL_FACTOR;i++){
            for(int j = 0; j<_TOTAL_FACTOR;j++){
                System.out.print(df.format(_COMPARE_FACTORS[i][j]) + "\t");
            }
            System.out.println();
        }
        System.out.println("==================================================");
        for(int k = 0; k< _SUM_COMPARE_FACTOR.length;k++){
            System.out.print(df.format(_SUM_COMPARE_FACTOR[k]) + "\t");
        }
        System.out.println();
        System.out.println("==================================================");
        
        System.out.println("Weight Matrix");
        
        for(int i = 0; i< _TOTAL_FACTOR;i++){
            for(int j = 0; j<_TOTAL_FACTOR;j++){
                System.out.print(df.format(_FACTORS_WEIGHT[i][j]) + "\t");
            }
            System.out.print(">>>>>>\t"+ df.format(_SUM_FACTOR_WEIGHT_ROW[i]) + "\t");
            System.out.println();
        }
        System.out.println("==================================================");
        for(int k = 0; k< _SUM_FACTOR_WEIGHT_ROW.length;k++){
            System.out.print(df.format(_SUM_FACTOR_WEIGHT_COL[k]) + "\t");
        }
        System.out.println();
        System.out.println("==================================================");
        System.out.println("AHP Priority");
        Arrays.sort(_SUM_FACTOR_WEIGHT_ROW);
        for(int k = _SUM_FACTOR_WEIGHT_ROW.length-1 ; k >= 0 ;k--){
            System.out.print(df.format(_SUM_FACTOR_WEIGHT_ROW[k]) + "\t");
        }
    }

    public static void main(String[] args)
    {
        FactorCriteria fc = new FactorCriteria(5, new String[]{"S1","S2","S3","S4","S5"});
        fc.setFactorComparation();
        fc.calSumFactor();
        fc.calImportantWeight();
        fc.printFactorCriteriaMetrix();
    }
}
