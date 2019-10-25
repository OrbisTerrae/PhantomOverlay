/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phantom.overlay;

import com.apple.eawt.AboutHandler;
import com.apple.eawt.PreferencesHandler;
import com.apple.eawt.AppEvent.AboutEvent;
import com.apple.eawt.AppEvent.PreferencesEvent;
import com.apple.eawt.Application;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

/**
 *
 * @author gbourelly
 */
public class PhantomOverlay extends javax.swing.JFrame {
    
    static private SRT myPhantomOverlay ;
    static private String sourceFile= "";
    static private int index=0;
    static int year;
    
    /**
     * Creates new form MainUI
     */
    public PhantomOverlay() {
        initComponents();
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("DJI SubRip file", "SRT", "srt", "txt" , "TXT");
        
        year = Calendar.getInstance().get(Calendar.YEAR);
        jFileChooser1.setFileFilter(filter);
        
        jLabel1.setText("");
        jLabel2.setText("");
        jLabel3.setText("");
        jLabel4.setText("");
        jLabel7.setText("");
        jLabel8.setText("");
        
        jSlider2.setValue(80);
        jSlider1.setValue(10);
       
    
        Application macApplication = Application.getApplication();
        macApplication.setEnabledPreferencesMenu(true);
        macApplication.setAboutHandler((AboutEvent ae) -> {
            jLabel14.setText("version "+SRT.getgStats().getAppVersion());
            jLabel15.setText("Copyright © 2015-"+year+" Orbis Terrae");
            jDialog2.setVisible(true);
            });
    
    
        macApplication.setPreferencesHandler((PreferencesEvent pe) -> {
            jDialog1.setVisible(true);
            });
    
       
    
     String filename;
        new FileDrop( System.out, jTextField1, new FileDrop.Listener()
        {@Override
   public void filesDropped( java.io.File[] files )
            {   for( int i = 0; i < files.length; i++ )
                {   
                    String srcFile;
                    try{
                        String name = files[i].getCanonicalPath();
                    if(name.toLowerCase().endsWith(".srt") ){
                        jTextField1.setText(name); 
                        srcFile = jTextField1.getText();
                        sourceFile = srcFile;
                    }
                    else {
                        jLabel3.setForeground(Color.red);
                        String simpleName = name.substring(name.lastIndexOf('/')+1);
                        jLabel1.setText("unable to read:");
                        jLabel2.setText("");
                        jLabel3.setText(simpleName);
                        return;
                    }
                    
                    
                    //System.out.println("file srcFile " + srcFile + " - sourceFile " + sourceFile);
                    
                    int dataRead = 0;
                    try {
                        dataRead = SRT.loadFile(sourceFile);
                    } catch (IOException ex) {
                    Logger.getLogger(PhantomOverlay.class.getName()).log(Level.SEVERE, null, ex);
                    String simpleName = srcFile.substring(srcFile.lastIndexOf('/')+1);
                    jLabel3.setForeground(Color.red);
                    jLabel1.setText("unable to read:");
                    jLabel2.setText("");
                    jLabel3.setText(simpleName);
                    return;
                    }
                    index = dataRead;
        if(SRT.getgStats().isIsConstructed()== false){
            throw new UnsupportedOperationException("Not supported yet.");
            //SRT.setgStats(new globalStats(dataRead));
        }
        SRT.getgStats().setFilename(sourceFile);
        SRT.getgStats().setIndex(dataRead);
        SRT.setStatData();
        SRT.getgStats().setFCPXMLstandalone(false);
        
        jLabel3.setForeground(Color.black);
        jLabel1.setText("On "+SRT.getgStats().getDateStr() 
                + " at "+SRT.getgStats().getStartTimeStr()); //+" to " + myPhantomOverlay.statfromSRT.getEndTimeStr()
        jLabel2.setText("Duration: "+SRT.getgStats().getDurationInt()+"s - "
                + SRT.getgStats().getIndex() +
                " points read.");
        jLabel3.setText("Alt Max: " + 
                SRT.getgStats().getMaxAltFloat() + "m - Distance: "+SRT.getgStats().getDistance() +"m");
        
                    }   // end try
                    catch( java.io.IOException e ) {}
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener

        
        Image imgIcon;
//        imgIcon = new ImageIcon(getClass().getResource("/phantom/overlay/map icon rounded 48.png")).getImage();
          imgIcon = new ImageIcon(getClass().getResource("/phantom/overlay/Phantom Overlay.png")).getImage();
        
        this.setIconImage(imgIcon);
        macApplication.setDockIconImage(imgIcon);
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jFileChooser1 = new javax.swing.JFileChooser();
        jDialog1 = new javax.swing.JDialog();
        jLabel9 = new javax.swing.JLabel();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jSlider2 = new javax.swing.JSlider();
        jLabel10 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jCheckBox6 = new javax.swing.JCheckBox();
        jSlider1 = new javax.swing.JSlider();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialog2 = new javax.swing.JDialog();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jFileChooser1.setAcceptAllFileFilterUsed(false);

        jDialog1.setTitle("Preferences");
        jDialog1.setLocation(new java.awt.Point(100, 100));
        jDialog1.setMinimumSize(new java.awt.Dimension(425, 320));
        jDialog1.setModal(true);
        jDialog1.setResizable(false);
        jDialog1.setSize(new java.awt.Dimension(425, 280));

        jLabel9.setText("GPX Options");

        jCheckBox4.setText("Temperature");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jCheckBox5.setText("Heart Rate");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jSlider2.setMajorTickSpacing(10);
        jSlider2.setMaximum(200);
        jSlider2.setMinimum(40);
        jSlider2.setPaintTicks(true);
        jSlider2.setMinimumSize(new java.awt.Dimension(36, 30));
        jSlider2.setPreferredSize(new java.awt.Dimension(190, 30));
        jSlider2.setRequestFocusEnabled(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jSpinner2, org.jdesktop.beansbinding.ELProperty.create("${value}"), jSlider2, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        jLabel10.setText("Global Options");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Metric");
        jRadioButton1.setToolTipText("");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("US");
        jRadioButton2.setEnabled(false);

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Knots");
        jRadioButton3.setEnabled(false);

        jButton3.setText("OK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Cancel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel11.setText("Units: ");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jSlider2, org.jdesktop.beansbinding.ELProperty.create("${value}"), jSpinner2, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        jCheckBox6.setText("Battery");

        jSlider1.setMajorTickSpacing(10);
        jSlider1.setMaximum(40);
        jSlider1.setMinimum(-15);
        jSlider1.setMinorTickSpacing(2);
        jSlider1.setPaintTicks(true);
        jSlider1.setPreferredSize(new java.awt.Dimension(190, 30));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jSpinner1, org.jdesktop.beansbinding.ELProperty.create("${value}"), jSlider1, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jSlider1, org.jdesktop.beansbinding.ELProperty.create("${value}"), jSpinner1, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        jLabel18.setText("Initial level:");

        jLabel19.setText("End:");

        jTextField2.setText("100");

        jTextField3.setText("20");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel20.setText("%");

        jLabel21.setText("%");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDialog1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel11))
                                    .addComponent(jCheckBox6)
                                    .addComponent(jCheckBox4)
                                    .addComponent(jCheckBox5))
                                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jDialog1Layout.createSequentialGroup()
                                            .addGap(22, 22, 22)
                                            .addComponent(jRadioButton1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jRadioButton2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jRadioButton3)
                                            .addGap(7, 7, 7))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jDialog1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jDialog1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel18)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel20)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel19)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel21))
                                            .addGroup(jDialog1Layout.createSequentialGroup()
                                                .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(153, 153, 153)
                .addComponent(jButton3)
                .addGap(39, 39, 39))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel9)
                .addGap(15, 15, 15)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox4)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jCheckBox5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jLabel10))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addGap(11, 11, 11))
        );

        jDialog2.setTitle("About");
        jDialog2.setResizable(false);
        jDialog2.setSize(new java.awt.Dimension(320, 200));
        jDialog2.setType(java.awt.Window.Type.UTILITY);

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel12.setText("Phantom Overlay");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phantom/overlay/map icon rounded 48.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        jLabel14.setText("version 1.0");

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        jLabel15.setText("Copyright © 2015-2016 Orbis Terrae");

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        jLabel16.setText("All rights reserved.");

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phantom Overlay");
        setBackground(new java.awt.Color(207, 175, 137));
        setIconImage(Toolkit.getDefaultToolkit().getImage(PhantomOverlay.class.getResource("/phantom/overlay/Phantom Overlay.png")));
        setResizable(false);

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("GPX");
        jCheckBox1.setToolTipText("Garmin Virb");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("KML");
        jCheckBox2.setToolTipText("Google Earth");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setText("FCPXML");
        jCheckBox3.setToolTipText("Final Cut Pro");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jButton1.setText("Export");
        jButton1.setToolTipText("into selected file formats");
        jButton1.setNextFocusableComponent(jTextField1);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Generate:");
        jLabel6.setToolTipText("");

        jTextField1.setToolTipText("");
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField1.setDropMode(javax.swing.DropMode.INSERT);
        jTextField1.setNextFocusableComponent(jButton1);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel4.setText("a");
        jLabel4.setToolTipText("");
        jLabel4.setSize(new java.awt.Dimension(7, 16));

        jLabel7.setForeground(new java.awt.Color(0, 204, 102));
        jLabel7.setText("a");

        jLabel8.setText("a");

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Blablabla");

        jLabel2.setText("Blabla 2Blabla 2Blabla 2Blabla 2Blabla");
        jLabel2.setToolTipText("");

        jLabel3.setText("blabla3");
        jLabel3.setAutoscrolls(true);

        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phantom/overlay/map icon rounded 10 128.png"))); // NOI18N

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.META_MASK));
        jMenuItem2.setText("Open");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                    .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLayeredPane1)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox2)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox3)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLayeredPane1.getAccessibleContext().setAccessibleDescription("");

        getAccessibleContext().setAccessibleDescription("Phantom Overlay");

        bindingGroup.bind();

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         if(index>0){
             SRT.setStatData();
             if(this.jCheckBox1.isSelected()){
                try {
                    SRT.convertToGPX(sourceFile+".GPX",index);
                    System.out.println("DONE! Check the result in the file: " + sourceFile+".GPX");
                    jLabel4.setForeground(Color.green);
                    jLabel4.setText("✔");
                 } catch (IOException ex) {
                Logger.getLogger(PhantomOverlay.class.getName()).log(Level.SEVERE, null, ex);
                jLabel4.setForeground(Color.red);
                jLabel4.setText("✗");
                }
             }
            if(jCheckBox2.isSelected()){
                try {
                    SRT.convertToKML(sourceFile+".kml",index); 
                    jLabel7.setForeground(Color.green);
                    jLabel7.setText("✔");
                 } catch (IOException ex) {
                Logger.getLogger(PhantomOverlay.class.getName()).log(Level.SEVERE, null, ex);  
                    jLabel7.setForeground(Color.red);
                    jLabel7.setText("✗");
                }
                
                System.out.println("DONE! Check the result in the file: " + sourceFile+".KML");
            
            }
            if(jCheckBox3.isSelected()){
                try {
//                    SRT.convertToFCPXMLMotion(sourceFile+".Motion.fcpxml",index); 
                    SRT.convertToFCPXMLText(sourceFile+".text.fcpxml",index); 
                    jLabel8.setForeground(Color.green);
                    jLabel8.setText("✔");
                 } catch (IOException ex) {
                Logger.getLogger(PhantomOverlay.class.getName()).log(Level.SEVERE, null, ex);
                jLabel8.setForeground(Color.red);
                jLabel8.setText("✗");
                }
                
                System.out.println("DONE! Check the result in the file: " + sourceFile+".text.FCPXML");
            
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        String srcFile = jTextField1.getText();
        sourceFile = srcFile;
        System.out.println("jTextFieldActionPerformed: srcFile =" + srcFile);
               
        int dataRead = 0;
        try {
             dataRead = SRT.loadFile(sourceFile);
        } catch (IOException ex) {
            Logger.getLogger(PhantomOverlay.class.getName()).log(Level.SEVERE, null, ex);
            String simpleName = srcFile.substring(srcFile.lastIndexOf('/')+1);
            jLabel3.setForeground(Color.red);
            jLabel1.setText("unable to find :");
            jLabel2.setText("");
            jLabel3.setText(simpleName);
            return;
        }
        index = dataRead;
        
        if(SRT.getgStats().isIsConstructed()== false){
            throw new UnsupportedOperationException("Not supported yet.");
            //SRT.setgStats(new globalStats(dataRead));
        }
        SRT.getgStats().setFilename(sourceFile);
        SRT.getgStats().setIndex(dataRead);
        SRT.setStatData();
        
        jLabel3.setForeground(Color.black);
        jLabel1.setText("On "+SRT.getgStats().getDateStr() 
                + " at "+SRT.getgStats().getStartTimeStr()); //+" to " + myPhantomOverlay.statfromSRT.getEndTimeStr()
        jLabel2.setText("Duration: "+SRT.getgStats().getDurationInt()+"s - "
                + SRT.getgStats().getIndex() +
                " points read.");
        jLabel3.setText("Alt Max: " + 
                SRT.getgStats().getMaxAltFloat() + "m - Distance: "+SRT.round(SRT.getgStats().getDistance(),2) +"m");
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
         String srcFile = "";
         
        jLabel1.setText("");
        jLabel2.setText("");
        jLabel3.setText("");
        jLabel4.setText("");
        jLabel6.setText("");
        jLabel7.setText("");
        jLabel3.setForeground(Color.black);
         int returnVal = jFileChooser1.showOpenDialog(jFileChooser1);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                try {
                    sourceFile = jFileChooser1.getSelectedFile().getCanonicalPath();
                    srcFile = jFileChooser1.getSelectedFile().getName();
                    jTextField1.setText(sourceFile);
                } catch (IOException ex) {
                    jLabel3.setForeground(Color.red);
                    jLabel1.setText("Unable to find :");
                    jLabel2.setText("");
                    jLabel3.setText(sourceFile);
                    Logger.getLogger(PhantomOverlay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                return;
            }
            
        int dataRead = 0;
        try {
            dataRead = SRT.loadFile(sourceFile);
        } catch (IOException ex) {
            Logger.getLogger(PhantomOverlay.class.getName()).log(Level.SEVERE, null, ex);
        }
        index = dataRead;
        
        if(SRT.getgStats().isIsConstructed()== false){
            throw new UnsupportedOperationException("Not supported yet.");
            //SRT.setgStats(new globalStats(dataRead));
        }
        SRT.getgStats().setFilename(sourceFile);
        SRT.getgStats().setIndex(dataRead);
        SRT.setStatData();
        
        jLabel1.setText("On "+SRT.getgStats().getDateStr() 
                + " at "+SRT.getgStats().getStartTimeStr()); //+" to " + myPhantomOverlay.statfromSRT.getEndTimeStr()
        jLabel2.setText("Duration: "+SRT.getgStats().getDurationInt()+"s - "
                + SRT.getgStats().getIndex() +
                " points read.");
        jLabel3.setText("Alt Max: " + 
                SRT.getgStats().getMaxAltFloat() + "m - Distance: "+SRT.getgStats().getDistance() +"m");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jDialog1.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox4.isSelected()){
            SRT.getgStats().setHasTemp(true);
            SRT.getgStats().setTempInt(jSlider1.getValue());
            System.out.println("Temperature "+ SRT.getgStats().isHasTemp()+" at "+SRT.getgStats().getTempInt()+"°");
        }
        else{
            SRT.getgStats().setHasTemp(false);
        }
        if(jCheckBox5.isSelected()){
            SRT.getgStats().setHasHR(true);
            SRT.getgStats().setHRInt(jSlider2.getValue());
        }
        else{
            SRT.getgStats().setHasHR(false);
        }
        if(jCheckBox6.isSelected()){
            SRT.getgStats().setHasPower(true);
            int max = 0;
            int min = 0;
            String maxStr = jTextField2.getText();
            String minStr = jTextField3.getText();
            try{
                max= Integer.parseInt(maxStr);
                min= Integer.parseInt(minStr);
                if(max > min){
                    SRT.getgStats().setPowerMaxInt(max);
                    SRT.getgStats().setPowerMinInt(min);
                }
                else{   
                    SRT.getgStats().setHasPower(false);
                    jCheckBox6.setSelected(false);
                    jTextField2.setText("100");
                    jTextField3.setText("0");
                }
            }catch(NumberFormatException e){
                SRT.getgStats().setHasPower(false);
                jCheckBox6.setSelected(false);
                jTextField2.setText("100");
                jTextField3.setText("0");
            }

        }
        else{
            SRT.getgStats().setHasPower(false);
        }
        jDialog1.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed

    }//GEN-LAST:event_jCheckBox4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        System.setProperty("apple.laf.useScreenMenuBar", "true"); 
        System.setProperty("apple.laf.useScreenMenuItem", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Phantom Overlay");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhantomOverlay.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PhantomOverlay.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PhantomOverlay.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(PhantomOverlay.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        //</editor-fold>



        myPhantomOverlay = new SRT();
        
        
        String srcFileNm = "";
        sourceFile = srcFileNm;
        
    
    
    
        //DJIOverlay.statfromSRT.setHasTemp(false);
        //DJIOverlay.statfromSRT.setHasHR(false);
        
    
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PhantomOverlay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
