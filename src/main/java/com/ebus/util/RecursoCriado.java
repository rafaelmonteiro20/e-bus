package com.ebus.util;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class RecursoCriado {

	private RecursoCriado() {

	}
	
	public static URI location(Integer id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(id).toUri();
	}
	
}
