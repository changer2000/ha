package com.etech.assertthat;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({JUnit4AssertTest.class, JUnitExceptionTest.class})
public class JUnitSuiteTest {

}
