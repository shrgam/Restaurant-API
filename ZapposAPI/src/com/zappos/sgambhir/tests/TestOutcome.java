package com.zappos.sgambhir.tests;

public class TestOutcome {

	private Boolean result;
	private String output ;

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

	public void setResult(Boolean result){
		this.result = result;
	}


	public String getOutput() {
		return output ;
	}

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