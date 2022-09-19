package uk.gov.dwp.uc.pairtest.domain;

public class TicketTypeRequestCount {

	private byte no_Of_Adults;
	private byte no_Of_Children;
	private byte no_Of_Infants;

	public TicketTypeRequestCount(byte no_Of_Adults, byte no_Of_Children, byte no_Of_Infants) {
		super();
		this.no_Of_Adults = no_Of_Adults;
		this.no_Of_Children = no_Of_Children;
		this.no_Of_Infants = no_Of_Infants;
	}

	public byte getNo_Of_Adults() {
		return no_Of_Adults;
	}

	public byte getNo_Of_Children() {
		return no_Of_Children;
	}

	public byte getNo_Of_Infants() {
		return no_Of_Infants;
	}

}
