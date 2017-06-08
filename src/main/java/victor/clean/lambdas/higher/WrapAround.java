package victor.clean.lambdas.higher;

public class WrapAround {
	public static void main(String[] args) {
		executeInTx(WrapAround::insertLanguages);
		executeInTx(WrapAround::insertCountries);
	}
	
	public static void countTime(Runnable r) {
		long t0 = System.nanoTime();
		r.run();
		long t1 = System.nanoTime();
		System.out.println("A durat " + (t1-t0));
	}
	
	public static void executeInTx(Runnable r) {
		try {
			System.out.println("Start Tx");
			r.run();
			System.out.println("Commit Tx");
		} catch (RuntimeException e) {
			System.out.println("Rollback Tx");
		} finally {
			System.out.println("Release Tx res");
		}
	}
	
	// System.nanoTime();

	// existing
	public static void insertLanguages() {
		System.out.println("Insert languages");
	}

	public static void insertCountries() {
		System.out.println("Insert countries");
	}
}
