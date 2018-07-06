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
            Scanner scannerFile = new Scanner(new File("../../mayan/Mayan/gen/runs/" + dir + "/" + filename));
            ArrayList<String> componentArr = new ArrayList<String>();
            while (scannerFile.hasNextLine())
            {
                componentArr.add(scannerFile.nextLine());
            }
            scannerFile.close();

            //extract report info
            for (int i = 0; i < componentArr.size(); i++)
            {
                try
                {
                    if (componentArr.get(i).substring(0, 6).equals("Report"))
                    {
                        String header = componentArr.get(i).trim();
                        i++;

                        String scenariosLaunched = componentArr.get(i).trim();
                        scenariosLaunched = scenariosLaunched.substring(21);
                        i++;

                        String scenariosCompleted = componentArr.get(i).trim();
                        scenariosCompleted = scenariosCompleted.substring(21);
                        i++;

                        String requestsCompleted = componentArr.get(i).trim();
                        requestsCompleted = requestsCompleted.substring(21);
                        i++;

                        String rps = componentArr.get(i).trim();
                        rps = rps.substring(10);
                        i++;
                        i++;

                        String min = componentArr.get(i).trim();
                        min = min.substring(5);
                        i++;

                        String max = componentArr.get(i).trim();
                        max = max.substring(5);
                        i++;

                        String med = componentArr.get(i).trim();
                        med = med.substring(8);
                        i++;

                        String p95 = componentArr.get(i).trim();
                        p95 = p95.substring(5);
                        i++;

                        String p99 = componentArr.get(i).trim();
                        p99 = p99.substring(5);
                        i++;

                        String codes = "";
                        i++;
                        String nextLine = componentArr.get(i).trim();
                        while (!nextLine.equals(""))
                        {
                            codes += nextLine + ",";
                            i++;
                            nextLine = componentArr.get(i).trim();
                        }
                        codes = codes.substring(0, codes.length() - 1);

                        Report report = new Report(header, Integer.parseInt(scenariosLaunched), Integer.parseInt(scenariosCompleted), Integer.parseInt(requestsCompleted), Double.parseDouble(rps), Double.parseDouble(min), Double.parseDouble(max), Double.parseDouble(med), Double.parseDouble(p95), Double.parseDouble(p99), codes);
                        reports.add(report);
                        numReports++;

                    } else if (componentArr.get(i).substring(0, 14).equals("Summary report"))
                    {
                        String header = componentArr.get(i).trim();
                        i++;

                        String scenariosLaunched = componentArr.get(i).trim();
                        scenariosLaunched = scenariosLaunched.substring(21);
                        i++;

                        String scenariosCompleted = componentArr.get(i).trim();
                        scenariosCompleted = scenariosCompleted.substring(21);
                        i++;

                        String requestsCompleted = componentArr.get(i).trim();
                        requestsCompleted = requestsCompleted.substring(21);
                        i++;

                        String rps = componentArr.get(i).trim();
                        rps = rps.substring(10);
                        i++;
                        i++;

                        String min = componentArr.get(i).trim();
                        min = min.substring(5);
                        i++;

                        String max = componentArr.get(i).trim();
                        max = max.substring(5);
                        i++;

                        String med = componentArr.get(i).trim();
                        med = med.substring(8);
                        i++;

                        String p95 = componentArr.get(i).trim();
                        p95 = p95.substring(5);
                        i++;

                        String p99 = componentArr.get(i).trim();
                        p99 = p99.substring(5);
                        i++;

                        String scenarioCounts = "";
                        i++;
                        String nextLineScen = componentArr.get(i).trim();
                        while (!nextLineScen.equals("Codes:"))
                        {
                            scenarioCounts += nextLineScen + ",";
                            i++;
                            nextLineScen = componentArr.get(i).trim();
                        }
                        scenarioCounts = scenarioCounts.substring(0, scenarioCounts.length() - 1);

                        String codes = "";
                        i++;
                        String nextLineCodes = componentArr.get(i).trim();
                        while (!nextLineCodes.equals(""))
                        {
                            codes += nextLineCodes + ",";
                            i++;
                            nextLineCodes = componentArr.get(i).trim();
                        }
                        codes = codes.substring(0, codes.length() - 1);

                        Report report = new Report(header, Integer.parseInt(scenariosLaunched), Integer.parseInt(scenariosCompleted), Integer.parseInt(requestsCompleted), Double.parseDouble(rps), Double.parseDouble(min), Double.parseDouble(max), Double.parseDouble(med), Double.parseDouble(p95), Double.parseDouble(p99), scenarioCounts, codes);
                        reports.add(report);
                        numReports++;

                    } else if (componentArr.get(i).substring(0, 7).equals("Started"))
                    {
                        String s = componentArr.get(i);
                        int pn = Integer.parseInt(s.substring(14, s.indexOf(",")));
                        int d = Integer.parseInt(s.substring(s.indexOf(":") + 2, s.indexOf("s @")));
                        String td = s.substring(s.indexOf("@") + 2);
                        Phase temp = new Phase(pn, d, td);
                        phases.add(temp);
                        numPhases++;
                    }
                } catch (StringIndexOutOfBoundsException e)
                {

                }
            }
        } catch (IOException e)
        {
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
        String buildUp = "";
        buildUp += dir + "/" + filename + "\n";
        buildUp += "REPORTS (" + numReports + "):\n";
        for (int i = 0; i < numReports; i++)
        {
            buildUp += reports.get(i) + "\n";
        }
        buildUp += "PHASES (" + numPhases + "):\n";
        for (int i = 0; i < numPhases; i++)
        {
            buildUp += phases.get(i) + "\n";
        }
        return buildUp;
    }

    public String getLagReport()
    {
        if (debug)
        {
            System.out.println(filename);
        }
        String buildUp = "";
        try
        {
            buildUp += filename.substring(0, filename.indexOf(".")) + "\n";
        } catch (StringIndexOutOfBoundsException e)
        {
            buildUp += filename + "\n";
        }

        buildUp += abnormalMessage() + "\n";
        if (debug)
        {
            System.out.println("--------------------------------------------------------------------------\n");
        }
        return buildUp;
    }

    public String abnormalMessage()
    {
        Report report;
        int numNonSummaryReports = numReports - 1; //ignore summary
        int numMeasures = 5;

        double[] minArr = new double[numNonSummaryReports], medianArr = new double[numNonSummaryReports], p95Arr = new double[numNonSummaryReports], p99Arr = new double[numNonSummaryReports], maxArr = new double[numNonSummaryReports];

        for (int i = 0; i < numNonSummaryReports; i++)
        {
            report = reports.get(i);

            minArr[i] = report.getMin();

            medianArr[i] = report.getMedian();

            p95Arr[i] = report.getP95();

            p99Arr[i] = report.getP99();

            maxArr[i] = report.getMax();
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
            double[] gradientArr = calcGrads(arrays[i]);
            double sd = calcSD(gradientArr, 1, 2);
            if (sd > flagSD)
            {
                String buildUp = "\t" + tags[i] + " deviates from linear, ";
                double[] gradient2ndArr = calcGrads(gradientArr);
                double newSd = calcSD(gradient2ndArr, 1, 1);
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

        int firstMiddlePos = n1;
        int lastMiddlePos = arr.length - n2;

        
        //conditionally add the front ones
        double sum = 0;
        int extra = 0;
        for (int i = 0; i < firstMiddlePos; i++)
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
        
        //add all middles
        for (int i = firstMiddlePos; i < lastMiddlePos; i++)
        {
            if (debug)
            {
                System.out.print(arr[i] + "\t");
            }
            sum += arr[i];
        }

        //conditionally add end ones
        for (int i = lastMiddlePos; i < arr.length; i++)
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
        

        if (debug)
        {
            System.out.println("");
        }
        
        //calculate average
        double avg = sum / ((lastMiddlePos - firstMiddlePos) + extra);

        double top = 0;
        double diff;
        extra = 0;
        
        //conditionally add front ones
        for (int i = 0; i < firstMiddlePos; i++)
        {
            if (arr[i] >= 0)
            {
                diff = arr[i] - avg;
                top += Math.pow(diff, 2);
                extra++;
            }
        }

        //add all middles
        for (int i = firstMiddlePos; i < lastMiddlePos; i++)
        {
            diff = arr[i] - avg;
            top += Math.pow(diff, 2);
        }

        //conditionally add end ones
        for (int i = lastMiddlePos; i < arr.length; i++)
        {
            if (arr[i] >= 0)
            {
                diff = arr[i] - avg;
                top += Math.pow(diff, 2);
                extra++;
            }

        }

        double frac = top / ((lastMiddlePos - firstMiddlePos) + extra);
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
