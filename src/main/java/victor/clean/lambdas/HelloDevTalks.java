package victor.clean.lambdas;

import java.util.ArrayList;
import java.util.List;

public class HelloDevTalks {

	public static void main(String[] args) {
		List<Order> orders = new ArrayList<>();
		
		 boolean found = orders.stream()
			 .anyMatch(o -> o.isNotDelivered());
		
//		 boolean found = orders.stream()
//			 .filter(o -> o.isNotDelivered())
//			 .findAny()
//			 .isPresent();
//		
//		 boolean found = orders.stream()
//			 .filter(o -> o.isNotDelivered())
//			 .count() >= 1;
//		
//		 boolean found = !orders.stream()
//			 .filter(o -> o.isNotDelivered())
//			 .collect(toList())
//			 .isEmpty();
//		
		// boolean found = orders.stream()
		// .filter(o -> o.isNotDelivered())
		// .collect(toList())
		// .size() >= 1;
		//
	}
}
