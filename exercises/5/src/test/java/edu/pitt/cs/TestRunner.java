package edu.pitt.cs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	/**
	 * Main method.
	 *
	 * @param args IGNORED, kept for compatibility
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		if (args.length > 0 && args[0].equals("buggy")) {
			Config.setBuggy(true);
			System.out.println("TESTING BUGGY IMPLEMENTATION\n");
		}

		ArrayList<Class> classesToTest = new ArrayList<Class>();

		// ADD ANY CLASSES YOU WISH TO TEST HERE
		classesToTest.add(DrunkCarnivalShooterTest.class);

		// For all test classes added, loop through and use JUnit
		// to run them.

		for (Class c : classesToTest) {
			Object testObject = c.newInstance();
			assert testObject != null;
			for (Method method : c.getMethods()) {
				if (method.isAnnotationPresent(Before.class)) {
					// do something
					method.invoke(testObject);
				}
			}
			for (Method method : c.getMethods()) {
				if (method.isAnnotationPresent(Test.class)) {
					// do something
					method.invoke(testObject);
				}
			}
			for (Method method : c.getMethods()) {
				if (method.isAnnotationPresent(After.class)) {
					// do something
					method.invoke(testObject);
				}
			}
		}
	}
}
