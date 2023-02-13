package filtering;
import java.util.*;

import metadata.MetadataManagerInterface;
import metadata.MetadataManagerFactory;


public class setupFilteringEngine{
    private MetadataManagerInterface Dt;
    private Map<String, List<String>> at;
    private ArrayList<ArrayList<String>>  dataList = new ArrayList<ArrayList<String>>(); 

    public setupFilteringEngine(Map<String, List<String>> atomicfil,MetadataManagerInterface data){
        this.Dt=data;
        this.at=atomicfil;
    }


    public Map<String, List<String>> Filtering(Map<String, List<String>> data, Map<String, List<String>> atomicfil){ //I methodos pou ine ipefthini gia ta filtra// 

        Map<String, List<String>> filteredMap = new HashMap<String, List<String>>();
        Set<String> sfilterfields = atomicfil.keySet();//Pairnoume ta keys pou einai ta pedia tou file apo to atomicfil k ta vazoume se ena array gia na paroume tis times tous//
        String[] filterfields = new String[sfilterfields.size()];
        sfilterfields.toArray(filterfields);
        Set<String> sdatafields = data.keySet();
        String[] datafields = new String[sdatafields.size()];
        sdatafields.toArray(datafields);
        
        ArrayList<Integer> pos = new ArrayList<Integer>();
        ArrayList<String> file[] = new ArrayList[filterfields.length];
        ArrayList<String> filter[] = new ArrayList[filterfields.length];
        ArrayList<String> copy = new ArrayList<>(data.size());
        
        for(int i=0; i<filterfields.length; i++){
            
            file[i].addAll(data.get(filterfields[i]));
            //file[i] = data.get(filterfields[i]);
            //filter[i] = atomicfil.get(filterfields[i]);
            filter[i].addAll(atomicfil.get(filterfields[i]));

        }

        for(int i=0; i<filterfields.length; i++){
            
            ArrayList<String> checkfile = file[i];
            ArrayList<String> checkfilter = filter[i];
            for(int x=0; x<checkfilter.size(); x++){
                for(int y=0; y<checkfile.size(); y++){

                    if(checkfilter.get(x).equals(checkfile.get(y))){
                        pos.add(y);
                    }
                }
            }
        }

        ArrayList<String> filteredfile[] = new ArrayList[datafields.length];
        ArrayList<String> datafile[] = new ArrayList[datafields.length];
        ArrayList<String> temp = new ArrayList<String>();

        for(int i=0; i<datafields.length; i++){
            
            datafile[i].addAll(data.get(datafields[i]));
        }

        for(int i=0; i<datafields.length;i++){
            
            ArrayList<String> tempdatafile = datafile[i];
            for(int x=0; x<tempdatafile.size(); x++){
                
                temp.add(tempdatafile.get(pos.get(x)));
            }          
            filteredMap.put(datafields[i], temp);
        }

        return filteredMap;

    }
    public int filtertest(Map<String, List<String>>data, Map<String, List<String>>atomicfil){
        int Check = 0;
        Map<String, List<String>> temp = new HashMap<String, List<String>>();
        temp = (Filtering(data, atomicfil));        
        Set<String> sdatafields = data.keySet();
        String[] datafields = new String[sdatafields.size()];
        sdatafields.toArray(datafields);
        ArrayList<String> checkmap[] = new ArrayList[datafields.length];
        for(int i=0; i<datafields.length; i++){
        
            checkmap[i].addAll(temp.get(datafields[i]));
        }
        
        ArrayList<String> temparray = checkmap[0];
        if(temparray.get(0) != null){
            Check = 1;
        }
        
        

        return Check;
    }
}