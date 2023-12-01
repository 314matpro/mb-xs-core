package mb.xs.core.log;

import java.io.OutputStream;

public interface LogFactory {
	public Log create( String name, Level initialLevel, OutputStream stream );
}
