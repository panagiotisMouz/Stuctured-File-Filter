package file.manager;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import filtering.setupFilteringEngine;

public class StructuredFileManagerFactory implements StructuredFileManagerInterface {

    private static final StructuredFileManagerInterface StructuredFileManagerFactory = new StructuredFileManagerFactory();
    private String pAlias;
    private String pPath;
    private String pSeparator;
    private String[] fileColNames;
    private  List<String[]> filterStructFiles;
    private File file;
    private Map<String, List<String>> AllFileData = new HashMap<String, List<String>>();

    public StructuredFileManagerFactory(){}//default costructor

    ColumnNames cn;

    setupFilteringEngine setF;

    @Override
    public File registerFile(String pAlias, String pPath, String pSeparator){
        this.cn = new ColumnNames();
        this.pAlias = pAlias;
        this.pPath = pPath;
        this.pSeparator = pSeparator;
        this.file = new File(pPath,pAlias);
        cn.Names();
        cn.FileToMap();
        return file;
    }

    @Override
    public String[] getFileColumnNames(String pAlias){// epistrefi mia sigkekrimeni stili t pinaka ta onomata olon ton kataxoriseon pou exoun gini. an den exoun gini kata xorisis epistrefi null
        setFileColNames(cn.getColNames());
        return fileColNames;
    }

    @Override
    public List<String[]> filterStructuredFile(String pAlias, Map<String, List<String>> pAtomicFilters){//epistrefi mia lista pou onomata kataxoriseon pou ikanopioun to sintheto filtro
        cn.setAllFileData(AllFileData);
        setF.Filtering(AllFileData, pAtomicFilters); 

        return filterStructFiles;
    }

    @Override
    public int printResultsToPrintStream(List<String[]> recordList, PrintStream pOut){//katefthini tin exodo t filter
        
        return 0;
    }

    public File getFile() {
        return file;
    }

    public String getpSeparator() {
        return pSeparator;
    }

    public void setFileColNames(String[] fileColNames) {
        this.fileColNames = Arrays.copyOf(fileColNames,fileColNames.length);
    }

    public file.manager.StructuredFileManagerInterface createStructuredFileManager() {
        return StructuredFileManagerFactory;
    }

    /*public StructuredFileManagerInterface createStructuredFileManager() {
        return ;
    }*/
}