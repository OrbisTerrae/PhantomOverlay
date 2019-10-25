/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phantom.overlay;

/**
 *
 * @author gbourelly
 */
public class gpsElement {
        private float  homeGPS1Float;      
        private float  homeGPS2Float;      
        private int  yearSRT;         
        private int  monthSRT;         
        private int  daySRT;      
        private int  hourSRT;        
        private int  minSRT;        
        private int  secSRT;      
        private float  droneGPS1Float;  
        private float  droneGPS2Float;  
        private String dateStr; 
        private String timeStr;
        private String LatZeroStr; 
        private String LonZeroStr;
        private String LatShortStr; 
        private String LonShortStr;
        private String AltStr;
        private float  AltFloat; 
        private float  TempFloat;         
        private int  HRInt;
        private boolean hasTemp;
        private float  PowerFloat; 
        private boolean hasPower;
        private boolean missData;
        private boolean missHomeData;
        private boolean hasHR;
        private double bearing;
        private String directionStr;
        private final double radius = 6371000; // earth's mean radius in km
        private double distance;
        private double onGoingDistance;
        private double speed;        
        private double prevHSpeed[];
        private String markerStr;

    public String getMarkerStr() {
        return markerStr;
    }

    public void setMarkerStr(String markerStr) {
        this.markerStr = markerStr;
    }
        

    public boolean isMissHomeData() {
        return missHomeData;
    }

    public void setMissHomeData(boolean missHomeData) {
        this.missHomeData = missHomeData;
    }
        
   public double getCurHSpeed() {
        double speed=0;
        speed = (
                prevHSpeed[0]+
                prevHSpeed[1]+
                prevHSpeed[2] )/ 3;
/*        System.out.println("*** Old Speed:"+ 
                this.prevHSpeed[0] +" - "+
                this.prevHSpeed[1] +" - "+
                this.prevHSpeed[2] +" - "+
                this.prevHSpeed[3] +" - "+
                this.prevHSpeed[4] +" - "+ "\n");*/
        if(speed != 0){
//            System.out.println("***Norm Speed:"+ speed + "m/s\n");
        }
        return speed * 3.6;
    }

    public double[] getPrevHSpeed() {
        return prevHSpeed;
    }

    public void setPrevHSpeed(double[] prevHSpeed) {
        this.prevHSpeed[0] = prevHSpeed[0];
        this.prevHSpeed[1] = prevHSpeed[1];
        this.prevHSpeed[2] = prevHSpeed[2];
        this.prevHSpeed[3] = prevHSpeed[3];
        this.prevHSpeed[4] = prevHSpeed[4];
    }

    public double getOnGoingDistance() {
        return onGoingDistance;
    }

    public void setOnGoingDistance(double onGoingDistance) {
        this.onGoingDistance = onGoingDistance;
    }

    public double getBearing() {
        return bearing;
    }

    public double getDistance() {
        return distance;
    }
    

// Helper function to convert degrees to radians
    public double DegToRad(double deg) {
        return (deg * Math.PI / 180);
    }

// Helper function to convert radians to degrees
    public double RadToDeg(double rad) {
        return (rad * 180 / Math.PI);
    }
    
    
    public void setDistance(double nextLat, double nextLon) {
        // calc between 2 gps points
    double lat1 = DegToRad(droneGPS1Float);
    double lon1 = DegToRad(droneGPS2Float);

    double lat2 = DegToRad(nextLat);
    double lon2 = DegToRad(nextLon);

    double deltaLat = lat2 - lat1;
    double deltaLon = lon2 - lon1;

    double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    distance = (radius * c);
}
    
    public String getDirectionStr(){
        // give direction / bearing in coordinates
        String coordNames[] = {" N ","NNE", " NE","ENE"," E ", "ESE"," SE","SSE", " S ","SSW", " SW","WSW", " W ","WNW", " NW","NNW", " N "};
          double directionid = Math.round(bearing / 22.5); 
          // no of array contain 360/16=22.5
          if (directionid < 0) {
              directionid = directionid + 16;
          }
          String compasLoc=coordNames[(int) directionid];
          return compasLoc;
    }
    
    public void setBearing(double bearing) {
        this.bearing = bearing;
    }
    
    public void setBearing(double lat2, double lon2) {
        // calculate direction / bearing in degrees
        double longitude1 = droneGPS2Float;
        double longitude2 = lon2;
        double latitude1 = Math.toRadians(droneGPS1Float);
        double latitude2 = Math.toRadians(lat2);
        double longDiff= Math.toRadians(longitude2-longitude1);
        double y= Math.sin(longDiff)*Math.cos(latitude2);
        double x=Math.cos(latitude1)*Math.sin(latitude2)-Math.sin(latitude1)*Math.cos(latitude2)*Math.cos(longDiff);

       bearing = (Math.toDegrees(Math.atan2(y, x))+360)%360;
    }
       

    public boolean isMissData() {
        return missData;
    }

    public void setMissData(boolean missData) {
        this.missData = missData;
    }

    public float getPowerFloat() {
        return PowerFloat;
    }

    public void setPowerFloat(float PowerFloat) {
        this.PowerFloat = PowerFloat;
    }

    public boolean isHasPower() {
        return hasPower;
    }

    public void setHasPower(boolean hasPower) {
        this.hasPower = hasPower;
    }

    public String getLatShortStr() {
        return LatShortStr;
    }

    public void setLatShortStr(String LatShortStr) {
        this.LatShortStr = LatShortStr;
    }

    public String getLonShortStr() {
        return LonShortStr;
    }

    public void setLonShortStr(String LonShortStr) {
        this.LonShortStr = LonShortStr;
    }

    public float getTempFloat() {
        return TempFloat;
    }

    public void setTempFloat(float TempFloat) {
        this.TempFloat = TempFloat;
    }

    public int getHRInt() {
        return HRInt;
    }

    public void setHRInt(int HRInt) {
        this.HRInt = HRInt;
    }

    public boolean isHasTemp() {
        return hasTemp;
    }

    public void setHasTemp(boolean hasTemp) {
        this.hasTemp = hasTemp;
    }

    public boolean isHasHR() {
        return hasHR;
    }

    public void setHasHR(boolean hasHR) {
        this.hasHR = hasHR;
    }
       
    public String getAltStr() {
        return AltStr;
    }

    public void setAltStr(String AltStr) {
        this.AltStr = AltStr;
    }

    public String getLatZeroStr() {
        return LatZeroStr;
    }

    public void setLatZeroStr(String LatZeroStr) {
        this.LatZeroStr = LatZeroStr;
    }

    public String getLonZeroStr() {
        return LonZeroStr;
    }

    public void setLonZeroStr(String LonZeroStr) {
        this.LonZeroStr = LonZeroStr;
    }

    public String getDateStr() {
        return dateStr;
    }
    public String getDateEUStr() {
        // show date in European format
        String    dateEUStr = daySRT+"/"+monthSRT+"/"+yearSRT;
        return dateEUStr;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setHomeGPS1Float(float homeGPS1Float) {
        this.homeGPS1Float = homeGPS1Float;
    }

    public void setHomeGPS2Float(float homeGPS2Float) {
        this.homeGPS2Float = homeGPS2Float;
    }

    public void setYearSRT(int yearSRT) {
        this.yearSRT = yearSRT;
    }

    public void setMonthSRT(int monthSRT) {
        this.monthSRT = monthSRT;
    }

    public void setDaySRT(int daySRT) {
        this.daySRT = daySRT;
    }

    public void setHourSRT(int hourSRT) {
        this.hourSRT = hourSRT;
    }

    public void setMinSRT(int minSRT) {
        this.minSRT = minSRT;
    }

    public void setSecSRT(int secSRT) {
        this.secSRT = secSRT;
    }

    public void setDroneGPS1Float(float droneGPS1Float) {
        this.droneGPS1Float = droneGPS1Float;
    }

    public void setDroneGPS2Float(float droneGPS2Float) {
        this.droneGPS2Float = droneGPS2Float;
    }

    public void setAltFloat(float AltFloat) {
        this.AltFloat = AltFloat;
    }

    public float getHomeGPS1Float() {
        return homeGPS1Float;
    }

    public float getHomeGPS2Float() {
        return homeGPS2Float;
    }

    public int getYearSRT() {
        return yearSRT;
    }

    public int getMonthSRT() {
        return monthSRT;
    }

    public int getDaySRT() {
        return daySRT;
    }

    public int getHourSRT() {
        return hourSRT;
    }

    public int getMinSRT() {
        return minSRT;
    }

    public float getSecSRT() {
        return secSRT;
    }

    public float getDroneGPS1Float() {
        return droneGPS1Float;
    }

    public float getDroneGPS2Float() {
        return droneGPS2Float;
    }

    public float getAltFloat() {
        return AltFloat;
    }
        
                
        
    public gpsElement(String hGPS1, String hGPS2, String date, String time, String dGPS1, String dGPS2, String Alt){
        this.markerStr = "";
    
        missData = false;
        missHomeData = false;
        
        prevHSpeed = new double[] {0,0,0,0,0}; 
        
        if(hGPS1.contains("N/A")){
            homeGPS1Float=(float) -123456789.0;
            missHomeData = true;
        }
        else{                
            homeGPS1Float=Float.parseFloat(hGPS1);    
        }   
        if(hGPS2.contains("N/A")){
            homeGPS2Float=(float) -123456789.0;
            missHomeData = true;
        }
        else{ 
            homeGPS2Float=Float.parseFloat(hGPS2);
        }   
           
        // fix files where GPS data are missing
        if(dGPS1.contains("N/A")){
            droneGPS1Float=(float) -123456789.0;
            missData = true;
            // will be corrected later in setStatData
            LatShortStr = dGPS1;
            LatZeroStr = dGPS1 + "00000000000"; 
        }
        else{                
            droneGPS1Float=Float.parseFloat(dGPS1);
            LatShortStr = dGPS1;
            LatZeroStr = dGPS1 + "00000000000"; 
        }
        if(dGPS2.contains("N/A")){
            droneGPS2Float=(float) -123456789.0;
            missData = true;
            LonShortStr = dGPS2;
            LonZeroStr = dGPS2;
        }
        else{                
            droneGPS2Float=Float.parseFloat(dGPS2);
            LonShortStr = dGPS2;
            LonZeroStr = dGPS2 + "00000000000";
        }
            
        AltFloat=Float.parseFloat(Alt);
        AltStr = Alt;
        dateStr = date;
        timeStr = time;
        hasTemp = false;
        hasHR = false;
        hasPower = false;
        TempFloat = -375;
        HRInt = 0;
        
        //parse time
        String atomsT[] = time.split(":");
        if (atomsT.length > 0 ){   
            hourSRT = Integer.parseInt(atomsT[0]);
            minSRT = Integer.parseInt(atomsT[1]);
            secSRT = Integer.parseInt(atomsT[2]);
        }
        else
            System.out.println("time not recognized:"+ atomsT.length + time + "\n");
        
        //Parse date
        yearSRT = 2000;
        monthSRT = 01;
        daySRT = 01;
        String monthSRTStr;
        String daySRTStr;
        yearSRT = Integer.parseInt(date.substring(0, 4));
        monthSRT = Integer.parseInt(date.substring(5, 7));
        daySRT = Integer.parseInt(date.substring(8, 10));
        if (monthSRT <10){ 
            monthSRTStr = "0"+ monthSRT;
        }
        else{ 
            monthSRTStr = Integer.toString(monthSRT);
        }
        if (daySRT <10){ 
            daySRTStr = "0"+ daySRT;
        }
        else{ 
            daySRTStr = Integer.toString(daySRT);
        }
        dateStr = yearSRT + "-" + monthSRTStr + "-" + daySRTStr;        
    }
}
