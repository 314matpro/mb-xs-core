package mb.xs.core;

@FunctionalInterface
public interface ExceptionalProcessor<I, O, E extends Exception> {
	public O process( I input ) throws E;
}
