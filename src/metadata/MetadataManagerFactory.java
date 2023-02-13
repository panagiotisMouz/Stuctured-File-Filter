package metadata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import file.manager.ColumnNames;
import file.manager.StructuredFileManagerFactory;

import java.io.File;


public abstract class MetadataManagerFactory implements MetadataManagerInterface {
    private String pAlias;
	private File pFile;
	private String pSeparator;
    ColumnNames n;
    String[] Col;
    StructuredFileManagerFactory stru;
    public MetadataManagerFactory(){}

    public MetadataManagerFactory(String pA,File pf , String sep){
        this.pAlias=pA;
        this.pFile=pf;
        this.pSeparator=sep;
    };
    @Override
    public Map<String, Integer> getFieldPositions(){
        Map<String,Integer> pos = new HashMap<>();
        setCol(n.getColNames());
        String s;
        int n;
        for(int i=0; i<Col.length; i++){
            s=Col[i];
            n=i;
            pos.put(s,n);
        }
        return pos;
    }
    
    @Override
    public File getDataFile(){
        File y=stru.getFile();
        
        return y;
    }
    @Override
    public String getSeparator(){
        return stru.getpSeparator();
    }
    @Override
    public String[] getColumnNames(){
        setCol(n.getColNames());
        return Col;
    }

    public void setCol(String[] Col){
        this.Col = Arrays.copyOf(Col,Col.length);
    }
    public String getAlias(){
        return this.pAlias;
    }
}
