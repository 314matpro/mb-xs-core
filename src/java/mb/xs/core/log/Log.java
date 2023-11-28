package mb.xs.core.log;

public interface Log {
	public void log( Level level, Throwable throwable, Object... message );
	public void log( Level level, byte[] data, int offset, int length );
	public void logStackTrace( Level level );

	public void trace( Object... message );
	public void trace( Throwable throwable );
	public void trace( String message, Throwable throwable );

	public void debug( Object... message );
	public void debug( Throwable throwable );
	public void debug( String message, Throwable throwable );

	public void info( Object... message );
	public void info( Throwable throwable );
	public void info( String message, Throwable throwable );

	public void status( Object... message );
	public void status( Throwable throwable );
	public void status( String message, Throwable throwable );

	public void warn( Object... message );
	public void warn( Throwable throwable );
	public void warn( String message, Throwable throwable );

	public void error( Object... message );
	public void error( Throwable throwable );
	public void error( String message, Throwable throwable );

	public boolean isEnabled( Level level );

	public boolean isTraceEnabled();
	public boolean isDebugEnabled();
	public boolean isInfoEnabled();
	public boolean isStatusEnabled();
	public boolean isWarnEnabled();
	public boolean isErrorEnabled();
}
