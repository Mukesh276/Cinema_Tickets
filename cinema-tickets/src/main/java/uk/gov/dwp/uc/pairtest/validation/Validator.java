package uk.gov.dwp.uc.pairtest.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestCount;

public interface Validator {

	void validateRequest(Long accountId, TicketTypeRequestCount ticketTypeRequestCount);
}
