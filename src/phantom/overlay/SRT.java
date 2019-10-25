/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phantom.overlay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Random;

public class SRT {
     private static gpsElement datafromSRT[];
     private static exifElement exiffromSRT[];
     private static globalStats gStats;

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    
    public SRT() {
        gStats = new globalStats();// init global variables
        datafromSRT = new gpsElement[6000]; // max flight time = 25m
        exiffromSRT = new exifElement[6000]; // 1 object per second = good margin
    }
    
    public static exifElement[] getExiffromSRT() {
        return exiffromSRT;
    }

    public static void setExiffromSRT(exifElement[] exiffromSRT) {
        SRT.exiffromSRT = exiffromSRT;
    }

    public static gpsElement[] getDatafromSRT() {
        return datafromSRT;
    }

    public static void setDatafromSRT(gpsElement[] datafromSRT) {
        SRT.datafromSRT = datafromSRT;
    }

    public static globalStats getgStats() {
        return gStats;
    }

    public static void setgStats(globalStats gStats) {
        SRT.gStats = gStats;
    }
 
    public static int convertToKML(String filename, int dataRead) throws IOException{
        
        File outFile = new File(filename);
        outFile.createNewFile();
        FileWriter ofstream = new FileWriter(outFile);
        BufferedWriter out = new BufferedWriter(ofstream);
        
        String simpleName;
        simpleName = filename.substring(filename.lastIndexOf('/')+1,filename.length()-8);
       
        String KMLheader =""+
"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<kml  xmlns=\"http://earth.google.com/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\" xmlns:trails=\"http://www.google.com/kml/trails/1.0\">\n" +
"<Document>\n" +
" <name>Phantom Overlay "+simpleName+"</name>\n" +
"  <Placemark>\n"+
"   <name>Phantom Overlay "+
                gStats.getDateStr()+" "+
                gStats.getStartTimeStr()+
"</name>\n";
        
        String KMLcloser =
"       </coordinates>\n" + 
"      </LineString>\n" +
"     </MultiGeometry>\n" +
"   </Placemark>\n";
        int year = Calendar.getInstance().get(Calendar.YEAR);
        out.write(KMLheader);
        out.write("    <Style id=\"OrbisTerrae\">\n" + 
"        <BalloonStyle>\n" +
"        <bgColor>DF8DB2D8</bgColor>\n" +
"        <textColor>FF134E96</textColor>\n" +
"        <text>File: "+gStats.getFilename()+ "<br/>"+
                "Date: "+gStats.getDateEUStr() +"<br/>"+
                "Start: "+gStats.getStartTimeStr() +"<br/>"+
                "End: "+gStats.getEndTimeStr() +"<br/>"+
                "Duration: "+ gStats.getDurationInt() +"s<br/>"+
                "Distance: "+ gStats.getDistance() +"m<br/>"+
                "Max Alt: "+ gStats.getMaxAltFloat()  +"m<br/>");
                
        if(gStats.isHasTemp()){
            out.write("Temp: " + gStats.getTempInt()  +"°C<br/>");
        }
        out.write("https://sites.google.com/site/oterrae/<br/>"+
                
                "Phantom Overlay v"+gStats.getAppVersion()+"<br/>" +
                "Copyright © 2015-"+year+" Orbis Terrae<br/>"+
                "All rights reserved.</text>\n"+
"    </BalloonStyle>\n");
        
        out.write(
"     <LineStyle>\n" + 
"      <color>DF8DB2D8</color>\n" + 
"      <width>5</width>\n" + 
"     </LineStyle>\n" +
"     <PolyStyle>\n" +
"        <color>0f00ff00</color>\n" +
"     </PolyStyle>\n" +
"    <LabelStyle>\n"+
"        <color>EF134E96</color>\n" +
"     </LabelStyle>\n" + 
"     <IconStyle>\n" +
"        <color>EF134E96</color>\n"+
"        <scale>1</scale>\n"+
"        <Icon>\n" +
"          <href>http://maps.google.com/mapfiles/kml/shapes/arrow.png</href>\n" +
"        </Icon>\n" +
"      </IconStyle>\n"+
"    </Style>\n"+
"    <MultiGeometry>\n"+
"     <styleUrl>#OrbisTerrae</styleUrl>\n"+
"     <visibility>1</visibility>\n"
        );
        
        out.write("     <Point><coordinates>");
        out.write(datafromSRT[1].getLonZeroStr() + ","
                        + datafromSRT[1].getLatZeroStr()+ ","
                        + datafromSRT[1].getAltStr() +" ");
        out.write("</coordinates></Point>\n");
        
        out.write(
"     <LineString>\n" +
"      <tessellate>1</tessellate>\n" +
"      <altitudeMode>relativeToGround</altitudeMode>\n" +
"       <coordinates>\n");
        int i=0;
        for (i = 1 ; i <= dataRead; i++){
                out.write(datafromSRT[i].getLonZeroStr() + ","
                        + datafromSRT[i].getLatZeroStr()+ ","
                        + datafromSRT[i].getAltStr() +" ");
        }
        out.write(KMLcloser);
      
        out.write(
"</Document>\n" +
"</kml>");
    out.close();
     return 0;
    }
    
    public static int convertToFCPXMLMotion(String filename, int dataRead) throws IOException{
                
        File outFile = new File(filename);
        outFile.createNewFile();
        FileWriter ofstream = new FileWriter(outFile);
        BufferedWriter out = new BufferedWriter(ofstream);
        
        String simpleName;
        simpleName = filename.substring(filename.lastIndexOf('/')+1,filename.length()-7);
       
        String FCPXMLheader =""+
"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
"<!DOCTYPE fcpxml>\n" +
"<fcpxml version=\"1.3\">\n" +
"	<project name=\"Phantom Overlay\">\n" +
"		<resources>\n" +
"			<media id=\"MotionClip\" name=\""+simpleName+"\">\n" +
"				<sequence format=\"VideoFormatId\">\n" +
"					<spine>\n" +
"						<video offset=\"0s\" ref=\"MotionFileId\" duration=\""
                                                +gStats.getDurationInt()+"s\">\n";
        
        String FCPXMLcloser =""+ 
"						</video>\n" +
"					</spine>\n" +
"				</sequence>\n" +
"			</media>\n" +
"			<effect id=\"MotionFileId\" name=\"Motion\" uid=\"~/Generators.localized/Orbis Terrae/Phantom Overlay/test template/test template.motn\"/>\n" +
"			<format id=\"VideoFormatId\" name=\"FFVideoFormatRateUndefined\"/>\n" +
"		</resources>\n" +
"		<ref-clip ref=\"MotionClip\" name=\"Phantom Overlay\" duration=\""
                +gStats.getDurationInt()+"s\"/>\n" +
"	</project>\n" +
"</fcpxml>";
        
        out.write(FCPXMLheader);
        out.write(
"<param name=\"Altitude\" key=\"9999/999669649/100/999669664/2/100\"\n" + 
"keyValues=\"");
        
        int i=0;
        float valeur;
        
        for (i = 1 ; i <= dataRead-1; i++){
            valeur = datafromSRT[i].getAltFloat() / gStats.getMaxAltFloat();
            if (valeur<0)
                valeur=0;
            out.write(Float.toString(valeur)+"; ");
        }
        valeur = datafromSRT[dataRead-1].getAltFloat()/ gStats.getMaxAltFloat();
        out.write(Float.toString(valeur));
        out.write("\"\nkeyTimes=\"");
        for (i = 1 ; i < dataRead-1; i++){
            out.write(i+"s; ");
        }
        out.write((dataRead-1)+"s");
        out.write("\"/>\n");
        
        
        out.write(
"<param name=\"AltitudeValue\"\n" +
"key=\"9999/999670558/999670564/2/513/500\"\n" +
"keyValues=\"");
        for (i = 1 ; i <= dataRead -1; i++){
            valeur = datafromSRT[i].getAltFloat();
            if (valeur<0)
                valeur=0;
            out.write(Float.toString(valeur)+"; ");
        }
        valeur = datafromSRT[dataRead-1].getAltFloat();
        out.write(Float.toString(valeur));
        
        out.write("\"\nkeyTimes=\"");
        for (i = 1 ; i < dataRead -1; i++){
            out.write(i+"s; ");
        }
        out.write((dataRead -1)+"s");
        out.write("\"/>\n");
        
        if(gStats.isHasTemp()){
            out.write(
"<param name=\"Value\"\n" +
"key=\"9999/999690331/999677365/2/513/500\"\n" +
"keyValues=\"");
        
            for (i = 1 ; i <= dataRead -1; i++){
                valeur = datafromSRT[i].getTempFloat();
                out.write(Float.toString(valeur)+"; ");
            }
            valeur = datafromSRT[dataRead-1].getTempFloat();
            out.write(Float.toString(valeur));
            out.write("\"\nkeyTimes=\"");
        
            for (i = 1 ; i < dataRead -1; i++){
                out.write(i+"s; ");
            }
            out.write((dataRead -1)+"s");
        
            out.write("\"/>\n");
        }
        out.write(
"<param name=\"Temp Enabled\" key=\"9999/999673227/100/999679910/2/100\" value=\"1\"/>\n"+
"<param name=\"Bearing Enabled\" key=\"9999/999673227/100/999686129/2/100\" value=\"1\"/>\n"+
"<param name=\"Speed Enabled\" key=\"9999/999673227/100/999673245/2/100\" value=\"0\"/>\n"+
"<param name=\"Altitude Enabled\" key=\"9999/999673227/100/999673235/2/100\" value=\"1\"/>\n");
       
        out.write(FCPXMLcloser);
  
        out.close();
        return 0;
    }    
    
    public static int convertToFCPXMLText(String filename, int dataRead) throws IOException{
                
        File outFile = new File(filename);
        outFile.createNewFile();
        FileWriter ofstream = new FileWriter(outFile);
        BufferedWriter out = new BufferedWriter(ofstream);
        
        String simpleName;
        simpleName = filename.substring(filename.lastIndexOf('/')+1,filename.length()-12);
       
        String FCPXMLheader =""+
"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
"<!DOCTYPE fcpxml>\n" +
"<fcpxml version=\"1.5\">\n" +
//"	<project name=\"Phantom Overlay\">\n" +
"		<resources>\n";
        
        String FCPXMLcloserStandAlone =""+ 
"                       <effect id=\"TextFormatFileId\" name=\"Basic Title\" uid=\".../Titles.localized/Bumper:Opener.localized/Basic Title.localized/Basic Title.moti\"/>\n" +
"			<format id=\"VideoFormatId\" name=\"FFVideoFormatRateUndefined\"/>\n"+
"		</resources>\n" +
"		<ref-clip ref=\"DateClip\" name=\"Phantom Overlay Date\" duration=\""
                +gStats.getDurationInt()+"s\"/>\n"+
"		<ref-clip ref=\"TimeClip\" name=\"Phantom Overlay Time\" duration=\""
                +gStats.getDurationInt()+"s\"/>\n"+
"		<ref-clip ref=\"GPSClip\" name=\"Phantom Overlay GPS\" duration=\""
                +gStats.getDurationInt()+"s\"/>\n"+
"		<ref-clip ref=\"AltClip\" name=\"Phantom Overlay Alt\" duration=\""
                +gStats.getDurationInt()+"s\"/>\n"+
"		<ref-clip ref=\"ExifClip\" name=\"Phantom Overlay Exif\" duration=\""
                +gStats.getDurationInt()+"s\"/>\n"+
"	</project>\n" +
"</fcpxml>";
        
        String FCPXMLcloserGlobal =""+ 
"                       <effect id=\"TextFormatFileId\" name=\"Basic Title\" uid=\".../Titles.localized/Bumper:Opener.localized/Basic Title.localized/Basic Title.moti\"/>\n" +
                "        <effect id=\"box\" name=\"Shapes\" uid=\".../Generators.localized/Elements.localized/Shapes.localized/Shapes.motn\"/>"+
"			<format id=\"VideoFormatId\" name=\"FFVideoFormatRateUndefined\"/>\n"+
"		</resources>\n" +
"                   <library>\n"+
"                       <event name=\"Phantom Overlay\">\n"+
"                           <ref-clip ref=\"TextSRT\" name=\"Flight Data\" duration=\""
                +gStats.getDurationInt()+"s\"/>\n" +
"                       </event>\n"+
"                   </library>\n"+
"</fcpxml>";
        
        out.write(FCPXMLheader);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        out.write("<!-- Phantom Overlay v"+gStats.getAppVersion()+ 
                " - Copyright © 2015-"+year+" Orbis Terrae - All rights reserved.-->\n");

        int i;
        float valeur;
    
        if (gStats.getFCPXMLstandalone() == true){
         out.write(
"<media id=\"DateClip\" name=\"Date ("+simpleName+")\">\n" +
"				<sequence format=\"VideoFormatId\">\n" +
"					<spine>\n");
        for (i = 1 ; i <= dataRead; i++){
            out.write("<title ref=\"TextFormatFileId\" name=\"Basic Title: ");
            valeur = datafromSRT[i].getAltFloat() / gStats.getMaxAltFloat();
            if (valeur<0)
                valeur=0;
            out.write(Float.toString(valeur));
            out.write("\" duration=\"");
            out.write("10/10s\">\n<text>");
            out.write(datafromSRT[i].getDateEUStr()+"\n");
            out.write("</text>\n</title>\n");
        }
        
        out.write("					</spine>\n" +
"				</sequence>\n" +
"			</media>\n");
        
        out.write("<media id=\"TimeClip\" name=\"Time ("+simpleName+")\">\n" +
"				<sequence format=\"VideoFormatId\">\n" +
"					<spine>\n");
         for (i = 1 ; i <= dataRead; i++){
            out.write("<title ref=\"TextFormatFileId\" name=\"Basic Title: ");
            valeur = datafromSRT[i].getAltFloat() / gStats.getMaxAltFloat();
            if (valeur<0)
                valeur=0;
            out.write(Float.toString(valeur));
            out.write("\" duration=\"");
            out.write("10/10s\">\n<text>");
            out.write(datafromSRT[i].getTimeStr()+"\n");
            out.write("</text>\n</title>\n");
        }
         
        out.write(
"					</spine>\n" +
"				</sequence>\n" +
"			</media>\n");      
        
        out.write("<media id=\"GPSClip\" name=\"GPS ("+simpleName+")\">\n" +
"				<sequence format=\"VideoFormatId\">\n" +
"					<spine>\n");
        
        for (i = 1 ; i <= dataRead; i++){
            out.write("<title ref=\"TextFormatFileId\" name=\"Basic Title: ");
            valeur = datafromSRT[i].getAltFloat() / gStats.getMaxAltFloat();
            if (valeur<0)
                valeur=0;
            out.write(Float.toString(valeur));
            out.write("\" duration=\"");
            out.write("10/10s\">\n<text>");
            out.write("Lat: "+datafromSRT[i].getLatShortStr() + " Lon: " +datafromSRT[i].getLonShortStr());
            out.write("</text>\n</title>\n");
             
        }
        out.write("					</spine>\n" +
"				</sequence>\n" +
"			</media>\n");
        
        out.write("<media id=\"AltClip\" name=\"Alt ("+simpleName+")\">\n" +
"				<sequence format=\"VideoFormatId\">\n" +
"					<spine>\n");
        
        for (i = 1 ; i <= dataRead; i++){
            out.write("<title ref=\"TextFormatFileId\" name=\"Basic Title: ");
            valeur = datafromSRT[i].getAltFloat() / gStats.getMaxAltFloat();
            if (valeur<0)
                valeur=0;
            out.write(Float.toString(valeur));
            out.write("\" duration=\"");
            out.write("10/10s\">\n<text>");
            out.write(" Alt: " +datafromSRT[i].getAltFloat()+"m\n");
            out.write("</text>\n</title>\n");
             
        }
        out.write(
"					</spine>\n" +
"				</sequence>\n" +
"			</media>\n");
        
        out.write(
"<media id=\"ExifClip\" name=\"Exif ("+simpleName+")\">\n" +
"				<sequence format=\"VideoFormatId\">\n" +
"					<spine>\n");            
        for (i = 1 ; i <= dataRead; i++){
            out.write("<title ref=\"TextFormatFileId\" name=\"Basic Title: ");
            out.write(exiffromSRT[i].getISOStr());
            out.write("\" duration=\"");
            out.write("10/10s\">\n<text>");
            out.write("ISO: "+exiffromSRT[i].getISO() + 
                    " Shutter: " +exiffromSRT[i].getShutter()+
                    " Fnum: " +exiffromSRT[i].getFnumStr()+
                    " EV: " + exiffromSRT[i].getEVStr()+"\n");
           out.write("</text>\n</title>\n");
            
        }
        out.write(
"					</spine>\n" +
"				</sequence>\n" +
"			</media>\n");       
        out.write(FCPXMLcloserStandAlone);
        }
        else{
        out.write(
"		   <media id=\"TextSRT\" name=\"Phantom Overlay ("+simpleName+")\">\n" +
"				<sequence duration=\""+gStats.getDurationInt()+"s\" format=\"VideoFormatId\" tcStart=\"0s\" tcFormat=\"NDF\" keywords=\"Orbis Terrae\">\n" +
"					<spine>\n");            
        for (i = 1 ; i <= dataRead; i++){
            out.write("<title name=\"Basic Title: ");
            out.write("("+datafromSRT[i].getTimeStr()+")");
            out.write("\" offset=\""+i+"s\" ref=\"TextFormatFileId\" duration=\"");
            out.write("1s\" role=\"titles.Overlay\">\n<text>\n");
            
            out.write("<text-style ref=\"line"+i+"-1\">");
            /*
            if(gStats.isHasTemp()){
                out.write(round(datafromSRT[i].getTempFloat(),1) +"° - ");
            }
            int distance = (int)datafromSRT[i].getOnGoingDistance();
            out.write(distance +"m - "+ datafromSRT[i].getDirectionStr()+" - "+ datafromSRT[i].getTimeStr() + " - " + datafromSRT[i].getDateEUStr() +"");
            out.write("</text-style>\n");
            
            out.write("<text-style ref=\"line"+i+"-2\">");
            out.write("\n");
            if(gStats.isHasPower()){
                int power = (int) round(datafromSRT[i].getPowerFloat(),0);
                out.write(power +"% - ");
            }
            out.write("ALT: " +datafromSRT[i].getAltFloat()+"m - GPS("+datafromSRT[i].getLatShortStr() + 
                    "," +datafromSRT[i].getLonShortStr()+
                    ")");
            out.write("</text-style>\n");
            // speed : double locSpeed = datafromSRT[i].getCurHSpeed();
            out.write("<text-style ref=\"line"+i+"-3\">");
            out.write("\nISO: "+exiffromSRT[i].getISO() + 
                    " SHUTTER: " +exiffromSRT[i].getShutter()+
                    " FNUM: " +exiffromSRT[i].getFnumStr()+
                    " EV: " + exiffromSRT[i].getEVStr()+"");
            out.write("</text-style>\n");
*/
            if(gStats.isHasPower()){
                int power = (int) round(datafromSRT[i].getPowerFloat(),0);
                out.write(power +"% - ");
            }
            if(gStats.isHasTemp()){
                out.write(round(datafromSRT[i].getTempFloat(),1) +"° - ");
            }
            out.write(datafromSRT[i].getTimeStr() + " - " + datafromSRT[i].getDateEUStr() +"");
            out.write("</text-style>\n");
            
            out.write("<text-style ref=\"line"+i+"-2\">");
            out.write("\n");
            int distance = (int)datafromSRT[i].getOnGoingDistance();
            double speed = round(datafromSRT[i].getCurHSpeed(),2);
            out.write(datafromSRT[i].getDirectionStr()+ " - " + speed +"km/h - "+distance +"m ALT: " +datafromSRT[i].getAltFloat()+"m");
            out.write("</text-style>\n"); 
            
            out.write("<text-style ref=\"line"+i+"-3\">");
            out.write("\n"+exiffromSRT[i].getISO() + " ISO 1/"+
                    exiffromSRT[i].getShutter()+ "s "+
                    exiffromSRT[i].getFnumStr()+
                    " EV: " + exiffromSRT[i].getEVStr()+"");

            
            out.write("</text-style>\n");
            
            
            
            
            
            
           out.write("</text>\n");
           out.write("<text-style-def id=\"line"+i+"-1\">\n" +
"<text-style font=\"Avenir Next\" fontSize=\"30\" alignment=\"right\"  bold=\"0\" lineSpacing=\"10\" italic=\"0\"/>\n" +
"</text-style-def>\n");
           out.write("<text-style-def id=\"line"+i+"-2\">\n" +
"<text-style font=\"Avenir Next\" fontSize=\"30\" alignment=\"right\"  bold=\"1\" lineSpacing=\"10\" italic=\"0\"/>\n" +
"</text-style-def>\n");
           out.write("<text-style-def id=\"line"+i+"-3\">\n" +
"<text-style font=\"Avenir Next\" fontSize=\"30\" alignment=\"right\"  bold=\"0\" lineSpacing=\"10\" italic=\"0\"/>\n" +
"</text-style-def>\n");
           
           out.write("   <adjust-transform position=\"0 -7.08749\" anchor=\"-83.3333 27.7778\"/>\n"); 
           if((datafromSRT[i].getMarkerStr().length() > 0)&&(i>1)){
               // out.write("   <marker start=\"0s\" duration=\"1s\" value=\""+datafromSRT[i].getMarkerStr()+"\"/>\n");
           }
           if(i==1){
               out.write("<video name=\"Shapes\" lane=\"-1\" offset=\"0s\" ref=\"box\" duration=\""+gStats.getDurationInt()+"s\" start=\"3600s\">\n" );
               out.write(
"                            <param name=\"Drop Shadow Opacity\" key=\"9999/988455508/1/208/211\" value=\"0\"/>\n" +
"                            <param name=\"Drop Shadow Distance\" key=\"9999/988455508/1/208/212\" value=\"0\"/>\n" +
"                            <param name=\"Drop Shadow Blur\" key=\"9999/988455508/1/208/213\" value=\"0\"/>\n" +
"                            <param name=\"Drop Shadow Angle\" key=\"9999/988455508/1/208/214\" value=\"0\"/>\n" +
"                            <param name=\"Outline Color\" key=\"9999/988455508/988455699/2/353/108/107\" value=\"1 1 1\"/>\n" +
"                            <param name=\"Fill Color\" key=\"9999/988455508/988455699/2/353/113/111\" value=\"0 0 0\"/>\n"+
"                            <param name=\"Shape\" key=\"9999/988461322/100/988461395/2/100\" value=\"4 (Rectangle)\"/>\n" +
"                            <param name=\"Fill\" key=\"9999/988461322/100/988464517/2/100\" value=\"1\"/>\n" +
"                            <param name=\"Roundness\" key=\"9999/988461322/100/988467054/2/100\" value=\"0.01\"/>\n" +
"                            <param name=\"Outline Width\" key=\"9999/988461322/100/988467855/2/100\" value=\"0\"/>\n" +
//"                            <adjust-transform position=\"41.6667 -33.75\" scale=\"0.832 0.96\" anchor=\"-16.5278 4.58333\"/>\n" +
"                            <adjust-transform position=\"41.6667 -33.75\" scale=\"0.75 0.96\" anchor=\"-22.2222 4.86111\"/>\n" +
"                            <adjust-blend amount=\"0.60\"/>\n" +
"                        </video>"
                       );
           }
           
           
            
           out.write("</title>\n");
            
        }
        out.write(
"					</spine>\n" +
"				</sequence>\n" +
"			</media>\n");
        out.write(FCPXMLcloserGlobal);
        }
        
        out.close();
        return 0;
    }
    
    public static int convertToGPX(String filename, int dataRead) throws IOException{
        
        /* offset algorithm: START */
        File outFile = new File(filename);
        outFile.createNewFile();
        FileWriter ofstream = new FileWriter(outFile);
        BufferedWriter out = new BufferedWriter(ofstream);
        
         String simpleName;
        simpleName = filename.substring(filename.lastIndexOf('/')+1);
        
        String GPXheader =
"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n" +
"<gpx xmlns=\"http://www.topografix.com/GPX/1/1\"\n" +
"    xmlns:gpxx=\"http://www.garmin.com/xmlschemas/GpxExtensions/v3\"\n" +
"    xmlns:gpxtrkx=\"http://www.garmin.com/xmlschemas/TrackStatsExtension/v1\"\n" +
"    xmlns:gpxtrkoffx=\"http://www.garmin.com/xmlschemas/TrackMovieOffsetExtension/v1\"\n" +
"    xmlns:wptx1=\"http://www.garmin.com/xmlschemas/WaypointExtension/v1\"\n" +
"    xmlns:gpxtpx=\"http://www.garmin.com/xmlschemas/TrackPointExtension/v1\"\n" +
"    xmlns:gpxpx=\"http://www.garmin.com/xmlschemas/PowerExtension/v1\"\n" +
"    xmlns:gpxacc=\"http://www.garmin.com/xmlschemas/AccelerationExtension/v1\"\n" +
"    creator=\"VIRB Elite\"\n" +
"    version=\"1.1\"\n" +
"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
"    <metadata>\n" +
"    <link href=\"https://sites.google.com/site/oterrae/\">\n" +
"      <text>Orbis Terrae</text>\n" +
"    </link>\n";

        String GPXcloser ="  </trk>\n" +
"\n" +
"</gpx>";
        
        out.write(GPXheader);
        out.write("    <time>" +gStats.getDateStr()+"T"+gStats.getStartTimeStr()+"Z</time>\n");
        out.write("    </metadata> \n");
        
        out.write("<trk>\n<name>"+simpleName+"</name>\n" +
"                  <extensions>\n" +
"                    <gpxx:TrackExtension>\n" +
"                        <gpxx:DisplayColor>Cyan</gpxx:DisplayColor>\n" +
"                    </gpxx:TrackExtension>\n" +
"                    <gpxtrkoffx:TrackMovieOffsetExtension>\n" +
"                        <gpxtrkoffx:StartOffsetSecs>0</gpxtrkoffx:StartOffsetSecs>\n" +
"                    </gpxtrkoffx:TrackMovieOffsetExtension>\n"+
"                  </extensions>\n");
        
        
        out.write("<trkseg>\n");
        int i=0;
        
        for (i = 1 ; i <= dataRead; i++){
            out.write("  <trkpt lat=\"" + datafromSRT[i].getLatZeroStr() +"\" lon=\""+ datafromSRT[i].getLonZeroStr()+"\">\n");
            out.write("      <ele>" + datafromSRT[i].getAltStr()+ "</ele>\n");
            out.write("      <time>" +datafromSRT[i].getDateStr()+"T"+datafromSRT[i].getTimeStr()+"Z</time>\n");
            if(datafromSRT[i].isHasTemp() || datafromSRT[i].isHasHR()){
                out.write("      <extensions>\n");
                out.write("       <gpxtpx:TrackPointExtension>\n");
                if(datafromSRT[i].isHasTemp()){
                    out.write("         <gpxtpx:atemp>"+ datafromSRT[i].getTempFloat() +"</gpxtpx:atemp>\n");
                }
                if(datafromSRT[i].isHasHR()){
                    out.write("         <gpxtpx:hr>"+ Integer.toString(datafromSRT[i].getHRInt()) +"</gpxtpx:hr>\n");
                }
                out.write("       </gpxtpx:TrackPointExtension>\n");
                out.write("      </extensions>\n");
             }
            out.write("  </trkpt>\n");
        }
        
        out.write("</trkseg>\n");    
        out.write(GPXcloser);
  
    out.close();
     return 0;
    }
    
    public static int loadFile(String filename) throws IOException{
    FileInputStream fstream = new FileInputStream(filename);
    DataInputStream in = new DataInputStream(fstream);
    BufferedReader br = new BufferedReader(new InputStreamReader(in));
    String strLine;

    String indexSRT="";
    String offsetStart="";
    String offsetEnd="";      
    String homeGPS1SRT="";      
    String homeGPS2SRT="";      
    String dateSRT="";      
    String timeSRT="";      
    String droneGPS1SRT="";  
    String droneGPS2SRT="";      
    String AltitudeSRT="";      
    String IsoSRT="";   
    String ShutterSRT="";   
    String EVSRT="";   
    String FnumSRT="";
    int nbdataread = 0;
        
    while ((strLine = br.readLine()) != null) {
        if (strLine.compareTo("\n") != 0) {
            indexSRT = strLine;
            //System.out.println("str1 SRT index : " + strLine + "\n");
        }

        if((strLine = br.readLine()) != null){
            String[] atoms = strLine.split(" --> ");               
            if (atoms.length == 2){
                offsetStart= atoms[0];
                offsetEnd= atoms[1];
                //System.out.println("str2 SRT " + offsetStart + " xx " + offsetEnd + "\n");
            }
        }
        
        if((strLine = br.readLine()) != null){
            String[] atoms = strLine.split(" "); 
            if (atoms.length == 3){

                String[] GPSSTR = atoms[0].split(",");
                homeGPS1SRT = GPSSTR[0].substring(5);
                homeGPS2SRT = GPSSTR[1].replace(')',' ');

                dateSRT= atoms[1];
                timeSRT= atoms[2];
                //System.out.println("str3 SRT " + homeGPS1SRT + " + " + homeGPS2SRT + "\n");
            }
         }
        if((strLine = br.readLine()) != null){
            String[] atoms = strLine.split(" "); 
            if (atoms.length == 2){//GPS(6.3072,45.6849)
                String[] GPSSTR = atoms[0].split(",");
                droneGPS2SRT = GPSSTR[0].substring(4);
                droneGPS1SRT = GPSSTR[1].replace(')',' ');
                String[] ALTSTR = atoms[1].split(":");             
                AltitudeSRT = ALTSTR[1];
                //System.out.println("str4 SRT lat " + droneGPS1SRT + " - lon " + droneGPS2SRT);
                //System.out.println("str4 SRT alt:" + AltitudeSRT + "\n");
            }
         }
        if((strLine = br.readLine()) != null){
            String[] atoms = strLine.split(" "); 
            if (atoms.length == 5){
                IsoSRT = atoms[0];
                ShutterSRT = atoms[1];
                EVSRT = atoms[2] + atoms[3];
                FnumSRT = atoms[4];

               // System.out.println("str5 SRT " + IsoSRT + "\n");
            }
            else{
                //System.out.println("lenght: "+ atoms.length +"\n");
                //for(int i=0 ; i < atoms.length ; i++){
                  //  System.out.println("Exif atom ["+i+"]: "+atoms[i]+"\n");
                //}
                if (atoms.length == 4){
                    IsoSRT = atoms[0];
                    ShutterSRT = atoms[1];
                    EVSRT = atoms[2];
                    FnumSRT = atoms[3];
                }
            }
         }
        if((strLine = br.readLine()) != null){
            // System.out.println("str6 SRT empty" + "\n");
        }
        int i = Integer.parseInt(indexSRT);

//        System.out.println("data <" +i+ "> read\n");
        datafromSRT[i] = new gpsElement(homeGPS1SRT, homeGPS2SRT, dateSRT, timeSRT, droneGPS1SRT, droneGPS2SRT, AltitudeSRT);
        exiffromSRT[i] = new exifElement(IsoSRT, ShutterSRT, EVSRT,FnumSRT);

        nbdataread = i;
    }
    in.close();
    return nbdataread;
} 
    
    
    public static int fixStatHomeData(){
    // fix when there are missing gps data
    int i;
    float lastValidLat =(float) -123456789.0;
    float lastValidLon=(float) -123456789.0;
    Boolean errorWithDataNotInitialized = false ;
    
    for (i=1; i<= gStats.getIndex(); i++){
        // fix end of the file
        if(datafromSRT[i].isMissHomeData() == false ){
           lastValidLat = datafromSRT[i].getHomeGPS1Float();
           lastValidLon = datafromSRT[i].getHomeGPS2Float();
        }
        else{
            if(lastValidLat == (float) -123456789.0 ){          
                errorWithDataNotInitialized = true;
            }
            else{
                datafromSRT[i].setMissHomeData(false);

                //System.out.println("not valid data at index : "+i +"(correcting first pass) -" + lastValidLatStr +";"+lastValidLonStr);
                datafromSRT[i].setHomeGPS1Float(lastValidLat);
                datafromSRT[i].setHomeGPS2Float(lastValidLon);
            }
        }
    }
    
    if(errorWithDataNotInitialized == true){
        for (i=gStats.getIndex() ; i >=1 ; i--){
        // fix begining of the file
            if(datafromSRT[i].isMissData() == false ){
                lastValidLat = datafromSRT[i].getHomeGPS1Float();
                lastValidLon = datafromSRT[i].getHomeGPS2Float();
            }
            else{
                if(lastValidLat  == (float) -123456789.0 ){
                    errorWithDataNotInitialized = true;
                }
                else{
                //System.out.println("not valid data at index : "+i +"(correcting second pass) -" + lastValidLatStr +";"+lastValidLonStr );
                datafromSRT[i].setMissData(false);

                datafromSRT[i].setHomeGPS1Float(lastValidLat);
                datafromSRT[i].setHomeGPS2Float(lastValidLon);
                }
            }
        }
    }
    return 0;
    }
    
    public static int fixStatData(){
    // fix when there are missing gps data
    int i;
    String lastValidLatStr ="N/A";
    String lastValidLonStr="N/A";
    Boolean errorWithDataNotInitialized = false ;
    
    for (i=1; i<= gStats.getIndex(); i++){
        // fix end of the file
        if(datafromSRT[i].isMissData() == false ){
            lastValidLatStr = datafromSRT[i].getLatShortStr();
            lastValidLonStr = datafromSRT[i].getLonShortStr();
        }
        else{
            if(lastValidLatStr.contains("N/A") ){
                errorWithDataNotInitialized = true;
            }
            else{
                datafromSRT[i].setMissData(false);

                //System.out.println("not valid data at index : "+i +"(correcting first pass) -" + lastValidLatStr +";"+lastValidLonStr);
                datafromSRT[i].setLatShortStr(lastValidLatStr);
                datafromSRT[i].setDroneGPS1Float(Float.parseFloat(lastValidLatStr));
                datafromSRT[i].setLatZeroStr(lastValidLatStr + "00000000000"); 

                datafromSRT[i].setLonShortStr(lastValidLonStr);
                datafromSRT[i].setDroneGPS2Float(Float.parseFloat(lastValidLonStr));
                datafromSRT[i].setLonZeroStr(lastValidLonStr + "00000000000"); 
            }
        }
    }
    
    if(errorWithDataNotInitialized == true){
        for (i=gStats.getIndex() ; i >=1 ; i--){
        // fix begining of the file
            if(datafromSRT[i].isMissData() == false ){
                lastValidLatStr = datafromSRT[i].getLatShortStr();
                lastValidLonStr = datafromSRT[i].getLonShortStr();
            }
            else{
                if(lastValidLatStr.contains("N/A") ){
                    errorWithDataNotInitialized = true;
                }
                else{
                //System.out.println("not valid data at index : "+i +"(correcting second pass) -" + lastValidLatStr +";"+lastValidLonStr );
                datafromSRT[i].setMissData(false);

                datafromSRT[i].setLatShortStr(lastValidLatStr);
                datafromSRT[i].setDroneGPS1Float(Float.parseFloat(lastValidLatStr));
                datafromSRT[i].setLatZeroStr(lastValidLatStr + "00000000000"); 

                datafromSRT[i].setLonShortStr(lastValidLonStr);
                datafromSRT[i].setDroneGPS2Float(Float.parseFloat(lastValidLonStr));
                datafromSRT[i].setLonZeroStr(lastValidLonStr + "00000000000"); 
                }
            }
        }
    }
    return 0;
}

    public static int setMarker(){
    float maxAlti = 0;
    float maxSpeed = 0;
    int i,j,k = 1;
    boolean[] tagDistance;
    boolean[] tagAlti;
     j=1;
     
    tagDistance = new boolean [80];
    tagAlti = new boolean [80];
    for (i=0 ; i<=79 ;i++) {
        tagDistance[i]=false;
        tagAlti[i]=false;
    }
    
    for(i=1; i<= gStats.getIndex(); i++){
        if(datafromSRT[i].getAltFloat() > maxAlti){
            // set max altitiude
            maxAlti = datafromSRT[i].getAltFloat();
            datafromSRT[i].setMarkerStr("New Max Alt: "+maxAlti+"m");     
            //System.out.println("##### new max alti= " + maxAlti+ "m @"+i);
        }
        else{
        }
        
        if(datafromSRT[i].getCurHSpeed() > maxSpeed){
            // set max speed
            //System.out.println("##### getCurHSpeed= " + datafromSRT[i].getCurHSpeed() + "m/s");
            maxSpeed = (float) datafromSRT[i].getCurHSpeed();
            datafromSRT[i].setMarkerStr("New Max Speed: "+maxSpeed+"km/h");     
            
            //System.out.println("##### new max speed= " + maxSpeed+ "km/h @"+i);
        }
        else{
        } 
        
        
        if(datafromSRT[i].getAltFloat() >= k*10){
            if(tagAlti[k] == false){
                datafromSRT[i].setMarkerStr("Reached h: "+k*10+"m"); 
                tagAlti[k] = true;
                k++;
            }    
        }
        else{
        }
       
        if(datafromSRT[i].getOnGoingDistance() >= j*250){
            if(tagDistance[j] == false){
                datafromSRT[i].setMarkerStr("Reached d: "+j*250+"m"); 
                tagDistance[j] = true;
                j++;
            }    
        }
        else{
        }
    }
    
    //System.out.println("##### max alti globale= " + maxAlti+ "m ");
    //System.out.println("##### max speed globale= " + maxSpeed+ "km/h ");
    gStats.setMaxAltFloat(maxAlti);  
    gStats.setMaxSpeedFloat((float) round(maxSpeed,2));  
    return 0;
    }
    
    public static int setStatData(){
    // parsing the table of STR elements to extract usefull info
    float maxLat = (float) 0.0;
    float maxLon = (float) 0.0;
    float minLat = (float) 180.0;
    float minLon = (float) 180.0;
    int i;
    Boolean fixNeeded = false;
    Boolean fixHomeNeeded = false;
    String startT;
    String endT;
    String dateL;
    Random rand = new Random();
    
//    System.out.println("setStartData temp="+gStats.isHasTemp());
    
    for(i=1; i<= gStats.getIndex(); i++){
        if(datafromSRT[i].isMissData()){
            fixNeeded= true;
        }
        if(datafromSRT[i].isMissHomeData()){
            fixHomeNeeded= true;
        }

        if(gStats.isHasTemp()==true){
            // generate temp data that doesn't exist yet
            datafromSRT[i].setHasTemp(true);
            datafromSRT[i].setTempFloat((float) round((float) (gStats.getTempInt() + rand.nextFloat()/10 - datafromSRT[i].getAltFloat()/100),2)); 
            //System.out.println("temp at " + i + " = "+ datafromSRT[i].getTempFloat()); 
        }
        else{
            datafromSRT[i].setHasTemp(false);
        }

        if(gStats.isHasHR()){
            // generate heart rate data that doesn't exist yet
            datafromSRT[i].setHasHR(true);
            datafromSRT[i].setHRInt(gStats.getHRInt() + rand.nextInt(20));
            //System.out.println("HR at " + i + " = "+ datafromSRT[i].getHRInt()); 
        }
        else{
            datafromSRT[i].setHasHR(false);
        }
            
        if(gStats.isHasPower()){
            // generate battery data that doesn't exist yet
            datafromSRT[i].setHasPower(true);
            int power =0;
            power = gStats.getPowerMaxInt() - gStats.getPowerMinInt();
            float powerInst =0;
            powerInst = ((float) power) / ((float) gStats.getDurationInt());
            //System.out.println("power at " + i + " = "+ round(powerInst,5) +" (" + gStats.getPowerMaxInt() +"% ;"+gStats.getPowerMinInt()+"%) duration = " +gStats.getDurationInt()); 
            datafromSRT[i].setPowerFloat(gStats.getPowerMaxInt() - powerInst*i); 
            //System.out.println("power at " + i + " = "+ gStats.getPowerMaxInt() +" - "+ powerInst +" x "+i); 
        }
        else{
            datafromSRT[i].setHasPower(false);
        }
            
        
    }
        
    if(fixNeeded){
        fixStatData();
        System.out.println("GPS data fixed");
    }
    if(fixHomeNeeded){
        fixStatHomeData();
        System.out.println("GPS Home data fixed");
    }
     
    float lat = (float) 0.0;
    String latStr;
    String latZeroStr;
    float lon = (float) 0.0;
    String direction;
    double distancetoNextPoint;
    double totalDistance =0;
    
    for(i=1; i<= gStats.getIndex(); i++){
        // get max/min Lat /lon
        lat = datafromSRT[i].getDroneGPS1Float();
        lon = datafromSRT[i].getDroneGPS2Float();

        if(lat > maxLat){
            maxLat = lat;
        }
        else{
        }
        if(datafromSRT[i].getDroneGPS2Float() > maxLon){
            maxLon = datafromSRT[i].getDroneGPS2Float();
            //System.out.println("new max lon " + maxLon + " @"+i);
        }
        else{
          }
        if(lat < minLat){
            minLat = lat;
        }
        else{
        }
        if(datafromSRT[i].getDroneGPS2Float() < minLon){
            minLon = datafromSRT[i].getDroneGPS2Float();
        }
        else{
        }
      }
    
    for(i=1; i<= gStats.getIndex(); i++){
        //calculate bearing and distance between 2 following points.
        if(i<gStats.getIndex()){  
            lat = datafromSRT[i+1].getDroneGPS1Float();
            lon = datafromSRT[i+1].getDroneGPS2Float();
        }
        else{
            lat = datafromSRT[i].getDroneGPS1Float();
            lon = datafromSRT[i].getDroneGPS2Float();
        }
        datafromSRT[i].setBearing (lat,lon);
        datafromSRT[i].setDistance (lat,lon);
        direction =datafromSRT[i].getDirectionStr();
        distancetoNextPoint = datafromSRT[i].getDistance();
        totalDistance += distancetoNextPoint;
        datafromSRT[i].setOnGoingDistance(round(totalDistance,2));

        if (datafromSRT[i].getDistance() == 0){
            if(datafromSRT[i].getBearing() == 0){
                datafromSRT[i].setBearing(gStats.getPreviousBearing());
            }
            else{
                gStats.setPreviousBearing(datafromSRT[i].getBearing()); 
            }
        }
        else{
            gStats.setPreviousBearing(datafromSRT[i].getBearing());
        } 
        direction =datafromSRT[i].getDirectionStr();
        
        gStats.setPrevHSpeed(distancetoNextPoint);
        datafromSRT[i].setPrevHSpeed(gStats.getPrevHSpeed());
       
    }
    gStats.setDistance(round(totalDistance,2));
      
    setMarker();
    
    // parse Date & Time
    startT = datafromSRT[1].getTimeStr();
    endT = datafromSRT[i-1].getTimeStr();
    dateL = datafromSRT[1].getDateStr();
    int startTs = 0;
    int endTs = 0;
      
    String[] tokens = startT.split(":");
    int hours = Integer.parseInt(tokens[0]);
    int minutes = Integer.parseInt(tokens[1]);
    int seconds = Integer.parseInt(tokens[2]);
    startTs = 3600 * hours + 60 * minutes + seconds;

    tokens = endT.split(":");
    hours = Integer.parseInt(tokens[0]);
    minutes = Integer.parseInt(tokens[1]);
    seconds = Integer.parseInt(tokens[2]);
    endTs = 3600 * hours + 60 * minutes + seconds;

    gStats.setDurationInt(1+endTs-startTs);
    //statfromSRT.setDurationInt(endTs);

    gStats.setDateStr(dateL);
    gStats.setStartTimeStr(startT); 
    gStats.setEndTimeStr(endT); 

    gStats.setMaxlat(maxLat);  
    gStats.setMaxlon(maxLon);  
    gStats.setMinlat(minLat);  
    gStats.setMinlon(minLon);  

    gStats.setDateEUStr(datafromSRT[1].getDateEUStr());
    
    /*
     System.out.println("<" + gStats.getIndex() + "> date: "+
             gStats.getDateStr() + " time ("+
             gStats.getStartTimeStr()+" ; "+
             gStats.getEndTimeStr()+") - Max Alt = " + maxAlti);
     System.out.println("Min (" + gStats.getMinlat() + " ; "+
             gStats.getMinlon() + " ) - Max ("+
             gStats.getMaxlat()+" ; "+
             gStats.getMaxlon()+")\n");
     System.out.println("Total duration: " + gStats.getDurationInt() + "s\n");
    */
    
    return 0; 
}

 
    public static void main(String[] args) throws IOException {
    }
          
  
}

