package uk.gov.dwp.uc.pairtest.helper;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestCount;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.utilities.TicketTypeEnum.Type;

public class Parser {

	public TicketTypeRequestCount getTicketTypeRequestCount(TicketTypeRequest... ticketTypeRequests) {

		byte no_Of_infants = 0;
		byte no_Of_children = 0;
		byte no_Of_adults = 0;

		for (TicketTypeRequest t : ticketTypeRequests) {
			if (t.getTicketType() == Type.ADULT)
				no_Of_adults += t.getNoOfTickets();
			else if (t.getTicketType() == Type.CHILD)
				no_Of_children += t.getNoOfTickets();
			else if (t.getTicketType() == Type.INFANT)
				no_Of_infants += t.getNoOfTickets();
			else
				throw new InvalidPurchaseException("Invalid Ticket Type");
		}
		return new TicketTypeRequestCount(no_Of_adults, no_Of_children, no_Of_infants);
	}
}
