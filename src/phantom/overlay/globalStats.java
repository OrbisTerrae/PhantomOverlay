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
public class globalStats {
    private String AppVersion ="1.1.2";
     private String dateStr; 
     private String startTimeStr;
     private String endTimeStr;
     private float  maxAltFloat;
     private float  maxSpeedFloat;
     private float maxlat;
     private float maxlon;
     private float minlat;
     private float minlon;
     private int index;
     private boolean isConstructed = false;
     private String filename;
     private int DurationInt;
     private boolean hasTemp;
     private boolean hasHR;
     private boolean hasPower;
     private int TempInt;     
     private int PowerMaxInt;         
     private int PowerMinInt;      
     private Boolean FCPXMLstandalone;
     private String DateEUStr;
     private double distance;
     private double previousBearing;
     private double prevHSpeed[];

    public float getMaxSpeedFloat() {
        return maxSpeedFloat;
    }

    public void setMaxSpeedFloat(float maxSpeedFloat) {
        this.maxSpeedFloat = maxSpeedFloat;
    }

     
 

    public double[] getPrevHSpeed() {
        return prevHSpeed;
    }


    public void setPrevHSpeed(double prevHSpeed) {
        
        /*System.out.println("***Prev Speed:"+ 
                this.prevHSpeed[0] +" - "+
                this.prevHSpeed[1] +" - "+
                this.prevHSpeed[2] +" - "+
                this.prevHSpeed[3] +" - "+
                this.prevHSpeed[4] +" - "+ "\n");*/
        
        this.prevHSpeed[4]=this.prevHSpeed[3];
        this.prevHSpeed[3]=this.prevHSpeed[2];
        this.prevHSpeed[2]=this.prevHSpeed[1];
        this.prevHSpeed[1]=this.prevHSpeed[0];
        this.prevHSpeed[0]= prevHSpeed;
        
        /*System.out.println("*** New Speed:"+ 
                this.prevHSpeed[0] +" - "+
                this.prevHSpeed[1] +" - "+
                this.prevHSpeed[2] +" - "+
                this.prevHSpeed[3] +" - "+
                this.prevHSpeed[4] +" - "+ "\n");*/
    }
    
    
    public boolean isIsConstructed() {
        return isConstructed;
    }

    public void setIsConstructed(boolean isConstructed) {
        this.isConstructed = isConstructed;
    }

    public double getPreviousBearing() {
        return previousBearing;
    }

    public void setPreviousBearing(double previousBearing) {
        this.previousBearing = previousBearing;
    }

     
    public String getDateEUStr() {
        return DateEUStr;
    }

    public void setDateEUStr(String DateEUStr) {
        this.DateEUStr = DateEUStr;
    }

    public String getAppVersion() {
        return AppVersion;
    }

    public void setAppVersion(String AppVersion) {
        this.AppVersion = AppVersion;
    }

    public Boolean getFCPXMLstandalone() {
        return FCPXMLstandalone;
    }

    public void setFCPXMLstandalone(Boolean FCPXMLstandalone) {
        this.FCPXMLstandalone = FCPXMLstandalone;
    }

    public boolean isHasPower() {
        return hasPower;
    }

    public void setHasPower(boolean hasPower) {
        this.hasPower = hasPower;
    }

    public int getPowerMaxInt() {
        return PowerMaxInt;
    }

    public void setPowerMaxInt(int PowerMaxInt) {
        this.PowerMaxInt = PowerMaxInt;
    }

    public int getPowerMinInt() {
        return PowerMinInt;
    }

    public void setPowerMinInt(int PowerMinInt) {
        this.PowerMinInt = PowerMinInt;
    }
    

    public globalStats() {
        isConstructed = true;
        
        prevHSpeed = new double[] {0,0,0,0,0}; 
    }


    public void setTempInt(int TempFloat) {
        this.TempInt = TempFloat;
    }

    public int getHRInt() {
        return HRInt;
    }

    public void setHRInt(int HRInt) {
        this.HRInt = HRInt;
    }
     private int  HRInt;

    public boolean isHasHR() {
        return hasHR;
    }
    public boolean isHasTemp() {
        return hasTemp;
    }

    public void setHasHR(boolean hasHR) {
        this.hasHR = hasHR;
    }

    public int getDurationInt() {
        return DurationInt;
    }

    public void setDurationInt(int DurationInt) {
        this.DurationInt = DurationInt;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public float getMaxlat() {
        return maxlat;
    }

    public void setMaxlat(float maxlat) {
        this.maxlat = maxlat;
    }

    public float getMaxlon() {
        return maxlon;
    }

    public void setMaxlon(float maxlon) {
        this.maxlon = maxlon;
    }

    public float getMinlat() {
        return minlat;
    }

    public void setMinlat(float minlat) {
        this.minlat = minlat;
    }

    public float getMinlon() {
        return minlon;
    }

    public void setMinlon(float minlon) {
        this.minlon = minlon;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }


    public float getMaxAltFloat() {
        return maxAltFloat;
    }

    public void setMaxAltFloat(float maxAltFloat) {
        this.maxAltFloat = maxAltFloat;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    void setHasTemp(boolean b) {
        hasTemp = b;
    }

    public double getDistance() {
        return distance;
    }
    void setDistance(double totalDistance) {
        distance = totalDistance; 
    }

    int getTempInt() {
        return TempInt; 
    }
     
     
}
