package com.SDP.Remaining;

import java.io.*;
import java.util.Properties;

public class PropertyReader {

    private static PropertyReader propertyReader = new PropertyReader();
    private static final String ConfigName="src/main/resources/config.properties";
    private static Properties props = new Properties();
    private static String exponentialFactor;
    private static String juniorMedior;
    private static String medior;
    private static String mediorSenior;
    private static String senior;


    public static void Read() {
        try {
            props.load(new FileInputStream(propertyReader.ConfigName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        exponentialFactor = props.getProperty("EXPONENTIAL_FACTOR");
        juniorMedior = props.getProperty("LVL_JUNIOR-MEDIOR");
        medior = props.getProperty("LVL_MEDIOR");
        mediorSenior = props.getProperty("LVL_MEDIOR-SENIOR");
        senior = props.getProperty("LVL_SENIOR");
    }

    public static void Write(){
        try {
            File f = new File(ConfigName);
            OutputStream out = new FileOutputStream(f);
            props.store(out, "DO NOT EDIT MANUALLY");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Double getExponentialFactor(){
        return Double.parseDouble(exponentialFactor);
    }

    public static void setExponentialFactor(int input){
        props.setProperty("EXPONENTIAL_FACTOR", Double.toString(input));
        Write();
    }

    public static int getJuniorMedior(){
        return Integer.parseInt(juniorMedior);
    }

    public static void setJuniorMedior(int input){
        props.setProperty("LVL_JUNIOR-MEDIOR", Integer.toString(input));
        Write();
    }

    public static int getMedior(){
        return Integer.parseInt(medior);
    }

    public static void setMedior(int input){
        props.setProperty("LVL_MEDIOR", Integer.toString(input));
        Write();
    }

    public static int getMediorSenior(){
        return Integer.parseInt(mediorSenior);
    }

    public static void setMediorSenior(int input){
        props.setProperty("LVL_MEDIOR-SENIOR", Integer.toString(input));
        Write();
    }

    public static int getSenior(){
        return Integer.parseInt(senior);
    }

    public static void setSenior(int input){
        props.setProperty("LVL_SENIOR", Integer.toString(input));
        Write();
    }
}
