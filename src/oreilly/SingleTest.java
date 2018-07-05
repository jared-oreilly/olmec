/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oreilly;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author jared.oreilly
 */
public class SingleTest
{

    private ArrayList<Report> reports;
    private ArrayList<Phase> phases;
    private int numReports;
    private int numPhases;
    private String dir;
    private String filename;

    public SingleTest(String dir, String filename)
    {
        this.filename = filename;
        this.dir = dir;
        reports = new ArrayList<Report>();
        phases = new ArrayList<Phase>();
        numReports = 0;
        numPhases = 0;

        try
        {
            //put file into arraylist
            //System.out.println("IN SINGLE TEST, FILE IS ../../mayan/Mayan/gen/runs/" + dir + "/" + filename);
            Scanner scFile = new Scanner(new File("../../mayan/Mayan/gen/runs/" + dir + "/" + filename));
            ArrayList<String> compArr = new ArrayList<String>();
            while (scFile.hasNextLine())
            {
                compArr.add(scFile.nextLine());
            }
            scFile.close();

            //extract report info
            for (int i = 0; i < compArr.size(); i++)
            {
                try
                {
                    if (compArr.get(i).substring(0, 6).equals("Report"))
                    {
                        String h = compArr.get(i).trim();
                        i++;

                        String sl = compArr.get(i).trim();
                        sl = sl.substring(21);
                        i++;

                        String sc = compArr.get(i).trim();
                        sc = sc.substring(21);
                        i++;

                        String rc = compArr.get(i).trim();
                        rc = rc.substring(21);
                        i++;

                        String rps = compArr.get(i).trim();
                        rps = rps.substring(10);
                        i++;
                        i++;

                        String min = compArr.get(i).trim();
                        min = min.substring(5);
                        i++;

                        String max = compArr.get(i).trim();
                        max = max.substring(5);
                        i++;

                        String med = compArr.get(i).trim();
                        med = med.substring(8);
                        i++;

                        String p95 = compArr.get(i).trim();
                        p95 = p95.substring(5);
                        i++;

                        String p99 = compArr.get(i).trim();
                        p99 = p99.substring(5);
                        i++;

                        String codes = "";
                        i++;
                        String nextLine = compArr.get(i).trim();
                        while (!nextLine.equals(""))
                        {
                            codes += nextLine + ",";
                            i++;
                            nextLine = compArr.get(i).trim();
                        }
                        codes = codes.substring(0, codes.length() - 1);

                        Report temp = new Report(h, Integer.parseInt(sl), Integer.parseInt(sc), Integer.parseInt(rc), Double.parseDouble(rps), Double.parseDouble(min), Double.parseDouble(max), Double.parseDouble(med), Double.parseDouble(p95), Double.parseDouble(p99), codes);
                        //System.out.println(temp);
                        reports.add(temp);
                        numReports++;

                    } else if (compArr.get(i).substring(0, 14).equals("Summary report"))
                    {
                        String h = compArr.get(i).trim();
                        i++;

                        String sl = compArr.get(i).trim();
                        sl = sl.substring(21);
                        i++;

                        String sc = compArr.get(i).trim();
                        sc = sc.substring(21);
                        i++;

                        String rc = compArr.get(i).trim();
                        rc = rc.substring(21);
                        i++;

                        String rps = compArr.get(i).trim();
                        rps = rps.substring(10);
                        i++;
                        i++;

                        String min = compArr.get(i).trim();
                        min = min.substring(5);
                        i++;

                        String max = compArr.get(i).trim();
                        max = max.substring(5);
                        i++;

                        String med = compArr.get(i).trim();
                        med = med.substring(8);
                        i++;

                        String p95 = compArr.get(i).trim();
                        p95 = p95.substring(5);
                        i++;

                        String p99 = compArr.get(i).trim();
                        p99 = p99.substring(5);
                        i++;

                        String scenarioCounts = "";
                        i++;
                        String nextLineScen = compArr.get(i).trim();
                        while (!nextLineScen.equals("Codes:"))
                        {
                            scenarioCounts += nextLineScen + ",";
                            i++;
                            nextLineScen = compArr.get(i).trim();
                        }
                        scenarioCounts = scenarioCounts.substring(0, scenarioCounts.length() - 1);

                        String codes = "";
                        i++;
                        String nextLineCodes = compArr.get(i).trim();
                        while (!nextLineCodes.equals(""))
                        {
                            codes += nextLineCodes + ",";
                            i++;
                            nextLineCodes = compArr.get(i).trim();
                        }
                        codes = codes.substring(0, codes.length() - 1);

                        Report temp = new Report(h, Integer.parseInt(sl), Integer.parseInt(sc), Integer.parseInt(rc), Double.parseDouble(rps), Double.parseDouble(min), Double.parseDouble(max), Double.parseDouble(med), Double.parseDouble(p95), Double.parseDouble(p99), scenarioCounts, codes);
                        //System.out.println(temp);
                        reports.add(temp);
                        numReports++;

                    } else if (compArr.get(i).substring(0, 7).equals("Started"))
                    {
                        String s = compArr.get(i);
                        int pn = Integer.parseInt(s.substring(14, s.indexOf(",")));
                        int d = Integer.parseInt(s.substring(s.indexOf(":") + 2, s.indexOf("s @")));
                        String td = s.substring(s.indexOf("@") + 2);
                        Phase temp = new Phase(pn, d, td);
                        //System.out.println(temp);
                        phases.add(temp);
                        numPhases++;
                    }
                } catch (StringIndexOutOfBoundsException e)
                {

                }
            }
        }
        catch(IOException e)
        {
            System.out.println("File not found for some reason: " + e);
        }
    }
    
    @Override
    public String toString()
    {
        String b = "";
        b += dir + "/" + filename + "\n";
        b += "REPORTS (" + numReports + "):\n";
        for(int i = 0; i < numReports; i++)
        {
            b += reports.get(i) + "\n";
        }
        b += "PHASES (" + numPhases + "):\n";
        for(int i = 0; i < numPhases; i++)
        {
            b += phases.get(i) + "\n";
        }
        return b;
    }
}
