package uk.gov.dwp.uc.pairtest.utilities;

public class TicketTypeEnum {

	public enum Type {
		ADULT((byte) 20), CHILD((byte) 10), INFANT((byte) 0);

		private final byte price;

		Type(byte price) {
			this.price = price;
		}

		public byte getPrice() {
			return this.price;
		}
	}
}
