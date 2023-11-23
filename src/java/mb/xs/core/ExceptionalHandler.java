package mb.xs.core;

public interface ExceptionalHandler<I, E extends Exception> {
	public void handle( I input ) throws E; 
}
