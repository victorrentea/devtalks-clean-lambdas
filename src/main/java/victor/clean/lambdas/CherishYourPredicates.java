package victor.clean.lambdas;

import static java.util.stream.Collectors.toSet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import lombok.Getter;
import lombok.Setter;

public class CherishYourPredicates {

	public void notifyCustomersOfIncompleteOrders(List<Order> orders) {
		LocalDate warningDate = LocalDate.now().plusDays(3);
		
		Set<Customer> customersToNotify = orders.stream()
				.filter(OrderPredicate.hasDeliveryDueDateBefore(warningDate).or(Order::isConfidential))
				.filter(this::hasOrderLinesNotInStock)
				.map(o -> {return o.getCustomer();}).collect(toSet());
	
		for (Customer customer : customersToNotify) {
			sendEmail(customer);
		}
	}

	static class OrderPredicate {
		public static Predicate<Order> hasDeliveryDueDateBefore(LocalDate date) {
			return order -> order.getDeliveryDueDate().isBefore(date);
		}
	}

	private boolean hasOrderLinesNotInStock(Order order) {
		return order.getOrderLines().stream().anyMatch(OrderLine::isNotInStock);
	}

	private void sendEmail(Customer customer) { /* stuff */ }

	
	public static class Order {
		@Getter @Setter private Customer customer;
		@Getter @Setter private LocalDate deliveryDueDate;
		@Getter private List<OrderLine> orderLines = new ArrayList<>();
		@Getter @Setter private boolean confidential;
	}
	
	public static class OrderLine {
		public enum Status {
			IN_STOCK, AT_PROVIDER, UNAVAILABLE
		}
		@Getter @Setter private Status status;
		public boolean isNotInStock() {
			return status != Status.IN_STOCK;
		}
	}

	public static class Customer {
	}
	
}

