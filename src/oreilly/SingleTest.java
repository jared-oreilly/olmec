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
        } catch (IOException e)
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
        for (int i = 0; i < numReports; i++)
        {
            b += reports.get(i) + "\n";
        }
        b += "PHASES (" + numPhases + "):\n";
        for (int i = 0; i < numPhases; i++)
        {
            b += phases.get(i) + "\n";
        }
        return b;
    }

    public String getLagReport()
    {
        String b = "";
        b += filename.substring(0, filename.indexOf(".")) + "\t";
        b += abnormalMessage() + "\n\n";
        return b;
    }

    public String abnormalMessage()
    {
        Report r;
        int newNumReports = numReports - 1; //ignore summary
        int numMeasures = 5;
        double[] minArr = new double[newNumReports], medianArr = new double[newNumReports], p95Arr = new double[newNumReports], p99Arr = new double[newNumReports], maxArr = new double[newNumReports];
        for (int i = 0; i < newNumReports; i++)
        {
            r = reports.get(i);
            minArr[i] = r.getMin();
            medianArr[i] = r.getMedian();
            p95Arr[i] = r.getP95();
            p99Arr[i] = r.getP99();
            maxArr[i] = r.getMax();
            //System.out.println(r.getMin() + "\t" + r.getMedian() + "\t" + r.getP95() + "\t" + r.getP99() + "\t" + r.getMax());
            System.out.print(r.getP95() + " ");
        }
        System.out.println("");

        double[] sdArr = new double[numMeasures];
        sdArr[0] = calcSD(minArr, true);
        sdArr[1] = calcSD(medianArr, true);
        sdArr[2] = calcSD(p95Arr, false);
        sdArr[3] = calcSD(p99Arr, false);
        sdArr[4] = calcSD(maxArr, false);

        System.out.println(sdArr[2]);
        
        if(sdArr[1] > 12.0)
        {
            return "ABNORMAL: Median deviates!";
        }
        
        if(sdArr[2] > 12.0)
        {
            return "ABNORMAL: P95 deviates!";
        }
        
        
        return "-";
    }

    public double calcSD(double arr[], boolean useTopOutlier)
    {
        double sd;
        if (useTopOutlier)
        {
            double sum = 0;
            for (int i = 0; i < arr.length; i++)
            {
                sum += arr[i];
            }
            double avg = sum / arr.length;

            double top = 0;
            double diff;
            for (int i = 0; i < arr.length; i++)
            {
                diff = arr[i] - avg;
                top += Math.pow(diff, 2);
            }

            double frac = top / arr.length;
            sd = Math.sqrt(frac);
        } else
        {
            //while going through, find max
            double sum = 0;
            sum += arr[0];
            double max = arr[0];
            int maxI = 0;
            for (int i = 1; i < arr.length; i++)
            {
                sum += arr[i];
                if (arr[i] > max)
                {
                    max = arr[i];
                    maxI = i;
                }
            }
            //calculate average without max
            double avg = (sum - max) / (arr.length - 1);
            //replace max with average
            arr[maxI] = avg;
            //System.out.println("MAX: " + max + "\tAVG: " + avg);

            double top = 0;
            double diff;
            for (int i = 0; i < arr.length; i++)
            {
                diff = arr[i] - avg;
                top += Math.pow(diff, 2);
            }

            double frac = top / arr.length;
            sd = Math.sqrt(frac);
        }

        sd *= 100;
        sd = Math.round(sd);
        sd /= 100;

        return sd;

    }
}
