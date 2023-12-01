package mb.xs.core.util;

public class SleepUtil {
	private SleepUtil() {
		/* Left intentionally blank */
	}

	public static void s( long seconds ) {
		ms( seconds * 1000 );
	}
	public static void ms( long milliseconds ) {
		try {
			Thread.sleep( milliseconds );
		} catch( InterruptedException e ) {
			Thread.currentThread().interrupt();
		}
	}
}
