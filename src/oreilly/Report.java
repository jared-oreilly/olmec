/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oreilly;

import java.util.*;

/**
 *
 * @author jared.oreilly
 */
public class Report
{

    private boolean summary;
    private String header;
    private int scenariosLaunched, scenariosCompleted, requestsCompleted;
    private double rpsSent;
    private double min, max, median, p95, p99;
    private ArrayList<String> scenarioCounts;
    private ArrayList<String> codes;

    public Report(String header, int scenariosLaunched, int scenariosCompleted, int requestsCompleted, double rpsSent, double min, double max, double median, double p95, double p99, String scenarioCounts, String codes)
    {
        //summary
        this.summary = true;
        this.header = header;
        this.scenariosLaunched = scenariosLaunched;
        this.scenariosCompleted = scenariosCompleted;
        this.requestsCompleted = requestsCompleted;
        this.rpsSent = rpsSent;
        this.min = min;
        this.max = max;
        this.median = median;
        this.p95 = p95;
        this.p99 = p99;
        
        this.scenarioCounts = new ArrayList<String>();
        Scanner scScenarios = new Scanner(scenarioCounts).useDelimiter(",");
        while(scScenarios.hasNext())
        {
            this.scenarioCounts.add(scScenarios.next());
        }
        
        this.codes = new ArrayList<String>();
        Scanner scCodes = new Scanner(codes).useDelimiter(",");
        while(scCodes.hasNext())
        {
            this.codes.add(scCodes.next());
        }
    }

    public Report(String header, int scenariosLaunched, int scenariosCompleted, int requestsCompleted, double rpsSent, double min, double max, double median, double p95, double p99, String codes)
    {
        //normal
        this.summary = false;
        this.header = header;
        this.scenariosLaunched = scenariosLaunched;
        this.scenariosCompleted = scenariosCompleted;
        this.requestsCompleted = requestsCompleted;
        this.rpsSent = rpsSent;
        this.min = min;
        this.max = max;
        this.median = median;
        this.p95 = p95;
        this.p99 = p99;
        
        this.codes = new ArrayList<String>();
        Scanner scCodes = new Scanner(codes).useDelimiter(",");
        while(scCodes.hasNext())
        {
            this.codes.add(scCodes.next());
        }
    }

    public String getCodeAt(int i)
    {
        return codes.get(i);
    }

    public int getNumCodes()
    {
        return codes.size();
    }

    public String getScenarioCountAt(int i)
    {
        return scenarioCounts.get(i);
    }

    public int getNumScenarioCount()
    {
        return scenarioCounts.size();
    }

    public boolean isSummary()
    {
        return summary;
    }

    public void setSummary(boolean summary)
    {
        this.summary = summary;
    }

    public String getHeader()
    {
        return header;
    }

    public void setHeader(String header)
    {
        this.header = header;
    }

    public int getScenariosLaunched()
    {
        return scenariosLaunched;
    }

    public void setScenariosLaunched(int scenariosLaunched)
    {
        this.scenariosLaunched = scenariosLaunched;
    }

    public int getScenariosCompleted()
    {
        return scenariosCompleted;
    }

    public void setScenariosCompleted(int scenariosCompleted)
    {
        this.scenariosCompleted = scenariosCompleted;
    }

    public int getRequestsCompleted()
    {
        return requestsCompleted;
    }

    public void setRequestsCompleted(int requestsCompleted)
    {
        this.requestsCompleted = requestsCompleted;
    }

    public double getRpsSent()
    {
        return rpsSent;
    }

    public void setRpsSent(double rpsSent)
    {
        this.rpsSent = rpsSent;
    }

    public double getMin()
    {
        return min;
    }

    public void setMin(double min)
    {
        this.min = min;
    }

    public double getMax()
    {
        return max;
    }

    public void setMax(double max)
    {
        this.max = max;
    }

    public double getMedian()
    {
        return median;
    }

    public void setMedian(double median)
    {
        this.median = median;
    }

    public double getP95()
    {
        return p95;
    }

    public void setP95(double p95)
    {
        this.p95 = p95;
    }

    public double getP99()
    {
        return p99;
    }

    public void setP99(double p99)
    {
        this.p99 = p99;
    }

    public String toString()
    {
        String b = "";
        //b += "Is summary: " + summary + "\n";
        b += header + "\n";
        b += "sl, sc, rc: " + scenariosLaunched + ", " + scenariosCompleted + ", " + requestsCompleted + "\n";
        b += "rpsSent: " + rpsSent + "\n";
        b += "min, max, median, p95, p99: " + min + ", " + max + ", " + median + ", " + p95 + ", " + p99 + "\n";
        b += "scenarioCounts: " + scenarioCounts + "\n";
        b += "codes: " + codes + "\n";
        return b;
    }

}
