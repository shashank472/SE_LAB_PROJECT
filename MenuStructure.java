import java.util.Arrays;
import java.util.Collections;

public class MenuStructure {
    Object Fid;
    Object name;
    Object cost;
    Integer[] Monthly_sale = new Integer[31];
    private Integer Day;


    public void set_Array(){
        for(int i=0;i<31;i++){
            Monthly_sale[i] = 0;
        }
    }

    public float Calculate_mean(){
        float ans = 0;
        float sum = 0;

        for(int i=0;i<31;i++){
            sum += Monthly_sale[i];
        }

        ans = sum/31;
        return ans;
    }

    public int Calculate_median(){
        Arrays.sort(Monthly_sale);
        return Monthly_sale[15];
    }

    public int Calculate_mode(){
        Arrays.sort(Monthly_sale);
         
        // find the max frequency using linear
        // traversal
        int max_count = 1, res = Monthly_sale[0];
        int curr_count = 1;
         
        for (int i = 1; i < 31; i++){
            if (Monthly_sale[i] == Monthly_sale[i - 1])
                curr_count++;
            else{
                if (curr_count > max_count){
                    max_count = curr_count;
                    res = Monthly_sale[i - 1];
                }
                curr_count = 1;
            }
        }
        
        
        // If last element is most frequent
        if (curr_count > max_count)
        {
            max_count = curr_count;
            res = Monthly_sale[31 - 1];
        }

        return res;
    }

    public float Calculate_stddev(){
        float mean = Calculate_mean();
        float res=0;
        for(int i=0; i<31; i++){
            res += Math.pow(Monthly_sale[i]-mean, 2);
        }
        res /= 31;
        return (float)Math.sqrt(res);
        // return res;
    }

    public void addQuantity(int Quant){
        Monthly_sale[Day] = Quant;
        Day = (Day+1)%31;
    }

    public void insert(Object Fid, Object name, Object cost){
        this.Fid = Fid;
        this.name = name;
        this.cost = cost;
    }

    MenuStructure(){
        set_Array();
        Day = 0;
    }

}
