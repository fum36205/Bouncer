package de.ur.mi.bouncer.error;

import java.io.PrintStream;
import java.util.ArrayList;

import de.ur.mi.bouncer.apps.WorldBouncerApp;

public class BouncerError extends RuntimeException {
	private static final long serialVersionUID = 2857549683108543041L;

	public BouncerError(String message) {
		super(message);
	}

	@Override
	public void printStackTrace(PrintStream s) {
		synchronized (s) {
			s.println(this);
			StackTraceElement[] trace = getStackTrace();
			for (int i = 0; i < trace.length; i++) {
				s.println("\tat " + trace[i]);
			}
		}
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		try {
			return removeUntilBouncerApp(super.getStackTrace());
		} catch (ClassNotFoundException e) {
			return super.getStackTrace();
		}
	}

	private static StackTraceElement[] removeUntilBouncerApp(
			StackTraceElement[] stackTrace) throws ClassNotFoundException {
		boolean includeFromNow = false;
		ArrayList<StackTraceElement> filtered = new ArrayList<StackTraceElement>();
		for (StackTraceElement ste : stackTrace) {
			Class<?> c = Class.forName(ste.getClassName());
			if (WorldBouncerApp.class.isAssignableFrom(c)) {
				includeFromNow = true;
			}
			if (includeFromNow) {
				filtered.add(ste);
			}
		}
		return (StackTraceElement[]) filtered
				.toArray(new StackTraceElement[filtered.size()]);
	}
}
