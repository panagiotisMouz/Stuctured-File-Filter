
import java.util.*;


import application.chart.management.BarChartViewerSingleSeries;
import application.chart.management.LineChartViewerSingleSeries;
import application.chart.management.VisualizationEngine;

public class MainEngine{
    public static void main(String[] args) {
    
        NaiveApplicationController Engine = new NaiveApplicationController();
        BarChartViewerSingleSeries B = new BarChartViewerSingleSeries();
        LineChartViewerSingleSeries L = new LineChartViewerSingleSeries();
        VisualizationEngine VE = new VisualizationEngine(); 
        String alias;
        String path;
        String outputFileName ="";
        String separator;
        String key;
        String fn1;
        String fieldNameF;
        String[] fieldNameValues,fn;
        List<String> keyValues = new ArrayList<String>();
        Map <String,List<String>> atomicFilters = new HashMap<String,List<String>>();;
        List<String[]> result = new ArrayList<String[]>();

        Scanner read = new Scanner(System.in);

        System.out.println("FILE FILTERING");
        System.out.println();

        System.out.println("Give File Name: ");
        alias = read.nextLine();

        System.out.println("Give File Path: ");
        path = read.nextLine();

        System.out.println("Give File Separator: ");
        separator = read.nextLine();

        Engine.registerFile(alias, path, separator);

        System.out.println("You want to add a filter?(yes/no): ");
        if(read.nextLine().equals("no")){
            System.exit(-1);
        }

        System.out.println("You want an atomic filter OR conjunctive?(atomic/conjunctive): ");
        if(read.nextLine().equals("atomic")){

            System.out.println("Give the atomic field you want to filter: ");
            key = read.nextLine();

            System.out.println("Give values of the field you want to filter(use space for multiple values): ");
            fieldNameF = read.nextLine();
            fieldNameValues = fieldNameF.split(" ");

            // vazei ta values tou fieldName stin lista 
            for(int i=0; i<fieldNameValues.length; i++){
                keyValues.add(fieldNameValues[i]);
            }

            // Map me to fieldName(pou einai to key) kai to list me ta values tou 
            atomicFilters.put(key,keyValues);

            // Onoma output file
            System.out.println("Give a name for your output file: ");
            outputFileName = read.nextLine();

            // rotame ton xristi an thelei na emfanisei table me ta atomika filtra
            System.out.println("If you want to display a table with the filter type 'yes' : ");
        
            // apotelesma filtrou se table 
            if(read.nextLine().equals("yes")){
                result = Engine.executeFilterAndShowJTable(alias, atomicFilters, outputFileName);
            }

            // rotame ton xristi an thelei na apothikefsei to file me ta results
            System.out.println("If you want to save a file with the filtered results type 'yes' : ");

            // apothikevoume to file me ta results
            if(read.nextLine().equals("yes")){
                Engine.saveToResultTextFile(outputFileName, result);
            }
        }
        if(read.nextLine().equals("conjunctive")){
            
            System.out.println("Type the number of filters you want to input: ");
            String str  = read.nextLine();
            int num = Integer.parseInt(str);

            for(int i=0; i<num; i++){
                System.out.println("Give a field you want to filter: ");
                key = read.nextLine();
            
                System.out.println("Give values of field:" + key + " you want to filter(use space for multiple values): ");
                fieldNameF = read.nextLine();
                fieldNameValues = fieldNameF.split(" ");

                // vazei ta values tou fieldName stin lista 
                for(int j=0; j<fieldNameValues.length; j++){
                    keyValues.add(fieldNameValues[j]);
                }

                // Map me to fieldName(pou einai to key) kai to list me ta values tou 
                atomicFilters.put(key,keyValues);

                // empty fieldNameValues
                fieldNameValues = null;
            }
            
            // Onoma output file
            System.out.println("Give a name for your output file: ");
            outputFileName = read.nextLine();

            // rotame ton xristi an thelei na emfanisei table me ta sintheta filtra
            System.out.println("If you want to display a table with the filter type 'yes' : ");
        
            // apotelesma filtrou se table 
            if(read.nextLine().equals("yes")){
                result = Engine.executeFilterAndShowJTable(alias, atomicFilters, outputFileName);
            }

            // rotame ton xristi an thelei na apothikefsei to file me ta results
            System.out.println("If you want to save a file with the filtered results type 'yes' : ");

            // apothikevoume to file me ta results
            if(read.nextLine().equals("yes")){
                Engine.saveToResultTextFile(outputFileName, result);
            }
        }

        System.out.println("If you want to display a chart with the filtered results type 'yes' : ");
        
        //
        if(read.nextLine().equals("yes")){
            System.out.println("You want to display the results in a chart?(Type: BarChart OR LineChart OR both): ");
            if(read.nextLine().equals("BarChart")){
                System.out.println("Give the 2 fields you want to display on the BarChart: ");
                fn1 = read.nextLine();
                fn = fn1.split(" ");
                String pXAxisName = fn[0];
                String pYAxisName = fn[1];
                Engine.showSingleSeriesBarChart(alias, result, pXAxisName, pYAxisName, outputFileName);
                System.out.println("You want to save the BarChart as PNG?(yes/no): ");
                if(read.nextLine().equals("yes")){
                    System.out.println("Give a name for the BarChart: ");
                    String chartNameB = read.nextLine();
                    VE.saveChartAsPng(chartNameB,B.getChart());

                }
            }
            if(read.nextLine().equals("LineChart")){
                System.out.println("Give the 2 fields you want to display on the LineChart: ");
                fn1 = read.nextLine();
                fn = fn1.split(" ");
                String pXAxisName = fn[0];
                String pYAxisName = fn[1];
                Engine.showSingleSeriesLineChart(alias, result, pXAxisName, pYAxisName, outputFileName);
                System.out.println("You want to save the LineChart as PNG?(yes/no): ");
                if(read.nextLine().equals("yes")){
                    System.out.println("Give a name for the LineChart: ");
                    String chartName = read.nextLine();
                    VE.saveChartAsPng(chartName, L.getChart());

                }
            }
            if(read.nextLine().equals("both")){
                System.out.println("Give the 2 fields you want to display on the BarChart and LineChart: ");
                fn1 = read.nextLine();
                fn = fn1.split(" ");
                String pXAxisName = fn[0];
                String pYAxisName = fn[1];
                Engine.showSingleSeriesBarChart(alias, result, pXAxisName, pYAxisName, outputFileName);
                Engine.showSingleSeriesLineChart(alias, result, pXAxisName, pYAxisName, outputFileName);
                
                System.out.println("You want to save the BarChart and LineChart as PNG?(yes/no): ");
                if(read.nextLine().equals("yes")){
                    System.out.println("Give a name for the BarChart: ");
                    String barChartName = read.nextLine();
                    VE.saveChartAsPng(barChartName,B.getChart());
                    System.out.println("Give a name for the LineChart: ");
                    String lineChartName = read.nextLine();
                    VE.saveChartAsPng(lineChartName,L.getChart());

                } 
            }
       
         }
         read.close();
    }
}

