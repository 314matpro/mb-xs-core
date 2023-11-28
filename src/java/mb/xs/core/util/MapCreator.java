package mb.xs.core.util;

import java.util.HashMap;
import java.util.Map;

import mb.xs.core.model.Model;

public class MapCreator<K, V> {
	private Model<K> keyType;
	private Model<V> valueType;
	private Map<K, V> map;

	public MapCreator( Model<K> keyType, Model<V> valueType ) {
		this( keyType, valueType, new HashMap<>() );
	}
	public MapCreator( Model<K> keyType, Model<V> valueType, Map<K, V> map ) {
		setKeyType( keyType );
		setValueType( valueType );
		set( map );
	}

	private void setKeyType( Model<K> keyType ) {
		this.keyType = keyType;
	}
	public Model<K> getKeyType() {
		return keyType;
	}

	private void setValueType( Model<V> valueType ) {
		this.valueType = valueType;
	}
	public Model<V> getValueType() {
		return valueType;
	}

	public MapCreator<K, V> with( K key, V value ) {
		map.put( key, value );
		return this;
	}
	public MapCreator<K, V> with( Map<K, V> values ) {
		values.forEach( ( key, value ) -> with( key, value ) );
		return this;
	}

	public void set( Map<K, V> map ) {
		this.map = map;
	}
	public Map<K, V> get() {
		return map;
	}

	public Map<K, V> create() {
		return new HashMap<>( map );
	}
	public MapCreator<K, V> copy() {
		return new MapCreator<>( keyType, valueType, create() );
	}
}
