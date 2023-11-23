package mb.xs.core.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelType<T> implements Model<T> {
	private Type generic;
	private Class<T> expected;
	private Class<? extends T> actual;
	private List<Model<?>> genericTypeArguments;

	public ModelType( Class<T> expected ) {
		this( expected, expected );
	}
	public ModelType( Type generic, Class<T> expected ) {
		this.generic = generic;
		this.expected = expected;
		this.actual = getActualFromType( generic, expected );
	}

	@Override
	public Type getGeneric() {
		return generic;
	}
	@Override
	public Class<T> getExpected() {
		return expected;
	}
	@Override
	public Class<? extends T> getActual() {
		return actual;
	}

	@Override
	public synchronized List<Model<?>> getGenericTypeArguments() {
		if( genericTypeArguments == null ) {
			genericTypeArguments = new ArrayList<>();

			if( generic instanceof ParameterizedType ) {
				for( Type genericArgument : ( (ParameterizedType) generic ).getActualTypeArguments() ) {
					genericTypeArguments.add( new ModelType<>( genericArgument, Object.class ) );
				}
			}
		}

		return genericTypeArguments;
	}
	@Override
	public Model<?> getGenericTypeArgument( int index ) {
		return getGenericTypeArgument( index, Types.OBJECT_MODEL );
	}
	@Override
	public Model<?> getGenericTypeArgument( int index, Model<?> defaultType ) {
		List<Model<?>> genericArguments = getGenericTypeArguments();
		return genericArguments.size() > index ? genericArguments.get( index ) : defaultType;
	}
	@Override
	public int getTypeArgumentCount() {
		return getGenericTypeArguments().size();
	}

	@Override
	@SuppressWarnings( "unchecked" )
	public T cast( Object instance ) throws ClassCastException {
		if( instance == null || actual.isInstance( instance ) ) {
			return (T) instance;
		}
		throw new ClassCastException( "Cannot cast " + instance + " ("
				+ ( instance != null
						? instance.getClass().getName() + " -> "
								+ Arrays.asList( instance.getClass().getGenericInterfaces() )
						: "null" )
				+ ") to " + generic );
	}
	@Override
	@SuppressWarnings( "unchecked" )
	public <C extends T> Class<C> castClass( Class<?> type ) {
		if( actual.isAssignableFrom( type ) ) {
			return (Class<C>) type;
		}
		throw new ClassCastException( "Cannot cast from " + type + " to " + actual );
	}
	@Override
	public ModelType<? extends T> castType( Class<?> type ) {
		return new ModelType<>( castClass( type ) );
	}

	@SuppressWarnings( "unchecked" )
	public static <T> Class<? extends T> getActualFromType( Type generic, Class<T> expected ) {
		Class<?> rawActual = Types.getRawClass( generic );

		if( expected.isAssignableFrom( rawActual ) ) {
			return (Class<? extends T>) rawActual;
		} else {
			throw new IllegalArgumentException( "Generic type " + generic + " is not assignable to " + expected );
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( generic == null ) ? 0 : generic.hashCode() );
		return result;
	}
	@Override
	public boolean equals( Object obj ) {
		if( this == obj ) {
			return true;
		}
		if( obj == null ) {
			return false;
		}
		if( !( obj instanceof Model ) ) {
			return false;
		}
		Model<?> other = (Model<?>) obj;
		if( generic == null ) {
			if( other.getGeneric() != null ) {
				return false;
			}
		} else if( !generic.equals( other.getGeneric() ) ) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "ModelType [" + generic + "]";
	}
}
