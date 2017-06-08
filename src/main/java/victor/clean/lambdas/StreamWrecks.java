package victor.clean.lambdas;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

public class StreamWrecks {
	
	public Set<Product> getMissingProducts(List<Order> orders) {
		 List<OrderLine> orderLines = orders.stream()
			.filter(Order::isNotDelivered)
			.flatMap(order -> order.getOrderLines().stream())
			.collect(toList());
		
		return orderLines.stream()
			.filter(line -> !line.isInStock())
			.map(OrderLine::getProduct)
			.filter(Product::isNotHidden)
			.collect(toSet());
	}
	
	public List<Product> stuff2(List<Order> orders) {
		List<Long> excludedProductIds = getExcludedProductIds();
		
		Stream<Product> frequentProducts = countProducts(orders)
				.entrySet().stream()
				.filter(e -> e.getValue() >= 10)
				.map(Entry::getKey);
		
		return frequentProducts
				.filter(p->excludedProductIds.contains(p.getId()))
				.collect(toList());
	}

	private Map<Product, Integer> countProducts(List<Order> orders) {
		return orders.stream()
				.filter(o -> o.getCreationDate().isAfter(LocalDate.now().minusYears(1)))
				.flatMap(o -> o.getOrderLines().stream())
				.collect(groupingBy(OrderLine::getProduct, summingInt(OrderLine::getItemCount)));
	}

	private List<Long> getExcludedProductIds() {
		return Arrays.asList(1L);
	}
}

// -- dummy data model --
class Order {
	public boolean isNotDelivered() {return false;}
	public List<OrderLine> getOrderLines() {return null;}
	public LocalDate getCreationDate() {return null;}
}
class OrderLine {
	public Product getProduct() {return null;}
	public boolean isInStock() {return false;}
	public int getItemCount() {return 0;}
}
class Product {
	public boolean isNotHidden() {return false;}
	public Long getId() {return 0l;}
}
