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

    private boolean debug = false;

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
        if (debug)
        {
            System.out.println(filename);
        }
        String b = "";
        try
        {
            b += filename.substring(0, filename.indexOf(".")) + "\n";
        } catch (StringIndexOutOfBoundsException e)
        {
            b += filename + "\n";
        }

        b += abnormalMessage() + "\n";
        if (debug)
        {
            System.out.println("--------------------------------------------------------------------------\n");
        }
        return b;
    }

    public String abnormalMessage()
    {
        Report r;
        int numNonSReports = numReports - 1; //ignore summary
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
        double[] minArr = new double[numNonSReports], medianArr = new double[numNonSReports], p95Arr = new double[numNonSReports], p99Arr = new double[numNonSReports], maxArr = new double[numNonSReports];

        for (int i = 0; i < numNonSReports; i++)
        {
            //System.out.println("REPORT " + i + " AND " + (i + 1));
            r = reports.get(i);

            minArr[i] = r.getMin();

            medianArr[i] = r.getMedian();

            p95Arr[i] = r.getP95();

            p99Arr[i] = r.getP99();

            maxArr[i] = r.getMax();
        }

        String s = "";
        String[] tags = new String[]
        {
            "Min", "Median", "P95", "P99", "Max"
        };
        double[][] arrays = new double[][]
        {
            minArr, medianArr, p95Arr, p99Arr, maxArr
        };

        for (int i = 0; i < numMeasures; i++)
        {
            double[] gradArr = calcGrads(arrays[i]);
            //double sd = calcSD(gradArr, 1, 2);
            double sd = calcSD(gradArr, 1, 2);
            if (sd > flagSD)
            {
                String buildUp = "\t" + tags[i] + " deviates from linear, ";
                double[] grad2Arr = calcGrads(gradArr);
                double newSd = calcSD(grad2Arr, 1, 1);
                if (newSd > flagSD)
                {
                    buildUp = "\tDANGER:\t" + buildUp + "and deviates from quadratic!\n";
                } else if (newSd < (flagSD / 2.0))
                {
                    buildUp = "\tPROBLEM:\t" + buildUp + "but is very quadratic\n";
                } else
                {
                    buildUp = "\tABNORMAL:\t" + buildUp + "but is reasonably quadratic\n";
                }
                s += buildUp;

            } else if (sd < (flagSD / 2.0))
            {
                s += "\t" + tags[i] + " is very linear\n";
            } else
            {
                s += "\t" + tags[i] + " is reasonably linear\n";
            }
            if (debug)
            {
                System.out.println("---------------------------");
            }
        }

        if (s.equals(""))
        {
            s = "\t-\n";
        }

        return s;
    }

    public double calcSD(double arr[], int n1, int n2)
    {

        double sd;

        int firstI = n1;
        int end = arr.length - n2;

        double sum = 0;
        int extra = 0;
        //
        for (int i = 0; i < firstI; i++)
        {
            if (arr[i] >= 0)
            {
                if (debug)
                {
                    System.out.print("+");
                }
                sum += arr[i];
                extra++;
                if (debug)
                {
                    System.out.print(arr[i] + "\t");
                }
            } else
            {
                if (debug)
                {
                    System.out.print("(" + arr[i] + ")\t");
                }
            }
        }
        //

        for (int i = firstI; i < end; i++)
        {
            if (debug)
            {
                System.out.print(arr[i] + "\t");
            }
            sum += arr[i];
        }

        //
        for (int i = end; i < arr.length; i++)
        {
            if (arr[i] >= 0)
            {
                if (debug)
                {
                    System.out.print("+");
                }
                sum += arr[i];
                extra++;
                if (debug)
                {
                    System.out.print(arr[i] + "\t");
                }
            } else
            {
                if (debug)
                {
                    System.out.print("(" + arr[i] + ")\t");
                }
            }
        }
        //

        if (debug)
        {
            System.out.println("");
        }
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

    public double[] calcGrads(double arr[])
    {
        double[] grads = new double[arr.length - 1];
        for (int i = 0; i < grads.length; i++)
        {
            grads[i] = roundTo(arr[i + 1] - arr[i], 2);
        }
        return grads;
    }

}
