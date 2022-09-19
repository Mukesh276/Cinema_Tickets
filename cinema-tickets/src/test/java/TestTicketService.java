import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestCount;
import uk.gov.dwp.uc.pairtest.helper.Parser;
import uk.gov.dwp.uc.pairtest.utilities.TicketTypeEnum.Type;
import uk.gov.dwp.uc.pairtest.validation.Validator;

@RunWith(MockitoJUnitRunner.class) 
public class TestTicketService {
	
	@Mock
	private Validator mockValidator;
	
	@Mock
	private TicketPaymentService mockTicketPaymentService;
	
	@Mock
	private SeatReservationService mockSeatReservationService;
	
	@Mock
	private Parser mockParser;
	
	@InjectMocks
	private TicketServiceImpl ticketServiceImpl;
	
	@Test
	public void testTicketReservation_Called() {
		Long accountId = 10L;
		TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(Type.ADULT, (byte)12);
		
		doNothing().when(mockValidator).validateRequest(anyLong(), any());
		doNothing().when(mockTicketPaymentService).makePayment(anyLong(), anyInt());
		doNothing().when(mockSeatReservationService).reserveSeat(anyLong(), anyInt());
		when(mockParser.getTicketTypeRequestCount(any())).thenReturn(new TicketTypeRequestCount((byte)12, (byte)0, (byte)0));
		
		ticketServiceImpl.purchaseTickets(accountId, ticketTypeRequest);
		
		verify(mockValidator, times(1)).validateRequest(anyLong(), any());
		verify(mockTicketPaymentService, times(1)).makePayment(anyLong(), anyInt());
		verify(mockSeatReservationService, times(1)).reserveSeat(anyLong(), anyInt());
		verify(mockParser, times(1)).getTicketTypeRequestCount(any());
	}
}
