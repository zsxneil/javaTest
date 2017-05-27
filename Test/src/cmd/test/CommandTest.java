package cmd.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandTest {  
    public static String exeCmd(String commandStr) {
    	commandStr = "lsnrctl show log_file";
        BufferedReader br = null;  
        StringBuilder sb = new StringBuilder();
        try {  
            Process p = Runtime.getRuntime().exec(commandStr);  
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));  
            String line = null;  
            while ((line = br.readLine()) != null) {  
                sb.append(line + "\n");  
            }  
            System.out.println(sb.toString()); 
            
        } catch (Exception e) { 
            e.printStackTrace();
        }   
        finally  
        {  
            if (br != null)  
            {  
                try {  
                    br.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        } 
        return sb.toString();
    }  
    
}  

/*public static void main(String[] args) {  
    String commandStr = "pdf2swf";
    String testString = CommandTest.exeCmd(commandStr);
    System.out.println(testString);
}  */
