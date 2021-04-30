package com.glide.qa.TestListeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.glide.qa.Utilities.TestUtils;

public class MyTransformer implements IAnnotationTransformer
{

	int count=0;
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {


		try {
			if(count==0) {
				TestUtils.getRunStatus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for(int i=0;i<TestUtils.testCases.size();i++) {
			if(testMethod.getName().equalsIgnoreCase(TestUtils.testCases.get(i)))
			{	
				System.out.println("The listener is activated for:-" + testMethod.getName());
				annotation.setPriority(Integer.parseInt(TestUtils.priority.get(i)));					//sets the priority for all the test cases based on the excel sheet input
				annotation.setDescription(TestUtils.testDescription.get(i)); 							//sets the description for all the test cases based on the excel sheet input
				annotation.setInvocationCount(Integer.parseInt(TestUtils.invocationCount.get(i)));		//sets the invocation count for all the test cases based on the excel sheet input
				if(TestUtils.runStatus.get(i).equalsIgnoreCase("No")) 
				{
					annotation.setEnabled(false);														//sets the enabled parameter for all the test cases based on the excel sheet input
					break;
				}
			} 
		}

		count++;
		System.out.println("TestCase Number is:"+count);
	}
}


