import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestCount;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.validation.Validator;
import uk.gov.dwp.uc.pairtest.validation.ValidatorImpl;

public class TestValidator {

	private Validator validator = new ValidatorImpl();
	
	@Test
	public void testSuccessValidator()
	{
		Long accountId = 10L;		
		TicketTypeRequestCount ticketTypeRequestCount = new TicketTypeRequestCount((byte)5, (byte)5, (byte)5);
		validator.validateRequest(accountId, ticketTypeRequestCount);
	}
	
	@Test
	public void testValidatorFailure_Account_ID()
	{
		Long accountId = -10L;		
		TicketTypeRequestCount ticketTypeRequestCount = new TicketTypeRequestCount((byte)5, (byte)5, (byte)5);
		try {
		validator.validateRequest(accountId, ticketTypeRequestCount);
		} catch(InvalidPurchaseException ex){
			assertTrue(ex.getMessage().contains("Invalid acount ID"));
		}
	}
	
	@Test
	public void testValidatorFailure_No_Of_Tickets()
	{
		Long accountId = 10L;		
		TicketTypeRequestCount ticketTypeRequestCount = new TicketTypeRequestCount((byte)15, (byte)15, (byte)5);
		try {
		validator.validateRequest(accountId, ticketTypeRequestCount);
		} catch(InvalidPurchaseException ex){
			assertTrue(ex.getMessage().contains("Number of Requests can't be more than 20"));
		}
	}
	
	@Test
	public void testValidatorNegativeTickets()
	{
		Long accountId = -10L;		
		TicketTypeRequestCount ticketTypeRequestCount = new TicketTypeRequestCount((byte)15, (byte)-1, (byte)0);
		try {
		validator.validateRequest(accountId, ticketTypeRequestCount);
		} catch(InvalidPurchaseException ex){
			assertTrue(ex.getMessage().contains("Number of children or infants ticket can't be negative"));
		}
	}
	
	@Test
	public void testValidator_At_Least_One_Adult()
	{
		Long accountId = 10L;		
		TicketTypeRequestCount ticketTypeRequestCount = new TicketTypeRequestCount((byte)0, (byte)11, (byte)4);
		try {
		validator.validateRequest(accountId, ticketTypeRequestCount);
		} catch(InvalidPurchaseException ex){
			assertTrue(ex.getMessage().contains("At least one adult ticket should be present in the ticket request"));
		}
	}

	@Test
	public void testValidator_Adult_And_Infants_Number()
	{
		Long accountId = 10L;		
		TicketTypeRequestCount ticketTypeRequestCount = new TicketTypeRequestCount((byte)8, (byte)1, (byte)9);
		try {
		validator.validateRequest(accountId, ticketTypeRequestCount);
		} catch(InvalidPurchaseException ex){
			assertTrue(ex.getMessage().contains("As infant sits on adult's lap, infants number can't be more than adults number"));
		}
	}
}
