package com.addr.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.addr.db.AddressDAO;
import com.addr.db.DBCon;

public class AddressDAOImpl2 implements AddressDAO {
	private static final String INSERT_ADDRESS = "insert into address"
			+ " values(seq_ad_num.nextval,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_ADDRESS = "select * from (select rownum as rown, "
			+ " addr.* from(select * from address order by ad_num) addr where rownum<=100) " + " where rown>=1";

	@Override
	public int insertAddressList(List<Map<String, String>> addrList) {
		int cnt = 0;
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(INSERT_ADDRESS);
			for (Map<String, String> addr : addrList) {
				ps.setString(1, addr.get("ad_code"));
				ps.setString(2, addr.get("ad_sido"));
				ps.setString(3, addr.get("ad_gugun"));
				ps.setString(4, addr.get("ad_dong"));
				ps.setString(5, addr.get("ad_lee"));
				ps.setString(6, addr.get("ad_bunji"));
				ps.setString(7, addr.get("ad_ho"));
				ps.setString(8, addr.get("ad_roadcode"));
				ps.setString(9, addr.get("ad_isbase"));
				ps.setString(10, addr.get("ad_orgnum"));
				ps.setString(11, addr.get("ad_subnum"));
				ps.setString(12, addr.get("ad_jinum"));
				ps.addBatch();
			}
			int[] resultCnts = ps.executeBatch();
			for (int resultCnt : resultCnts) {
				if (resultCnt == -2) {
					cnt++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public List<Map<String, String>> selectAddrList(List<Map<String, String>> addrList) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(SELECT_ADDRESS);
			ResultSet rs = ps.executeQuery();
			List<Map<String, String>> addressList = new ArrayList<>();
			while (rs.next()) {
				Map<String, String> address = new HashMap<>();
				address.put("ad_num", rs.getString("ad_num"));
				address.put("ad_sido", rs.getString("ad_sido"));
				address.put("ad_gugun", rs.getString("ad_gugun"));
				address.put("ad_dong", rs.getString("ad_dong"));
				address.put("ad_lee", rs.getString("ad_lee"));
				address.put("ad_bunji", rs.getString("ad_bunji"));
				address.put("ad_ho", rs.getString("ad_ho"));
				address.put("ad_roadcode", rs.getString("ad_roadcode"));
				address.put("ad_isbase", rs.getString("ad_roadcode"));
				address.put("ad_orgnum", rs.getString("ad_orgnum"));
				address.put("ad_subnum", rs.getString("ad_subnum"));
				address.put("ad_jinum", rs.getString("ad_jinum"));

				addressList.add(address);

			}
			return addressList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		AddressDAO adao = new AddressDAOImpl2();
		List<Map<String, String>> addrList = adao.selectAddrList(null);
		System.out.println(addrList);

	}
}
