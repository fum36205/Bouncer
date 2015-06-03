package de.ur.mi.bouncer.stacktrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StackTraceFilter {
	private final List<Class<?>> classFilter;

	public static StackTraceFilter forClasses(Class<?> ...classes) {
		return new StackTraceFilter(classes);
	}
	
	private StackTraceFilter(List<Class<?>> classFilter) {
		this.classFilter = classFilter;
	}

	private StackTraceFilter(Class<?>[] classes) {
		this(Arrays.asList(classes));
	}

	public StackTraceElement[] select(StackTraceElement[] stackTrace) {
		ArrayList<StackTraceElement> selected = new ArrayList<StackTraceElement>();
		for (StackTraceElement ste : stackTrace) {
			try {
				Class<?> c = Class.forName(ste.getClassName());
				if (matchesFilter(c)) {
					selected.add(ste);
				}
			} catch (ClassNotFoundException e) {
				return stackTrace;
			}
		}
		return selected.toArray(new StackTraceElement[selected.size()]);
	}

	private boolean matchesFilter(Class<?> cls) {
		for (Class<?> c : classFilter) {
			if (c.isAssignableFrom(cls)) {
				return true;
			}
		}
		return false;
	}
}
