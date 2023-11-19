package mb.xs.core;

@FunctionalInterface
public interface Factory<T> {
	public T create();
}
