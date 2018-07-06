package oreilly;

import java.util.*;

public class Report
{

    private boolean summary;
    private String header;
    private int scenariosLaunched, scenariosCompleted, requestsCompleted;
    private double rpsSent;
    private double min, max, median, p95, p99;
    private ArrayList<String> scenarioCounts;
    private String scenarioCountsString;
    private ArrayList<String> codes;
    private String codesString;

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
        this.scenarioCountsString = scenarioCounts;
        this.codesString = codes;

        this.scenarioCounts = new ArrayList<String>();
        Scanner scScenarios = new Scanner(scenarioCounts).useDelimiter(",");
        while (scScenarios.hasNext())
        {
            this.scenarioCounts.add(scScenarios.next());
        }

        this.codes = new ArrayList<String>();
        Scanner scCodes = new Scanner(codes).useDelimiter(",");
        while (scCodes.hasNext())
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
        this.codesString = codes;

        this.codes = new ArrayList<String>();
        Scanner scCodes = new Scanner(codes).useDelimiter(",");
        while (scCodes.hasNext())
        {
            this.codes.add(scCodes.next());
        }
    }

    public String getScenarioCountsString()
    {
        return scenarioCountsString;
    }

    public void setScenarioCountsString(String scenarioCountsString)
    {
        this.scenarioCountsString = scenarioCountsString;
    }

    public String getCodesString()
    {
        return codesString;
    }

    public void setCodesString(String codesString)
    {
        this.codesString = codesString;
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

    public double getAverageRequestsPerScenario()
    {
        double averageRPS = requestsCompleted / (double) scenariosCompleted;
        averageRPS *= 100;
        averageRPS = (int) Math.round(averageRPS);
        averageRPS /= 100.0;
        return averageRPS;
    }

    @Override
    public String toString()
    {
        String buildUp = "";
        buildUp += "Is summary: " + summary + "\n";
        buildUp += header + "\n";
        buildUp += "sl, sc, rc: " + scenariosLaunched + ", " + scenariosCompleted + ", " + requestsCompleted + "\n";
        buildUp += "rpsSent: " + rpsSent + "\n";
        buildUp += "min, median, p95, p99, max: " + min + ", " + median + ", " + p95 + ", " + p99 + ", " + max + "\n";
        buildUp += "scenarioCounts: " + scenarioCounts + "\n";
        buildUp += "codes: " + codes + "\n";
        return buildUp;
    }

    public String printSummaryReport(boolean printScenCounts)
    {
        String buildUp = "";

        buildUp += "SCENARIOS AND REQUESTS\n";
        buildUp += "Scenarios Launched:\t\t" + scenariosLaunched + "\n";
        buildUp += "Scenarios Completed:\t\t" + scenariosCompleted + "\n";
        buildUp += "Requests Completed:\t\t" + requestsCompleted + "\n";
        buildUp += "Average Requests per Scenario:\t" + getAverageRequestsPerScenario() + "\n";
        buildUp += "Requests sent per second (RPS):\t" + rpsSent + "\n";
        buildUp += "\n";

        buildUp += "RESPONSE TIMES (ms)\n";
        buildUp += "Minimum:\t\t" + min + "\n";
        buildUp += "Median:\t\t" + median + "\n";
        buildUp += "95th %ile:\t\t" + p95 + "\n";
        buildUp += "99th %ile:\t\t" + p99 + "\n";
        buildUp += "Maximum:\t\t" + max + "\n";
        buildUp += "\n";

        buildUp += "CODES\n";
        for (int i = 0; i < codes.size(); i++)
        {
            String currentCode = codes.get(i);
            buildUp += currentCode.substring(0, currentCode.indexOf(":") + 1) + "\t" + currentCode.substring(currentCode.indexOf(":") + 2) + "\n";
        }
        buildUp += "\n";

        if (printScenCounts)
        {
            buildUp += "SCENARIO COUNTS\n";
            for (int i = 0; i < scenarioCounts.size(); i++)
            {
                String currentScenario = scenarioCounts.get(i);
                buildUp += currentScenario.substring(0, currentScenario.indexOf(":") + 1) + "\t" + currentScenario.substring(currentScenario.indexOf(":") + 2, currentScenario.indexOf("(") - 1) + "\t" + currentScenario.substring(currentScenario.indexOf("(") + 1, currentScenario.indexOf(")")) + "\n";
            }
        }

        return buildUp;
    }

    public String printScenCounts()
    {
        String buildUp = "";
        buildUp += "SCENARIO COUNTS\n";
        for (int i = 0; i < scenarioCounts.size(); i++)
        {
            String cur = scenarioCounts.get(i);
            buildUp += cur.substring(cur.indexOf(":") + 2, cur.indexOf("(") - 1) + "\t" + cur.substring(cur.indexOf("(") + 1, cur.indexOf(")")) + "\t" + cur.substring(0, cur.indexOf(":")) + "\n";
        }
        return buildUp;
    }

}
