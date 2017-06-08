package victor.clean.lambdas;

import static java.util.stream.Collectors.toList;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("unused")
public class NameYourLambdas {

	//@Autowired
	private ARepo aRepo;
	
	//@Autowired
	private ABMapper mapper;
	
	private List<B> getAllA() {
		return aRepo.findAll().stream().map(mapper::convertAToB).collect(toList());
	}

}
class ABMapper {
	public B convertAToB(A a) {
		B b = new B();
		b.setFirstNameB(a.getFirstNameA());
		b.setLastNameB(a.getLastNameA());
		return b;
	}
}

// -------- fake code ---------
interface ARepo {
	List<A> findAll(); 
}

class A {
	@Getter @Setter private String firstNameA;
	@Getter @Setter private String lastNameA;
}
class B {
	@Getter @Setter private String firstNameB;
	@Getter @Setter private String lastNameB;
}
