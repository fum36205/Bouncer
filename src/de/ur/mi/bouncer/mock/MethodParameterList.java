package de.ur.mi.bouncer.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MethodParameterList implements Iterable<MethodParameter> {

	private final ArrayList<MethodParameter> parameters;

	public MethodParameterList(MethodParameter... parameters) {
		this.parameters = new ArrayList<MethodParameter>();
		this.parameters.addAll(Arrays.asList(parameters));
	}

	public boolean equals(MethodParameterList params) {
		if (parameters.size() != params.size()) {
			return false;
		}
		for (int i = 0; i < parameters.size(); i++) {
			if (!parameters.get(i).equals(params.get(i))) {
				return false;
			}
		}
		return true;
	}

	public boolean equalsIgnoreValues(MethodParameterList params) {
		if (parameters.size() != params.size()) {
			return false;
		}
		for (int i = 0; i < parameters.size(); i++) {
			if (!parameters.get(i).equalsIgnoreValues(params.get(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Iterator<MethodParameter> iterator() {
		return parameters.iterator();
	}

	public int size() {
		return parameters.size();
	}

	public MethodParameter get(int index) {
		return parameters.get(index);
	}

	public String toString(boolean ignoreValues) {
		String result = "";
		for (int i = 0; i < parameters.size() - 1; i++) {
			result += parameters.get(i).toString(ignoreValues) + ", ";
		}
		if (parameters.size() > 0) {
			result += parameters.get(parameters.size() - 1).toString(
					ignoreValues);
		}
		return result;
	}
}
