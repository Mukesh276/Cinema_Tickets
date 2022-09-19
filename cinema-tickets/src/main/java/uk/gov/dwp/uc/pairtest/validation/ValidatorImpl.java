package uk.gov.dwp.uc.pairtest.validation;

import java.util.ArrayList;
import java.util.List;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestCount;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.utilities.Constants;

public class ValidatorImpl implements Validator {

	@Override
	public void validateRequest(Long accountId, TicketTypeRequestCount ticketTypeRequestCount) {

		List<String> validationMessages = new ArrayList<>();

		if (accountId <= 0)
			validationMessages.add("Invalid acount ID");

		if (ticketTypeRequestCount.getNo_Of_Children() < 0 || ticketTypeRequestCount.getNo_Of_Infants() < 0)
			validationMessages.add("Number of children or infants ticket can't be negative");

		if (ticketTypeRequestCount.getNo_Of_Adults() <= 0)
			validationMessages.add("At least one adult ticket should be present in the ticket request");

		if (ticketTypeRequestCount.getNo_Of_Adults() < ticketTypeRequestCount.getNo_Of_Infants())
			validationMessages.add("As infant sits on adult's lap, infants number can't be more than adults number");

		if (ticketTypeRequestCount.getNo_Of_Infants() + ticketTypeRequestCount.getNo_Of_Children()
				+ ticketTypeRequestCount.getNo_Of_Adults() > Constants.MAX_ALLOWED_TICKETS)
			validationMessages.add(String.format("Number of Requests can't be more than %d", Constants.MAX_ALLOWED_TICKETS));

		if (validationMessages.size() > 0)
			throw new InvalidPurchaseException(validationMessages);
	}
}
