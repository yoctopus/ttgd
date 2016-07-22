/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.rules;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import ttgs.algorithms.Engine;
import ttgs.main.MainLogger;

/**
 *
 * @author Vincent
 */
public class FileDefaults {


    private LocalTime startday;
    private LocalTime endDay;
    private LocalTime startsmallBreak;
    private LocalTime startofbigBreak;
    private LocalTime startofLunchBrake;
    private LocalTime startofGamesBreak;
    private Duration lectureDuration;
    private Duration smallBreakDuration;
    private Duration bigBreakDuration;
    private Duration lunchBreakDuration;
    private Duration gamesBreakDuration;
    private String uniName;
    private String num_of_schools;

    private File config;
    private Document doc;
    private DocumentBuilder builder;

    public FileDefaults(File file) {
        try {
            config = file;
            logit("Building document factory...");
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            logit("Parsing file from factory...");
            doc = builder.parse(config);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Defaults.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LocalTime getStartday() {
        return startday;
    }

    public void setStartday(LocalTime startday) {
        this.startday = startday;
    }

    public LocalTime getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalTime endDay) {
        this.endDay = endDay;
    }

    public LocalTime getStartsmallBreak() {
        return startsmallBreak;
    }

    public void setStartsmallBreak(LocalTime startsmallBreak) {
        this.startsmallBreak = startsmallBreak;
    }

    public LocalTime getStartofbigBreak() {
        return startofbigBreak;
    }

    public void setStartofbigBreak(LocalTime startofbigBreak) {
        this.startofbigBreak = startofbigBreak;
    }

    public LocalTime getStartofLunchBrake() {
        return startofLunchBrake;
    }

    public void setStartofLunchBrake(LocalTime startofLunchBrake) {
        this.startofLunchBrake = startofLunchBrake;
    }

    public LocalTime getStartofGamesBreak() {
        return startofGamesBreak;
    }

    public void setStartofGamesBreak(LocalTime startofGamesBreak) {
        this.startofGamesBreak = startofGamesBreak;
    }

    public Duration getSmallBreakDuration() {
        return smallBreakDuration;
    }

    public void setSmallBreakDuration(Duration smallBreakDuration) {
        this.smallBreakDuration = smallBreakDuration;
    }

    public Duration getBigBreakDuration() {
        return bigBreakDuration;
    }

    public void setBigBreakDuration(Duration bigBreakDuration) {
        this.bigBreakDuration = bigBreakDuration;
    }

    public Duration getLunchBreakDuration() {
        return lunchBreakDuration;
    }

    public void setLunchBreakDuration(Duration lunchBreakDuration) {
        this.lunchBreakDuration = lunchBreakDuration;
    }

    public Duration getGamesBreakDuration() {
        return gamesBreakDuration;
    }

    public void setGamesBreakDuration(Duration gamesBreakDuration) {
        this.gamesBreakDuration = gamesBreakDuration;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getNum_of_schools() {
        return num_of_schools;
    }

    public void setNum_of_schools(String num_of_schools) {
        this.num_of_schools = num_of_schools;
    }

    public Duration getLectureDuration() {
        return lectureDuration;
    }

    public void setLectureDuration(Duration lectureDuration) {
        this.lectureDuration = lectureDuration;
    }

    private ArrayList<String> Separate(String s) {
        String[] val;
        ArrayList<String> vals;
        logit("spliting " + s);
        if (s.isEmpty()) {
            vals = null;
        } else if (s.contains(":")) {
            val = s.split(":");
            vals = new ArrayList();
            boolean addAll = vals.addAll(Arrays.asList(val));
        } else {
            logit("invalide string " + s + " for splitting");
            return null;
        }
        return vals;
    }

    public void configure() {
        logit("Root => " + getDoc().getDocumentElement().getNodeName());

        if (getDoc().hasChildNodes()) {
            Node root = getDoc().getFirstChild();
            logit("Getting parent node " + root.getNodeName());
            if (root.hasChildNodes()) {
                NodeList nodes = root.getChildNodes();
                for (int count = 0; count < nodes.getLength(); count++) {
                    Node elemNode = nodes.item(count);
                    if (elemNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) elemNode;
                        String nodeName = element.getNodeValue();
                        String value = element.getTextContent();
                        logit("Reading value " + value + " from node " + nodeName);
                        ArrayList<String> values = Separate(value);
                        int hour = Integer.parseInt(values.get(0));
                        int min = Integer.parseInt(values.get(1));
                        logit("aquaring time from " + hour + " houre and " + min + " minutes");
                        LocalTime ltime = LocalTime.of(hour, min);
                        logit("Getting duration  for " + calcMinutes(hour, min) + " minutes");
                        Duration dtime = Duration.ofMinutes(calcMinutes(hour, min));
                        loop:
                        switch (nodeName) {
                            case "start":
                                logit("setting start time to " + ltime.toString());
                                setStartday(ltime);
                                break loop;
                            case "end":
                                logit("setting end time to " + ltime.toString());
                                setEndDay(ltime);
                                break loop;
                            case "smallbreak":
                                logit("setting small break time to " + ltime.toString());
                                setStartsmallBreak(ltime);
                                break loop;
                            case "bigbreak":
                                logit("setting big break time to " + ltime.toString());
                                setStartofbigBreak(ltime);
                                break loop;
                            case "lunchbreak":
                                logit("setting lunch start time to " + ltime.toString());
                                setStartofLunchBrake(ltime);
                                break loop;
                            case "gamesbreak":
                                logit("setting games break time to " + ltime.toString());
                                setStartofGamesBreak(ltime);
                                break loop;
                            case "lecture_duration":
                                logit("setting lecture duration to " + dtime.getSeconds() + " seconds");
                                setLectureDuration(dtime);
                                break loop;
                            case "lunch_duration":
                                logit("setting lunch duration to " + dtime.getSeconds() + " seconds");
                                setLunchBreakDuration(dtime);
                                break loop;
                            case "big_break_duration":
                                logit("setting big break duration to " + dtime.getSeconds() + " seconds");
                                setBigBreakDuration(dtime);
                                break loop;
                            case "small_break_duration":
                                logit("setting small break duration to " + dtime.getSeconds() + " seconds");
                                setSmallBreakDuration(dtime);
                                break loop;

                            case "name":
                                logit("setting university name to " + value + " ");
                                setUniName(value);
                                break loop;
                            case "schools":
                                logit("setting number of schools to " + value + " ");
                                setNum_of_schools(value);
                                break loop;
                            default:
                                break loop;
                        }
                    } else {
                        logit("Traversing next node ");
                    }
                }
            } else {
                logit("Node " + root.getNodeName() + " does not have children");
            }
            setGamesBreakDuration(Engine.calcDurationBetween(getStartofGamesBreak(), getEndDay()));
            Defaults.setDefaults(this.getStartday(), this.getEndDay(), this.getStartsmallBreak(), this.getStartofbigBreak(),
                    this.getStartofLunchBrake(), this.getStartofGamesBreak(), this.getSmallBreakDuration(),
                    this.getBigBreakDuration(), this.getUniName(), this.getNum_of_schools());
        } else {
            logit(getDoc().getLocalName() + " does not have parent node");
        }
    }

    private int calcMinutes(int hour, int min) {
        return Engine.calcMinutes(hour, min);
    }

    private static void logit(String log) {
        MainLogger.logger("FileDefaults > " + log);
    }

    /**
     * @return the config
     */
    public File getConfig() {
        return config;
    }

    /**
     * @param config the config to set
     */
    public void setConfig(File config) {
        this.config = config;
    }

    /**
     * @return the doc
     */
    public Document getDoc() {
        return doc;
    }

    /**
     * @param doc the doc to set
     */
    public void setDoc(Document doc) {
        this.doc = doc;
    }

    /**
     * @return the builder
     */
    public DocumentBuilder getBuilder() {
        return builder;
    }

    /**
     * @param builder the builder to set
     */
    public void setBuilder(DocumentBuilder builder) {
        this.builder = builder;
    }
}
