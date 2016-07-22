/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.rules;

import java.time.Duration;
import java.time.LocalTime;

/**
 *
 * @author Vincent
 */
public class XmlObject {

    private LocalTime startday, endDay;
    private LocalTime startsmallBreak, startofbigBreak, startofLunchBrake, startofGamesBreak;
    private Duration lectureDuration, smallBreakDuration, bigBreakDuration, lunchBreakDuration, gamesBreakDuration;

    private String uniName;
    private String num_of_schools;

    public XmlObject(String uniName, String num_of_schools) {
        this.uniName = uniName;
        this.num_of_schools = num_of_schools;
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

    public Duration getLectureDuration() {
        return lectureDuration;
    }

    public void setLectureDuration(Duration lectureDuration) {
        this.lectureDuration = lectureDuration;
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
    
    public void save() {
        
    }
}
