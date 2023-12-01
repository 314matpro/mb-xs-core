package mb.xs.core.model;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.*;

public class Types {
	public static final Model<Object> OBJECT_MODEL = new ModelType<>( Object.class );
	public static final Model<String> STRING_MODEL = new ModelType<>( String.class );

	public static final Model<Boolean> BOOLEAN_MODEL = new ModelType<>( Boolean.class );
	public static final Model<Byte> BYTE_MODEL = new ModelType<>( Byte.class );
	public static final Model<Short> SHORT_MODEL = new ModelType<>( Short.class );
	public static final Model<Integer> INTEGER_MODEL = new ModelType<>( Integer.class );
	public static final Model<Long> LONG_MODEL = new ModelType<>( Long.class );
	public static final Model<Float> FLOAT_MODEL = new ModelType<>( Float.class );
	public static final Model<Double> DOUBLE_MODEL = new ModelType<>( Double.class );

	public static final Model<Boolean> PRIMITIVE_BOOLEAN_MODEL = new ModelType<>( boolean.class );
	public static final Model<Byte> PRIMITIVE_BYTE_MODEL = new ModelType<>( byte.class );
	public static final Model<Short> PRIMITIVE_SHORT_MODEL = new ModelType<>( short.class );
	public static final Model<Integer> PRIMITIVE_INT_MODEL = new ModelType<>( int.class );
	public static final Model<Long> PRIMITIVE_LONG_MODEL = new ModelType<>( long.class );
	public static final Model<Float> PRIMITIVE_FLOAT_MODEL = new ModelType<>( float.class );
	public static final Model<Double> PRIMITIVE_DOUBLE_MODEL = new ModelType<>( double.class );

	public static final Model<File> FILE_MODEL = new ModelType<>( File.class );

	private Types() {
		/* Left intentionally blank */
	}

	public static Class<?> getRawClass( Type generic ) {
		if( generic instanceof Class ) {
			return (Class<?>) generic;
		} else if( generic instanceof ParameterizedType ) {
			return getRawClass( ( (ParameterizedType) generic ).getRawType() );
		} else if( generic instanceof TypeVariable ) {
			return Object.class;
		} else if( generic instanceof WildcardType ) {
			return Object.class;
		} else {
			throw new UnsupportedOperationException( "Failed to get raw type of: " + generic );
		}
	}

	public static ParameterizedType createGenericType( Type rawType, Type... actualTypeArguments ) {
		return new SimpleParameterizedType( rawType, null, actualTypeArguments );
	}
	public static ParameterizedType createGenericSubType( Type rawType, Type ownerType, Type... actualTypeArguments ) {
		return new SimpleParameterizedType( rawType, ownerType, actualTypeArguments );
	}

	@SuppressWarnings( "unchecked" )
	public static <T> Model<Collection<T>> createModelCollection( Model<T> type ) {
		return (Model<Collection<T>>) (Model<?>) new ModelType<>(
				createGenericType( Collection.class, type.getGeneric() ), Collection.class );
	}
	@SuppressWarnings( "unchecked" )
	public static <T> Model<List<T>> createModelList( Model<T> type ) {
		return (Model<List<T>>) (Model<?>) new ModelType<>( createGenericType( List.class, type.getGeneric() ),
				List.class );
	}
	@SuppressWarnings( "unchecked" )
	public static <T> Model<Set<T>> createModelSet( Model<T> type ) {
		return (Model<Set<T>>) (Model<?>) new ModelType<>( createGenericType( Set.class, type.getGeneric() ),
				Set.class );
	}
	@SuppressWarnings( "unchecked" )
	public static <K, V> Model<Map<K, V>> createModelMap( Model<K> keyType, Model<V> valueType ) {
		return (Model<Map<K, V>>) (Model<?>) new ModelType<>(
				createGenericType( Map.class, keyType.getGeneric(), valueType.getGeneric() ), Map.class );
	}
}
