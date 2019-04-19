package com.addr.db;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.addr.db.impl.AddressDAOImpl2;

public class selectTest {
	AddressDAO adao = new AddressDAOImpl2();

	@Test
	public void SelectTest() {
		List<Map<String, String>> addrList = adao.selectAddrList(null);
		assertEquals(100, addrList.size());

	}
	

}
