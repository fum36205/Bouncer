package de.ur.mi.bouncer.mock.matcher;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import de.ur.mi.bouncer.mock.MethodInvocation;

public class IsInvocationWithParameters extends TypeSafeMatcher<MethodInvocation>{
	private final MethodInvocation invocation;
	
	public IsInvocationWithParameters(MethodInvocation invocation) {
		this.invocation = invocation;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(this.invocation.toString(false));
	}

	@Override
	protected boolean matchesSafely(MethodInvocation invocation) {
		return (this.invocation.equals(invocation));
	}

	@Override
	protected void describeMismatchSafely(MethodInvocation item,
			Description mismatchDescription) {
		mismatchDescription.appendText("was: ");
		mismatchDescription.appendText(item.toString(false));
	}
}
