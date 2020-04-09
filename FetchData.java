package com.angulatpac;

import java.sql.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.lang.management.*;
import java.util.concurrent.TimeUnit;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class FetchData {
 public static void main(String []args){
     float rup=0.0f,dup=0.0f;
     try{
        while(true){
     	   // 1. Registering the Driver 
            Class.forName("com.mysql.jdbc.Driver"); 
            // 2. Creating connection between java code and database
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/report?useSSL=false","root","password"); 
            // 3. Creating statement
            Statement stmt=(Statement) con.createStatement();
            
    		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
 		   // Reading current date and time of the system
    		   LocalDateTime currDateTime = LocalDateTime.now();
    		   
            // Calculating amount of RAM used in %
 		   long freeSpace = new File("/").getFreeSpace();
            long memorySize=new File("/").getTotalSpace();
            long usedRamSpace=memorySize-freeSpace; 
            rup=(float)(usedRamSpace*100)/memorySize; 
            rup=(float) (Math.round(rup * 100.0) / 100.0);
            
            // Calculating amount of DISK used in %
            long diskSize= ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
            long feeSize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getFreePhysicalMemorySize();
            long usedDiskSpace=diskSize-feeSize;     
            dup=(float)(usedDiskSpace*100)/diskSize; 
            dup=(float) (Math.round(dup * 100.0) / 100.0);
            
            // Calculating CPU load in %
            double CPU=((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getSystemCpuLoad(); 
            CPU=CPU*100;
            CPU=(Math.round(CPU * 100.0) / 100.0);
            
            // Printing RAM , DISK and CPU utilization on console
            System.out.println("Reading Date And Time= "+dtf.format(currDateTime));
            System.out.println("Ram Used= "+rup+"%");
            System.out.println("Disk Used= "+dup+"%");
            System.out.println("CPU used= "+CPU+"%"); 
            System.out.println("------------------------------------------");
            // 4. Executing query 
            // Inserting the RAM,DISK,CPU utilization in the Raw table 
            stmt.executeUpdate("insert into storePercentData(ramUti,diskUti,cpuUti,redDateTime)values('"+rup+"','"+dup+"','"+CPU+"','"+currDateTime+"')");    
            
            if(HelperClass.getTrueOrFalse()){
         	   break;
            }
            
            try{ TimeUnit.SECONDS.sleep(3);}        
            catch(InterruptedException E){System.out.println(E);}
           
           // 5. Closing the connection
           con.close();
           stmt.close();
           
       }//end of while     
    }//end of try     
    catch(Exception e){        
        System.out.println(e);   
    }
 }//end of main
}//end of class

