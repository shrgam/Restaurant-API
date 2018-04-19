package com.zappos.sgambhir.tests;

import java.util.HashMap;

import javax.ws.rs.core.Response;

/**
 * @author Shriya
 *
 */
public class TestController {

    private HashMap<String, TestOutcome> resultSet = new HashMap<String, TestOutcome>();

	/**
	 * @param testCaseName
	 * @param resp
	 */
	public void positiveCase(String testCaseName, Response resp)
	{
		boolean result = false;
		String testOutput = "";
		
		if (resp != null)
		{
			Object respCode = resp.getStatus();

			if (respCode != null
					&& respCode.equals(Response.Status.OK.getStatusCode())) {
				result = true;
			}

			testOutput = resp.readEntity(String.class);
		}
		else
		{
			result = false;
			testOutput = "No response recieved";
		}

		updateResultSet(testCaseName, result, testOutput);
		
	}
	
	/**
	 * @param testCaseName
	 * @param resp
	 */
	public void negativeCase(String testCaseName, Response resp)
	{
		boolean result = false;
		String testOutput = "";
		
		if (resp !=null){
			Object respCode = resp.getStatus();

			if (respCode != null && respCode.equals(Response.Status.BAD_REQUEST.getStatusCode())) {
				result = true;
			}

			testOutput = resp.readEntity(String.class);
		}
		else
		{
			result = false;
			testOutput = "No response recieved";
		}

		updateResultSet(testCaseName, result, testOutput);
	}
	
	public void internalError(String testCaseName, Response resp)
	{
		boolean result = false;
		String testOutput = "";
		
		if (resp !=null){
			Object respCode = resp.getStatus();

			if (respCode != null && respCode.equals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())) {
				result = true;
			}

			testOutput = resp.readEntity(String.class);
		}
		else
		{
			result = false;
			testOutput = "No response recieved";
		}

		updateResultSet(testCaseName, result, testOutput);
	}
	
	/**
	 * @param testCaseName
	 * @param result
	 * @param reason
	 */
	private void updateResultSet(String testCaseName, Boolean result, String reason)
	{
		//method put will replace the value of an existing key and will create it if doesn't exist.
		TestOutcome to = new TestOutcome(result, reason);
		resultSet.put(testCaseName, to);
	}
	
	public void printResults()
	{
		for (String testName: resultSet.keySet()){
			String key = testName.toString();
			String value = resultSet.get(key).toString();
			System.out.println(key + " : " + value);  
		} 
	}
}