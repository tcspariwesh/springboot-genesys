package com.example.demo.mockito.argument.captor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

public class TestMockitoArgumentCaptor {

	@Captor
	ArgumentCaptor acLong;

	@Test
	void test() {
		MathUtils mockMathUtils = mock(MathUtils.class);
		when(mockMathUtils.add(1, 1)).thenReturn(2);
		when(mockMathUtils.isInteger(anyString())).thenReturn(true);

		ArgumentCaptor<Integer> acInteger = ArgumentCaptor.forClass(Integer.class);
		ArgumentCaptor<String> acString = ArgumentCaptor.forClass(String.class);

		assertEquals(2, mockMathUtils.add(1, 1));
		assertTrue(mockMathUtils.isInteger("1"));
		assertTrue(mockMathUtils.isInteger("999"));

		verify(mockMathUtils).add(acInteger.capture(), acInteger.capture());
		List<Integer> allValues = acInteger.getAllValues();
		assertEquals(List.of(1, 1), allValues);
		verify(mockMathUtils, times(2)).isInteger(acString.capture());
		List<String> allStringValues = acString.getAllValues();
		assertEquals(List.of("1", "999"), allStringValues);
	}
}