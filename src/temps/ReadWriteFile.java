package temps;

import java.io.*;
import java.util.*;

public class ReadWriteFile {

    public static List<String> readFile(String readFile) throws IOException{
        List<String> tempList = new LinkedList<String>();

        FileReader fr = new FileReader(readFile);
        BufferedReader br = new BufferedReader(fr);
        String line = "";

        while((line=br.readLine())!=null){
            line = line.replace("\uFEFF", "");
            line = line.replace("\\n", "\n");
            tempList.add(line);
        }

        br.close();
        fr.close();

        System.out.println("File read");

        return tempList;        
    }

    public static void writeFile(String folder,String writeFile, List<String> writeList) throws IOException{
        folder = folder+"\\";
        File folderpath = new File(folder);
        if(!folderpath.exists()){folderpath.mkdir();}

        FileWriter fw = new FileWriter(folder+writeFile,true);
        BufferedWriter bw = new BufferedWriter(fw);
        
        for (String item:writeList){
            bw.write(item);
        }
        
        bw.close();
        fw.close();

        System.out.println("File saved");
    }
}
