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
public class exifElement {
    //ISO:478 Shutter:30 EV: 0 Fnum:F2.8 
       private int  ISO;      
       private int  Shutter;        
       private float  EV;      
       private float  Fnum;  
       private String  ISOStr;  
       private String ShutterStr; 
       private String EVStr;
       private String FnumStr;

    public exifElement(String ISOStr, String ShutterStr, String EVStr, String FnumStr) {
        String[] atoms;
        
        //System.out.println("ISO: "+ ISOStr + " - Shutter: "+ ShutterStr + " - EV: "+ EVStr+ " - FNum: "+ FnumStr+"\n");
        atoms = ISOStr.split(":"); 
        if (atoms.length > 0){
            this.ISOStr = atoms[1];
            }
        else
            this.ISOStr = "100";
        this.ISO = Integer.parseInt(this.ISOStr);
      
        atoms = ShutterStr.split(":"); 
        if (atoms.length > 0){
            this.ShutterStr = atoms[1];
            //System.out.println("EV: arg:"+ ShutterStr + " - atom:"+ this.ShutterStr + " - "+ atoms.length+"\n");
            }
        else{
            this.ShutterStr = "0";
        }
//        this.ShutterStr = ShutterStr;
        this.Shutter = Integer.parseInt(this.ShutterStr);
        
        atoms = EVStr.split(":"); 
        if (atoms.length > 0){
            this.EVStr = atoms[1];
            //System.out.println("EV: argstr= "+ EVStr + " - atom:"+ this.EVStr + " - "+ atoms.length+"\n");
          
            String[] EVatom;
            EVatom = this.EVStr.split("/"); 
            
            if (EVatom.length > 1){
                //System.out.println("EV: argstr= "+ EVStr + " ("+atoms.length+")"+ "- EVatom= "+ EVatom + "("+EVatom.length+")"+"\n");
                for(int i=0 ; i < EVatom.length ; i++){
//                    System.out.println("Exif EVatom ["+i+"]: "+EVatom[i]+"\n");
                }
                int top = 0;
                int bottom = 0;
                top = Integer.parseInt(EVatom[0]);
                bottom = Integer.parseInt(EVatom[1]);
                this.EV = (float) top / (float) bottom;
//                System.out.println("Exif EV:"+this.EV+" top:"+top+" bottom:"+bottom+"\n");
            }
            else{
                this.EV = Integer.parseInt(this.EVStr);        
            }
        }
        else{
            this.EVStr = "0";
            this.EV = 0;
            this.EV = Integer.parseInt(this.EVStr);
        }
        
        
        atoms = FnumStr.split(":"); 
        if (atoms.length > 0){
            this.FnumStr = atoms[1];
            //System.out.println("Fnum: arg:-"+ FnumStr + "-split:"+atoms.length+" - atom:1"+ this.FnumStr + " - atom0"+ atoms[0]+"\n");
             }
        else{
            this.FnumStr = "2.8";
        }
        this.Fnum = Float.parseFloat(this.FnumStr.substring(1));
        //System.out.println("EXIF : "+ ISO + " - "+ Shutter + " - "+ EV + " - " + Fnum + "\n");
    }

    public int getISO() {
        return ISO;
    }

    public void setISO(int ISO) {
        this.ISO = ISO;
    }

    public int getShutter() {
        return Shutter;
    }

    public void setShutter(int Shutter) {
        this.Shutter = Shutter;
    }

    public float getEV() {
        return EV;
    }

    public void setEV(float EV) {
        this.EV = EV;
    }

    public float getFnum() {
        return Fnum;
    }

    public void setFnum(float Fnum) {
        this.Fnum = Fnum;
    }

    public String getISOStr() {
        return ISOStr;
    }

    public void setISOStr(String ISOStr) {
        this.ISOStr = ISOStr;
    }

    public String getShutterStr() {
        return ShutterStr;
    }

    public void setShutterStr(String ShutterStr) {
        this.ShutterStr = ShutterStr;
    }

    public String getEVStr() {
        return EVStr;
    }

    public void setEVStr(String EVStr) {
        this.EVStr = EVStr;
    }

    public String getFnumStr() {
        return FnumStr;
    }

    public void setFnumStr(String FnumStr) {
        this.FnumStr = FnumStr;
    }
    
}
