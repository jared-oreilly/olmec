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
    private final double flagSD = 20.0;

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
            //System.out.println("File not found for some reason: " + e);
        }
    }
    
    public void addReport(Report r)
    {
        reports.add(r);
        numReports++;
    }
    
    public void addPhase(Phase p)
    {
        phases.add(p);
        numPhases++;
    }
    
    public ArrayList<Phase> getPhases()
    {
        return phases;
    }
    
    public ArrayList<Report> getReports()
    {
        return reports;
    }
    
    public int getNumPhases()
    {
        return numPhases;
    }
    
    public int getNumReports()
    {
        return numReports;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public String getDir()
    {
        return dir;
    }

    public void setDir(String dir)
    {
        this.dir = dir;
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
        System.out.println(filename);
        String b = "";
        try
        {
            b += filename.substring(0, filename.indexOf(".")) + "\n";
        }
        catch(StringIndexOutOfBoundsException e)
        {
            b += filename + "\n";
        }
        
        b += abnormalMessage() + "\n";
        System.out.println("--------------------------------------------------------------------------\n");
        return b;
    }

    public String abnormalMessage()
    {
        Report r1;
        int numDiffs = numReports - 2; //ignore summary
        int numMeasures = 5;

        /*
        double[] minArr = new double[newNumReports], medianArr = new double[newNumReports], p95Arr = new double[newNumReports], p99Arr = new double[newNumReports], maxArr = new double[newNumReports];
        for (int i = 0; i < newNumReports; i++)
        {
            r1 = reports.get(i);
            minArr[i] = r1.getMin();
            medianArr[i] = r1.getMedian();
            p95Arr[i] = r1.getP95();
            p99Arr[i] = r1.getP99();
            maxArr[i] = r1.getMax();
            //System.out.println(r.getMin() + "\t" + r.getMedian() + "\t" + r.getP95() + "\t" + r.getP99() + "\t" + r.getMax());
            System.out.print(r1.getP95() + " ");
        }
        System.out.println("");
         */
        Report r2;
        double[] minArr = new double[numDiffs], medianArr = new double[numDiffs], p95Arr = new double[numDiffs], p99Arr = new double[numDiffs], maxArr = new double[numDiffs];
        for (int i = 0; i < numDiffs; i++)
        {
            //System.out.println("REPORT " + i + " AND " + (i + 1));
            r1 = reports.get(i);
            r2 = reports.get(i + 1);
            minArr[i] = roundTo(r2.getMin() - r1.getMin(), 2);
            medianArr[i] = roundTo(r2.getMedian() - r1.getMedian(), 2);
            p95Arr[i] = roundTo(r2.getP95() - r1.getP95(), 2);
            p99Arr[i] = roundTo(r2.getP99() - r1.getP99(), 2);
            maxArr[i] = roundTo(r2.getMax() - r1.getMax(), 2);
            //System.out.println("Min\tMed\tP95\tP99\tMax");
            //System.out.println(r2.getMin() + "\t" + r2.getMedian() + "\t" + r2.getP95() + "\t" + r2.getP99() + "\t" + r2.getMax());
            //System.out.println("-\t-\t-\t-\t-");
            //System.out.println(r1.getMin() + "\t" + r1.getMedian() + "\t" + r1.getP95() + "\t" + r1.getP99() + "\t" + r1.getMax());
            //System.out.println("=\t=\t=\t=\t=");
            //System.out.println(minArr[i] + "\t" + medianArr[i] + "\t" + p95Arr[i] + "\t" + p99Arr[i] + "\t" + maxArr[i]);
            //System.out.println("");
        }
        System.out.println("");

        double[] sdArr = new double[numMeasures];
        sdArr[0] = calcSD(minArr, false, false);
        //sdArr[1] = calcSD(minArr, true, true);
        sdArr[1] = calcSD(medianArr, false, false);
        sdArr[2] = calcSD(p95Arr, false, false);
        //sdArr[3] = calcSD(p95Arr, true, true);
        sdArr[3] = calcSD(p99Arr, false, false);
        sdArr[4] = calcSD(maxArr, false, false);

        System.out.println("Min\tMed\tP95\tP99\tMax");
        System.out.println(sdArr[0] + "\t" + sdArr[1] + "\t" + sdArr[2] + "\t" + sdArr[3] + "\t" + sdArr[4]);

        String s = "";

        if (sdArr[0] > flagSD)
        {
            s += "\tABNORMAL: Min deviates!\n";
        }

        if (sdArr[1] > flagSD)
        {
            s += "\tABNORMAL: Median deviates!\n";
        }

        if (sdArr[2] > flagSD)
        {
            s += "\tABNORMAL: P95 deviates!\n";
        }

        if (sdArr[3] > flagSD)
        {
            s += "\tABNORMAL: P99 deviates!\n";
        }

        if (sdArr[4] > flagSD)
        {
            s += "\tABNORMAL: Max deviates!\n";
        }

        if (s.equals(""))
        {
            s = "\t-\n";
        }
        return s;
    }

    public double calcSD(double arr[], boolean useFirst, boolean useLast)
    {
        double sd;

        int firstI;
        if (useFirst)
        {
            firstI = 0;
        } else
        {
            //firstI = 1;
            firstI = 2;
        }

        int end;
        if (useLast)
        {
            end = arr.length;
        } else
        {
            //end = arr.length - 1;
            end = arr.length - 2;
        }

        double sum = 0;
        int extra = 0;
        //
        for (int i = 0; i < firstI; i++)
        {
            if (arr[i] >= 0)
            {
                sum += arr[i];
                extra++;
                System.out.print(arr[i] + "\t");
            } else
            {
                System.out.print("(" + arr[i] + ")\t");
            }
        }
        //

        for (int i = firstI; i < end; i++)
        {
            System.out.print(arr[i] + "\t");
            sum += arr[i];
        }

        //
        for (int i = end; i < arr.length; i++)
        {
            if (arr[i] >= 0)
            {
                sum += arr[i];
                extra++;
                System.out.print(arr[i] + "\t");
            } else
            {
                System.out.print("(" + arr[i] + ")\t");
            }
        }
        //

        System.out.println("");
        //calculate average
        double avg = sum / ((end - firstI) + extra);

        double top = 0;
        double diff;
        extra = 0;
        //
        for (int i = 0; i < firstI; i++)
        {
            if (arr[i] >= 0)
            {
                diff = arr[i] - avg;
                top += Math.pow(diff, 2);
                extra++;
            }
        }
        //

        for (int i = firstI; i < end; i++)
        {
            diff = arr[i] - avg;
            top += Math.pow(diff, 2);
        }

        //
        for (int i = end; i < arr.length; i++)
        {
            if (arr[i] >= 0)
            {
                diff = arr[i] - avg;
                top += Math.pow(diff, 2);
                extra++;
            }
        }
        //

        double frac = top / ((end - firstI) + extra);
        sd = Math.sqrt(frac);

        sd = roundTo(sd, 2);

        return sd;

    }

    public double roundTo(double num, int dec)
    {
        int toTimesBy = (int) Math.pow(10, dec);
        num *= toTimesBy;
        num = Math.round(num);
        num /= toTimesBy;
        return num;
    }
}
