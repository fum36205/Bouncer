package de.ur.mi.bouncer.error;

import java.io.PrintStream;

import de.ur.mi.bouncer.stacktrace.StackTraceFilter;

public class BouncerError extends RuntimeException {
	private static final long serialVersionUID = 2857549683108543041L;
	private final StackTraceFilter stackTraceFilter;

	public BouncerError(String message, StackTraceFilter stackTraceFilter) {
		super(message);
		this.stackTraceFilter = stackTraceFilter;
	}

	@Override
	public void printStackTrace(PrintStream s) {
		synchronized (s) {
//			s.println(this);
			StackTraceElement[] trace = getStackTrace();
			for (int i = 0; i < trace.length; i++) {
				s.println("\tat " + trace[i]);
			}
		}
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return stackTraceFilter.select(super.getStackTrace());
	}
}
