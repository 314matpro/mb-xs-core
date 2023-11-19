package mb.xs.core;

@FunctionalInterface
public interface ExceptionalRunnable<E extends Exception> {
	public void run() throws E;
}
