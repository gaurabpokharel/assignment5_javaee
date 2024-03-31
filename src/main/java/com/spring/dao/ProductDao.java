package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.beans.Product;

public class ProductDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int save(Product p) {
		String sql = "insert into product(name,price,quantity) values('" + p.getName() + "'," + p.getPrice() + ",'"
				+ p.getQuantity() + "')";
		return template.update(sql);
	}

	public int deleteproduct(int id) {
		String sql = "delete from product where id=" + id + "";
		try {
			return template.update(sql);
		} catch (EmptyResultDataAccessException ex) {
			// Handle empty result, perhaps return null or throw a custom exception
			return 0; // Or throw a custom exception
		}
	}

	public Product getProductById(int id) {
		String sql = "select * from product where id=?";
		try {
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Product>(Product.class));
		}
		catch (EmptyResultDataAccessException ex) {
			// Handle empty result, perhaps return null or throw a custom exception
			return null; // Or throw a custom exception
		}
	}

	public Product getProductByName(String name) {
		String sql = "select * from product where name=?";
		try {
			return template.queryForObject(sql, new Object[] { name },
					new BeanPropertyRowMapper<Product>(Product.class));
		} catch (EmptyResultDataAccessException ex) {
			// Handle empty result, perhaps return null or throw a custom exception
			return null; // Or throw a custom exception
		}
	}

	public int updateProduct(Product p) {
		String sql = "update product set name='" + p.getName() + "', price=" + p.getPrice() + ",quantity='"
				+ p.getQuantity() + "' where id=" + p.getId() + "";
		try {
			return template.update(sql);
		} catch (EmptyResultDataAccessException ex) {
			// Handle empty result, perhaps return null or throw a custom exception
			return 0; // Or throw a custom exception
		}
	}

	public List<Product> getProduct() {
		return template.query("select * from product", new RowMapper<Product>() {
			public Product mapRow(ResultSet rs, int row) throws SQLException {
				Product e = new Product();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPrice(rs.getFloat(3));
				e.setQuantity(rs.getInt(4));
				return e;
			}
		});
	}
}
