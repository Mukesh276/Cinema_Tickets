import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestCount;
import uk.gov.dwp.uc.pairtest.helper.Parser;
import uk.gov.dwp.uc.pairtest.utilities.TicketTypeEnum.Type;

public class TestParser {

	private Parser parser = new Parser();

	@Test
	public void testParser() {
		TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(Type.ADULT, (byte) 13);
		TicketTypeRequestCount ticketTypeRequestCount = parser.getTicketTypeRequestCount(ticketTypeRequest);

		assertEquals(13, ticketTypeRequestCount.getNo_Of_Adults());
		assertEquals(0, ticketTypeRequestCount.getNo_Of_Children());
		assertEquals(0, ticketTypeRequestCount.getNo_Of_Infants());
	}

	@Test
	public void testParserMultiple() {
		TicketTypeRequest ticketTypeRequest1 = new TicketTypeRequest(Type.ADULT, (byte) 13);
		TicketTypeRequest ticketTypeRequest2 = new TicketTypeRequest(Type.CHILD, (byte) 1);
		TicketTypeRequest ticketTypeRequest3 = new TicketTypeRequest(Type.CHILD, (byte) 1);
		TicketTypeRequest ticketTypeRequest4 = new TicketTypeRequest(Type.INFANT, (byte) 1);
		TicketTypeRequest ticketTypeRequest5 = new TicketTypeRequest(Type.INFANT, (byte) 1);

		TicketTypeRequestCount ticketTypeRequestCount = parser.getTicketTypeRequestCount(ticketTypeRequest1, ticketTypeRequest2,
				ticketTypeRequest3, ticketTypeRequest4, ticketTypeRequest5);

		assertEquals(13, ticketTypeRequestCount.getNo_Of_Adults());
		assertEquals(2, ticketTypeRequestCount.getNo_Of_Children());
		assertEquals(2, ticketTypeRequestCount.getNo_Of_Infants());
	}
}
