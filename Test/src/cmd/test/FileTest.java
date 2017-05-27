package cmd.test;

import java.io.File;

public class FileTest {
	
	public static double fileSize(String path) {
		File file = new File(path);
		return getDirSize(file);
	}
	
	public static double getDirSize(File file) { 
		
        //�ж��ļ��Ƿ����     
        if (file.exists()) {     
            //�����Ŀ¼��ݹ���������ݵ��ܴ�С    
            if (file.isDirectory()) {     
                File[] children = file.listFiles();     
                double size = 0;     
                for (File f : children)     
                    size += getDirSize(f);     
                return size;     
            } else {//������ļ���ֱ�ӷ������С,�ԡ��ס�Ϊ��λ   
                double size = (double) file.length() / 1024 / 1024;        
                return size;     
            }     
        } else {     
            System.out.println("�ļ������ļ��в����ڣ�����·���Ƿ���ȷ��");     
            return 0.0;     
        }     
    }
    
}  

/*public static void main(String[] args) {  
    String commandStr = "pdf2swf";
    String testString = CommandTest.exeCmd(commandStr);
    System.out.println(testString);
}  */
