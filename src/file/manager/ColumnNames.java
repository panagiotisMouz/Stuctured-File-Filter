package file.manager;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnNames extends StructuredFileManagerFactory{



    private File f ;
    private FileReader fr;
    private BufferedReader br;
    private String[] colNames, fileData;
    private Map<String, List<String>> AllFileData = new HashMap<String, List<String>>();
    private char sep;

    //public ColumnNames(){}

    //StructuredFileManagerFactory obj = new StructuredFileManagerFactory();


    public void Names(){
       // this.f = obj.getFile();
        this.f = getFile();
        try {
            fr=new FileReader(f);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        br=new BufferedReader(fr);
        int c = 0;
        int i = 0;
        //sep = obj.getpSeparator().charAt(0);
        this.sep = getpSeparator().charAt(0);
        try {
            while((c = br.read()) != -1){
                char chr = (char) c;
                if (chr != sep ){
                    colNames[i] = colNames[i] + chr;
                }else {
                    i++;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for(int n=0; n<colNames.length; n++){
            AllFileData.put(colNames[n], new ArrayList<String>());
        }
    }

    public void FileToMap(){
        //this.f = getFile();
        try {
            fr=new FileReader(f);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
       // sep = getpSeparator().charAt(0);
        String firstline, line;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            firstline = br.readLine();
            while ((line = br.readLine()) != null) {
                fileData = line.split(",");
                for(int i = 0; i<fileData.length; i++){
                    AllFileData.get(colNames[i]).add(fileData[i]);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String[] getColNames() {
        return Arrays.copyOf(colNames,colNames.length);
    }

    public void setAllFileData(Map<String,List<String>> nEw){
        nEw.putAll(AllFileData);
    } 

}
