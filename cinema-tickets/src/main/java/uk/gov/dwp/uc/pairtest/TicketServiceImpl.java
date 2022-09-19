package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestCount;
import uk.gov.dwp.uc.pairtest.exception.GlobalExceptionHandler;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.helper.Parser;
import uk.gov.dwp.uc.pairtest.utilities.TicketTypeEnum.Type;
import uk.gov.dwp.uc.pairtest.validation.Validator;

public class TicketServiceImpl implements TicketService {

	/**
	 * Should only have private methods other than the one below.
	 */

	private Validator validator;
	private TicketPaymentService ticketPaymentService;
	private SeatReservationService seatReservationService;
	private Parser parser;

	public TicketServiceImpl(Validator validator, TicketPaymentService ticketPaymentService,
			SeatReservationService seatReservationService, Parser parser) {
		this.validator = validator;
		this.seatReservationService = seatReservationService;
		this.ticketPaymentService = ticketPaymentService;
		this.parser = parser;
	}

	@Override
	public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests)
			throws InvalidPurchaseException {

		Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler());

		TicketTypeRequestCount ticketTypeRequestCount = parser.getTicketTypeRequestCount(ticketTypeRequests);

		// Validate the request and throw error in case of validation fails
		validator.validateRequest(accountId, ticketTypeRequestCount);

		// Calculate price and total seats for the request
		byte totalSeats = (byte) (ticketTypeRequestCount.getNo_Of_Adults()
				+ ticketTypeRequestCount.getNo_Of_Children());
		int totalPrice = (ticketTypeRequestCount.getNo_Of_Adults() * Type.ADULT.getPrice())
				+ (ticketTypeRequestCount.getNo_Of_Children() * Type.CHILD.getPrice());

		// Do the payment and reserve seat(s)
		ticketPaymentService.makePayment(accountId, totalPrice);
		seatReservationService.reserveSeat(accountId, totalSeats);
	}
}
