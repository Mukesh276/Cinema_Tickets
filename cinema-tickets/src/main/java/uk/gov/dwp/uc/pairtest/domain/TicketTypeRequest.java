package uk.gov.dwp.uc.pairtest.domain;

import uk.gov.dwp.uc.pairtest.utilities.TicketTypeEnum.Type;

/**
 * Immutable Object
 */
public final class TicketTypeRequest {

	private final byte noOfTickets;
	private Type type;

	public TicketTypeRequest(Type type, byte noOfTickets) {
		this.type = type;
		this.noOfTickets = noOfTickets;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public Type getTicketType() {
		return type;
	}
}
