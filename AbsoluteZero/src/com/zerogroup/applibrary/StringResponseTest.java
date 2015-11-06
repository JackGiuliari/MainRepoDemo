package com.zerogroup.applibrary;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringResponseTest {

	@Test
	public void testFindResponse() {
		
		String testString = "white Riot";
		StringResponse responder = new StringResponse(testString);
		responder.addResponse("WhITe", "RIOT!");
		Object[] responses = responder.findResponse();
		String testRight = "RIOT!";
		
		assertEquals(testRight, responses[0]);
		
		
	}

}
