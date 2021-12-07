package sd;

import java.io.IOException;
import java.net.URL;
import com.thetransactioncompany.jsonrpc2.*;
import com.thetransactioncompany.jsonrpc2.client.*;
import java.net.*;
import java.util.*;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import org.jfree.ui.RefineryUtilities;
import sd.charts.BaselineChart;
import sd.charts.OptimalChart;
import sd.charts.SevenDaysChart;

import java.util.List;
import java.util.ArrayList;

//******  4606a841-ff66-46a2-9eab-77628b35fd48 *******///

public class ClientController {

    public void viewSevenDays() {
        URL serverUrl = null;

        try {
            serverUrl = new URL("http://localhost:8080/rpc");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        JSONRPC2Session mySession = new JSONRPC2Session(serverUrl);
        mySession.getOptions().setAllowedResponseContentTypes(new String[]{"text/html;charset=UTF-8"});

        String method = "getMonitoringLastSevenDays";
        //Map<String, Object> params = new HashMap<String, Object>();
       // params.put("recipient", "Penny Adams");
       // params.put("amount", 179.05);
        String id = "req-001";

        // Create a new JSON-RPC 2.0 request
        JSONRPC2Request reqOut = new JSONRPC2Request(method, id);

        // Parse response string
        JSONRPC2Response respIn = null;

        try {
            respIn = mySession.send(reqOut);
        } catch (JSONRPC2SessionException e) {
            System.err.println(e.getMessage());
        }

        // Check for success or error
        if (respIn!=null && respIn.indicatesSuccess()) {

            System.out.println("The request succeeded :");

            System.out.println("\tresult : " + respIn.getResult());
            System.out.println("\tid     : " + respIn.getID());
            String listString = respIn.getResult().toString();
            listString = listString.substring(1, listString.length() - 1);
            String[] ary = listString.split(",");

            List<Float> values = new ArrayList<>();

            for(String s:ary){
                values.add(Float.parseFloat(s));
            }


            SevenDaysChart chart = new SevenDaysChart("Consumption in last 7 days" , "Consumption in last 7 days", values);

            chart.pack( );
            RefineryUtilities.centerFrameOnScreen( chart );
            chart.setVisible( true );
        }
        else {
            System.out.println("The request failed :");

            JSONRPC2Error err = respIn.getError();

            System.out.println("\terror.code    : " + err.getCode());
            System.out.println("\terror.message : " + err.getMessage());
            System.out.println("\terror.data    : " + err.getData());
        }
    }

    public void baseline() {
        URL serverUrl = null;

        try {
            serverUrl = new URL("http://localhost:8080/rpc");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        JSONRPC2Session mySession = new JSONRPC2Session(serverUrl);
        mySession.getOptions().setAllowedResponseContentTypes(new String[]{"text/html;charset=UTF-8"});

        String method = "baseline";
        //Map<String, Object> params = new HashMap<String, Object>();
        // params.put("recipient", "Penny Adams");
        // params.put("amount", 179.05);
        String id = "req-002";

        // Create a new JSON-RPC 2.0 request
        JSONRPC2Request reqOut = new JSONRPC2Request(method, id);

        // Parse response string
        JSONRPC2Response respIn = null;

        try {
            respIn = mySession.send(reqOut);
        } catch (JSONRPC2SessionException e) {
            System.err.println(e.getMessage());
        }

        // Check for success or error
        if (respIn!=null && respIn.indicatesSuccess()) {

            System.out.println("The request succeeded :");

            System.out.println("\tresult : " + respIn.getResult());
            System.out.println("\tid     : " + respIn.getID());
            String listString = respIn.getResult().toString();
            listString = listString.substring(1, listString.length() - 1);
            String[] ary = listString.split(",");

            List<Float> values = new ArrayList<>();

            for(String s:ary){
                values.add(Float.parseFloat(s));
            }


            BaselineChart chart = new BaselineChart("Baseline" , "Baseline", values);

            chart.pack( );
            RefineryUtilities.centerFrameOnScreen( chart );
            chart.setVisible( true );
        }
        else {
            System.out.println("The request failed :");

            JSONRPC2Error err = respIn.getError();

            System.out.println("\terror.code    : " + err.getCode());
            System.out.println("\terror.message : " + err.getMessage());
            System.out.println("\terror.data    : " + err.getData());
        }
    }

    public void optimalInterval(String text) {
        URL serverUrl = null;

        try {
            serverUrl = new URL("http://localhost:8080/rpc");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        JSONRPC2Session mySession = new JSONRPC2Session(serverUrl);
        mySession.getOptions().setAllowedResponseContentTypes(new String[]{"text/html;charset=UTF-8"});

        String method = "optimumInterval";
        String id = "req-003";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("interval", text);

        JSONRPC2Request reqOut = new JSONRPC2Request(method, params, id);

        JSONRPC2Response respIn = null;

        try {
            respIn = mySession.send(reqOut);
        } catch (JSONRPC2SessionException e) {
            System.err.println(e.getMessage());
        }

        if (respIn!=null && respIn.indicatesSuccess()) {

            System.out.println("The request succeeded :");

            System.out.println("\tresult : " + respIn.getResult());
            System.out.println("\tid     : " + respIn.getID());
            String listString = respIn.getResult().toString();
            listString = listString.substring(1, listString.length() - 1);
            String[] ary = listString.split(",");

            List<Float> values = new ArrayList<>();

            for(String s:ary){
                values.add(Float.parseFloat(s));
            }

            OptimalChart chart = new OptimalChart("Optimal interval" , "Optimal interval", values);

            chart.pack();
            RefineryUtilities.centerFrameOnScreen( chart );
            chart.setVisible( true );
        }
        else {
            System.out.println("The request failed :");

            JSONRPC2Error err = respIn.getError();

            System.out.println("\terror.code    : " + err.getCode());
            System.out.println("\terror.message : " + err.getMessage());
            System.out.println("\terror.data    : " + err.getData());
        }
    }
}
