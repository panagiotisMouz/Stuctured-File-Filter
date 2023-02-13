package filtering;

//import java.io.File;
//import java.util.Collection;
import java.util.List;
import java.util.Map;

import metadata.MetadataManagerInterface;

public class FilteringEngineFactory implements FilteringEngineInterface {
    private MetadataManagerInterface Dt;
    private Map<String, List<String>> at;//plires file
    
    public FilteringEngineFactory(){}//default costructor

    public FilteringEngineFactory(Map<String, List<String>> atomicfil,MetadataManagerInterface data ){
        this.Dt=data;
        this.at=atomicfil;
    }

    @Override
    public int setupFilteringEngine(Map<String, List<String>> pAtomicFilters,MetadataManagerInterface pMetadataManager){
       setupFilteringEngine next=new setupFilteringEngine(pAtomicFilters, pMetadataManager);
       next.Filtering(at,pAtomicFilters);
       return next.filtertest(at,pAtomicFilters);
    }
    @Override 
    public List<String[]> workWithFile(){
       

     //Map<String, List<String>> fp=(Map<String, List<String>>) atomicfil.entrySet().stream().filter(p->getkey().intValue().startWith("A")).collect(Collectors.toMap(map->map.getKey(),map->map.getvalue()));
		return null;

    
    }
     /*  public Map<String, List<String>> singleFiltering(Map<String, List<String>> unfilteredmap){ //I methodos pou ine ipefthini gia ta apla filtra// 

        Map<String, List<String>> filteredMap= new HashMap<>();//ena kainourio map pou tha mpei mesa to filtrarismeno arxeio//
        int filterlength = unfilteredmap.get(atomicfil(List.size());
        for (int i=0; i <= filterlength; i++){//vazoume to pedio to opio theloume na filtraristei sto kainourio map//

            filteredMap.put(atomicfil,List.add(unfliteredmap.get(atomicfil(List.get(i))) );


        }
        return filteredMap;
    }

    public Map<String, List<String>> customFiltering(Map<String, List<String>> filteredmap, Map<String, List<String>> customfilter){

        
        */

}   
