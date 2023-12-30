//package com.algowizards.finalpricetracker;
//
//import java.util.List;
//
//public class PriceTrackerCoding {
//
//        // Method to generate price trend chart.
//    public static void priceTrend(Product product, PriceCatcherManager listOfPriceCatcher){
//        lookup_item scan4= new lookup_item();
//        List<String[]> scan5 = scan4.scanFile(lookup_item.columnNum, lookup_item.FileLocation);
//        List<String[]> filt3 = scan4.filter(itemcode, 0,scan5);
//        System.out.println("Price trend for "+ filt3.get(0)[1]);
//        // Scanning and filtering a specific itemcode in pricecatcher file and storing into an arraylist.
//        pricecatcher scan=new pricecatcher();
//        String date[]={"2023-08-01","2023-08-02","2023-08-03","2023-08-04","2023-08-05","2023-08-06","2023-08-07","2023-08-08","2023-08-09","2023-08-10","2023-08-11","2023-08-12","2023-08-13","2023-08-14","2023-08-15","2023-08-16","2023-08-17","2023-08-18","2023-08-19","2023-08-20","2023-08-21","2023-08-22","2023-08-23","2023-08-24","2023-08-25","2023-08-26","2023-08-27","2023-08-28","2023-08-29"};
//        List<String[]> scan1 = scan.scanFile(pricecatcher.columnNum, pricecatcher.FileLocation);
//        List<String[]> filt = scan.filter(itemcode, 2,scan1);
//        System.out.println("DAY  |    PRICE");
//        System.out.println("-------------------");
//        double total=0,average;
//        double[]arr=new double[29];
//        // For loop to calculate and print price average of every day.
//        for (int j = 0;  j< 29; j++) {
//            total=0;
//            average=0;
//            // Filtering specific dates in the arraylist filt.
//            List<String[]> filt1 = scan.filter(date[j], 0,filt);
//            for (int i = 0; i < filt1.size(); i++) {
//                // Parsing the price of item according to date and calculating its average price.
//                total+=  Double.parseDouble(filt1.get(i)[3]);
//                average = Math.ceil(total/filt1.size() * 10) / 10;
//                //average = (total/filt1.size() * 10) / 10;
//
//        }
//            // Storing all the average prices in an array.
//            arr[j]=average;
//
//        }
//        // For loop to print the symbol, average marks as well as day.
//        for (int k = 0; k < 29; k++) {
//            int day=k+1;
//            if(k<9){
//                System.out.print(day+"    |  ");
//                ptSymbol(arr,k);
//                System.out.printf("%.2f",arr[k]);
//                System.out.println("");
//            }
//            else{
//                System.out.print(day+"   |  ");
//                ptSymbol(arr,k);
//                System.out.printf("%.2f",arr[k]);
//                System.out.println("");
//            }
//
//        }
//
//    }
//    // Method to generate the $ symbols used in price trend.
//    public static void ptSymbol(double[] arr,int j){
//        // Finding the biggest number in array.
//        double max=arr[0];
//        double min=arr[0];
//        //loop to find the min and max value
//        for (int i = 1; i < arr.length; i++) {
//            if(arr[i]>max){
//                max=arr[i];
//            }
//            else if(arr[i]<min){
//                min=arr[i];
//            }
//        }
//        // Scaling factor to map values to the range 0-1
//        double scale = (max - min != 0) ? 1.0 / (max - min) : 1.0; // $$$
//
//
//        // Scaling the specific value at index j
//        double scaledValue = (arr[j] - min) * scale;
//
//        // Printing the symbols according to the value of average.
//        if(scaledValue>=0.9)
//            System.out.print("$$$$$ ");
//        else if (scaledValue>=0.7)
//            System.out.print("$$$$  ");
//        else if (scaledValue>=0.5)
//            System.out.print("$$$   ");
//        else if (scaledValue>=0.3)
//            System.out.print("$$    ");
//        else
//            System.out.print("$     ");
//    }
//
//
//
//
//    public static void topFive(String itemcode){
//        pricecatcher scan= new pricecatcher();
//        lookup_premise scann=new lookup_premise();
//
//        List<String[]> scan1 = scan.scanFile(pricecatcher.columnNum, pricecatcher.FileLocation);
//        List<String[]> filt = scan.filter(itemcode, 2,scan1);
//        List<String[]> scan2 = scan.rankPrice("lowest", 5,filt);
//
//        lookup_item scan4= new lookup_item();
//        List<String[]> scan5 = scan4.scanFile(lookup_item.columnNum, lookup_item.FileLocation);
//        List<String[]> filt3 = scan4.filter(itemcode, 0,scan5);
//
//
//
//
//        System.out.println("TOP 5 CHEAPEST SELLERS FOR " + filt3.get(0)[1]);
//        System.out.println("");
//
//        for (int j = 0; j <5; j++) {
//            List<String[]> scan3 = scann.scanFile(lookup_premise.columnNum, lookup_premise.FileLocation);
//            List<String[]> filt1 = scann.filter(Double.toString(Double.parseDouble(scan2.get(j)[1])), 0,scan3);
//
//            System.out.println(filt1.get(0)[1]);
//            System.out.println("PRICE : RM"+scan2.get(j)[3]);
//            System.out.println("ADDRESS : " +filt1.get(0)[2]);
//            System.out.println("");
//        }
//
//
//
//    }
//
//    public static void main(String[] args) {
//
//        String itemcode="70";
//        priceTrend(itemcode);
//        System.out.println("");
//        topFive(itemcode);
//
//    }
//
//}
