package mb.xs.core.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class SimpleParameterizedType implements ParameterizedType {
	private Type rawType;
	private Type ownerType;
	private Type[] actualTypeArguments;
	
	public SimpleParameterizedType( Type rawType, Type ownerType, Type... actualTypeArguments ) {
		if( rawType == null ) {
			throw new IllegalArgumentException( "Raw type cannot be null" );
		} else if( actualTypeArguments.length < 1 ) {
			throw new IllegalArgumentException( "Must supply at least one type argument" );
		}
		
		this.rawType = rawType;
		this.ownerType = ownerType;
		this.actualTypeArguments = actualTypeArguments;
	}

	@Override
	public Type[] getActualTypeArguments() {
		return actualTypeArguments;
	}
	@Override
	public Type getOwnerType() {
		return ownerType;
	}
	@Override
	public Type getRawType() {
		return rawType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder( ownerType + "." + rawType + "<" );
		
		boolean first = true;
		for( Type arg : actualTypeArguments ) {
			if( first ) {
				first = false;
			} else {
				builder.append( "," );
			}
			builder.append( arg );
		}
		
		builder.append( ">" );
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode( actualTypeArguments );
		result = prime * result + ( ( ownerType == null ) ? 0 : ownerType.hashCode() );
		result = prime * result + ( ( rawType == null ) ? 0 : rawType.hashCode() );
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
		if( !( obj instanceof ParameterizedType ) ) {
			return false;
		}
		ParameterizedType other = (ParameterizedType) obj;
		if( !Arrays.equals( actualTypeArguments, other.getActualTypeArguments() ) ) {
			return false;
		}
		if( ownerType == null ) {
			if( other.getOwnerType() != null ) {
				return false;
			}
		} else if( !ownerType.equals( other.getOwnerType() ) ) {
			return false;
		}
		if( rawType == null ) {
			if( other.getRawType() != null ) {
				return false;
			}
		} else if( !rawType.equals( other.getRawType() ) ) {
			return false;
		}
		return true;
	}
}
