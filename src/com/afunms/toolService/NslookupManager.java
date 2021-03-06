package com.afunms.toolService;

public class NslookupManager {

	private String domainName;

	private NslookupExecute nslookupExecute = new NslookupExecute();

	public NslookupManager() {
		super();
	}

	public NslookupManager(String domainName) {
		super();
		this.domainName = domainName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public void executeTraceRouteResult(String id) {
		// ƴ����
		String order = "nslookup " + domainName;
		nslookupExecute.executeNslookup(order, domainName, id);
	}

}
