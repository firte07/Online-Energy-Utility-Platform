package ro.tuc.ds2020.controllers;

import com.thetransactioncompany.jsonrpc2.JSONRPC2ParseException;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.tuc.ds2020.services.RPCService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class RPCController {
    private final RPCService rpcService;

    @Autowired
    public RPCController(RPCService rpcService){
        this.rpcService = rpcService;
    }

    @RequestMapping("/rpc")
    public ResponseEntity<JSONObject> connectRpc(@RequestBody String message) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("Message " + message); //request
        JSONRPC2Request reqIn = null;

        try {
            reqIn = JSONRPC2Request.parse(message);
        } catch (JSONRPC2ParseException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        // How to extract the request data
        System.out.println("Parsed request with properties :");
        System.out.println("\tmethod     : " + reqIn.getMethod());
        System.out.println("\tparameters : " + reqIn.getNamedParams());
        System.out.println("\tid         : " + reqIn.getID() + "\n");

        if(reqIn.getMethod().equals("getMonitoringLastSevenDays") ||
                reqIn.getMethod().equals("baseline")){
            Method method = rpcService.getClass().getMethod(reqIn.getMethod(), String.class);
            List<Float> methodResult = (List<Float>) method.invoke(rpcService,
                    reqIn.getNamedParams().get("idClient"));
            System.out.println("Size " + methodResult.size());

            String result = String.valueOf(methodResult);
            JSONRPC2Response respOut = new JSONRPC2Response(result, reqIn.getID());
            System.out.println("Result: "+result);
            System.out.println("Resp out: " + respOut);
            return new ResponseEntity<>(respOut.toJSONObject(), HttpStatus.OK);
        }else if (reqIn.getMethod().equals("optimumInterval")){
            Method method = rpcService.getClass().getMethod(reqIn.getMethod(), String.class, String.class);
            List<Float> methodResult = (List<Float>) method.invoke(rpcService,
                    reqIn.getNamedParams().get("idClient"),
                    reqIn.getNamedParams().get("interval"));
            System.out.println(methodResult.size());
            String result = String.valueOf(methodResult);
            JSONRPC2Response respOut = new JSONRPC2Response(result, reqIn.getID());
            return new ResponseEntity<>(respOut.toJSONObject(), HttpStatus.OK);
        }
        String result = "Failed ";
        JSONRPC2Response respOut = new JSONRPC2Response(result, reqIn.getID());

        return new ResponseEntity<>(respOut.toJSONObject(), HttpStatus.OK);
    }
}
