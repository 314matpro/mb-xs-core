package mb.xs.core.model;

import java.util.function.Supplier;

import mb.xs.core.Factory;

public interface Template<T> extends Supplier<T>, Factory<T> {
	public Model<T> getSpecification();
	public Model<? extends T> getInstanceType();

	public boolean isPopulated();

	@Override
	default T get() {
		return create();
	}
}
