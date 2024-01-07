package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Common.Log;
import Helpers.DataFaker.DataFaker;
import Helpers.Reporter.Listener;
import org.testng.TestNG;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import static Helpers.Helpers.ExcuteCommandCMD;
import static Helpers.Helpers.getProjectPath;
import static Utils.StringHandler.capitalizeFully;


@Listeners(Listener.class)
public class mainTest {
    public static void main(String[] args) throws IOException {
        TestNG testng = new TestNG();
        String xmlFileName = "./runtest.xml";
        List<XmlSuite> suite;
        try {
            suite = (List<XmlSuite>) (new Parser(xmlFileName).parse());
            testng.setXmlSuites(suite);
            testng.run();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(DataFaker.getAddressByFaker());
//        System.out.println(DataFaker.getCellPhoneByFaker());
//        System.out.println(DataFaker.getFullNameByFaker());
//        System.out.println(DataFaker.getFakerByLocate("vi").name().fullName());
//        System.out.println(DataFaker.getCellPhoneByFaker());
    }
}

