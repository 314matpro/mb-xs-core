package mb.xs.core.model;

import java.lang.reflect.Type;
import java.util.List;

public interface Model<T> {
	public Type getGeneric();
	public Class<T> getExpected();
	public Class<? extends T> getActual();
	
	public List<Model<?>> getGenericTypeArguments();
	public Model<?> getGenericTypeArgument( int index );
	public Model<?> getGenericTypeArgument( int index, Model<?> defaultType );
	public int getTypeArgumentCount();
	
	public T cast( Object instance ) throws ClassCastException;
	public <C extends T> Class<C> castClass( Class<?> type );
	public Model<? extends T> castType( Class<?> type );
}
