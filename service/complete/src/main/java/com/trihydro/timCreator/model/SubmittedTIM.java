package com.trihydro.timCreator.model;

public class SubmittedTIM
{	
	private TIM tim;
	private RSU[] rsus;
	private String dateSent;
	private String dateReceived;
	private SNMP snmp;
	
	public TIM getTIM() {
		return tim;
	}

	public void setTIM(TIM tim) {
		this.tim = tim;
	}

	public RSU[] getRSUs() {
		return this.rsus;
	}

	public void setRSU(RSU[] rsus) {
		this.rsus = rsus;
	}

	public String getDateSent() {
		return dateSent;
	}

	public void setDateSent(String dateSent) {
		this.dateSent = dateSent;
	}

	public String getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(String dateReceived) {
		this.dateReceived = dateReceived;
	}	

	public SNMP getSNMP() {
		return this.snmp;
	}

	public void setSNMP(SNMP snmp) {
		this.snmp = snmp;
	}
}