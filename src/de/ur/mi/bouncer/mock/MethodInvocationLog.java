package de.ur.mi.bouncer.mock;

import java.util.ArrayList;

public class MethodInvocationLog {
	private final ArrayList<MethodInvocation> invocations;

	public MethodInvocationLog() {
		invocations = new ArrayList<MethodInvocation>();
	}

	public void addInvocation(String name, MethodParameter... parameters) {
		addInvocation(MethodInvocation.create(name, parameters));
	}

	public void addInvocation(String variableName, String methodName,
			MethodParameter... parameters) {
		addInvocation(MethodInvocation.create(variableName, methodName,
				parameters));
	}

	public void addInvocation(MethodInvocation mi) {
		invocations.add(mi);
	}

	public boolean contains(MethodInvocation methodInvocation,
			boolean ignoreParamValues) {
		return count(methodInvocation, ignoreParamValues) > 0;
	}

	public boolean contains(MethodInvocation methodInvocation,
			boolean ignoreParamValues, int count) {
		return count(methodInvocation, ignoreParamValues) == count;
	}

	public ArrayList<MethodInvocation> findInvocations(
			MethodInvocation methodInvocation) {
		ArrayList<MethodInvocation> results = new ArrayList<MethodInvocation>();
		for (MethodInvocation mi : invocations) {
			if (methodInvocation.equalsIgnoreValues(mi)) {
				results.add(mi);
			}
		}
		return results;
	}

	public int count(MethodInvocation methodInvocation,
			boolean ignoreParamValues) {
		int invocationCount = 0;
		for (MethodInvocation mi : invocations) {
			boolean found = false;
			if (ignoreParamValues) {
				found = mi.equalsIgnoreValues(methodInvocation);
			} else {
				found = mi.equals(methodInvocation);
			}
			if (found) {
				invocationCount++;
			}
		}
		return invocationCount;
	}

	public int totalCount() {
		return invocations.size();
	}

	public MethodInvocation invocationAt(int position) {
		if (invocations.isEmpty()) {
			return null;
		}
		if (position > invocations.size() - 1) {
			return null;
		}
		return this.invocations.get(position);
	}

	public void reset() {
		invocations.clear();
	}
}
