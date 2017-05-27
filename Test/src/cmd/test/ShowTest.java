package cmd.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.*;
public class ShowTest
{
    public static String show(String host) {
    StringBuffer pingResult = new StringBuffer();
    String showCmd = "lsnrctl show trc_directory";
     BufferedReader in;
    try {
      Runtime r = Runtime.getRuntime();
      Process p = r.exec(showCmd);

      in = new BufferedReader(new
      InputStreamReader(p.getInputStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        pingResult.append(inputLine);
        pingResult.append("\n");
       }
      in.close();
      return pingResult.toString();

      }
     catch (IOException e) {
        return e.getMessage();
     }

   }

}

/*public static void main(String[] args) {  
    String commandStr = "pdf2swf";
    String testString = CommandTest.exeCmd(commandStr);
    System.out.println(testString);
}  */
