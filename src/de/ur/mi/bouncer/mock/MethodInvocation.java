package de.ur.mi.bouncer.mock;

public class MethodInvocation {
	private final String variableName;
	private final String methodName;
	private final MethodParameterList parameters;

	public static MethodInvocation create(String name, MethodParameter... parameters) {
		return new MethodInvocation("", name, parameters);
	}
	
	public static MethodInvocation create(String variableName, String methodName, MethodParameter... parameters) {
		return new MethodInvocation(variableName, methodName, parameters);
	}

	private MethodInvocation(String variableName, String name, MethodParameter... parameters) {
		this.variableName = variableName;
		this.methodName = name;
		this.parameters = new MethodParameterList(parameters);
	}

	public boolean equals(MethodInvocation other) {
		if (!methodName.equals(other.methodName)) {
			return false;
		}
		if (!parameters.equals(other.parameters)) {
			return false;
		}
		return true;
	}

	public boolean equalsIgnoreValues(MethodInvocation other) {
		if (!methodName.equals(other.methodName)) {
			return false;
		}
		if (!parameters.equalsIgnoreValues(other.parameters)) {
			return false;
		}
		return true;
	}
	
	public String toString(boolean ignoreValues) {
		String result = "";
		if (!this.variableName.isEmpty()) {
			result += this.variableName + ".";
		}
		result += this.methodName + "(";
		result += parameters.toString(ignoreValues);
		result += ")";
		return result;
	}
	
	@Override
	public String toString() {
		return this.toString(false);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MethodInvocation)) {
			return false;
		}
		MethodInvocation other = (MethodInvocation)obj;
		if (!methodName.equals(other.methodName)) {
			return false;
		}
		if (!parameters.equals(other.parameters)) {
			return false;
		}
		return true;
	}
	
	public Object valueOfParameterAt(int index) {
		return parameters.get(index).value();
	}
}
