package com.zappos.sgambhir.tests;

/**
 * @author Shriya
 *
 */
public class TestOutcome {

	private Boolean result;
	private String output ;

	/**
	 * @param result
	 * @param output
	 */
	public TestOutcome(Boolean result, String output){
		this.result = result;
		this.output = output;
	}

	public TestOutcome(){
		this.result = false;
		this.output = new String();
	}


	public Boolean getResult() {
		return result;
	}

	/**
	 * @param result
	 */
	public void setResult(Boolean result){
		this.result = result;
	}


	public String getOutput() {
		return output ;
	}

	/**
	 * @param reason
	 */
	public void setOutput(String reason){
		this.output  = reason;
	}

	@Override
	public String toString() {
		if (output.equals(""))
		{
			output = "No error";
		}
		
		return String.format(result + " , " + output );
	}
}