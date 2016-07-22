/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Vincent
 */
public class RunTTGS {

    public static void start(String[] args) {
        new RunTTGS().run();
    }

    private String GenXMLFile;
    private String genBean;

    public RunTTGS() {

        GenXMLFile = "ttgs/main/generator.xml";
        genBean = "generator";

    }

    private void run() {
        ApplicationContext context = new ClassPathXmlApplicationContext(getGenXMLFile());
        Generator gen = (Generator) context.getBean(getGenBean());
        gen.run();
    }

    /**
     * @return the GenXMLFile
     */
    public String getGenXMLFile() {
        return GenXMLFile;
    }

    /**
     * @param GenXMLFile the GenXMLFile to set
     */
    public void setGenXMLFile(String GenXMLFile) {
        this.GenXMLFile = GenXMLFile;
    }

    /**
     * @return the genBean
     */
    public String getGenBean() {
        return genBean;
    }

    /**
     * @param genBean the genBean to set
     */
    public void setGenBean(String genBean) {
        this.genBean = genBean;
    }


}
