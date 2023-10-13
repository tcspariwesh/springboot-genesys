package com.example.demo.mockito.argument.captor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class TestStatic {
	@Test
	void givenStaticMethodWithNoArgs_whenMocked_thenReturnsMockSuccessfully() {
	    assertThat(StaticUtils.name()).isEqualTo("Testing ");

	    try (MockedStatic<StaticUtils> utilities = Mockito.mockStatic(StaticUtils.class)) {
	        utilities.when(StaticUtils::name).thenReturn("Pariwesh");
	        assertThat(StaticUtils.name()).isEqualTo("Pariwesh");
	    }

	    assertThat(StaticUtils.name()).isEqualTo("Testing ");
	}
}
