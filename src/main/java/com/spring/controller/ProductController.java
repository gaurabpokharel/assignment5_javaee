package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.beans.Emp;
import com.spring.beans.Product;
import com.spring.dao.ProductDao;

@Controller
public class ProductController {

	@Autowired
	ProductDao productDao;

	@RequestMapping("/productform")
	public String showform(Model m) {
		m.addAttribute("command", new Product()); // addAttributes() method add values in the Model that'll be
													// identified globally.
		return "productform";
	}

	@RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
	public String save(@ModelAttribute("product") Product product, Model model) {
		Product existingProduct = productDao.getProductByName(product.getName()); // Assuming dao has a method to get a
																					// // product by its ID

		if (product.getQuantity() <= 0) {
			productDao.deleteproduct(product.getId());
			model.addAttribute("errorMessage", "Product '" + product.getName() + "' removed from the list.");
		} else {
			if (existingProduct != null) {
				existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
				productDao.updateProduct(existingProduct); // Assuming dao has a method to update the product
			} else {
				// Product doesn't exist, add it to the database
				productDao.save(product); // Assuming dao has a method to save a new product
			}
		}
		return "redirect:/viewproduct";// will redirect to view product request mapping
	}

	@RequestMapping("/viewproduct")
	public String viewproduct(Model m) {
		List<Product> list = productDao.getProduct();
		m.addAttribute("list", list);
		return "viewproduct";
	}

	@RequestMapping(value = "/editproduct/{id}")
	public String editproduct(@PathVariable int id, Model m) {
		Product p = productDao.getProductById(id);
		if (p != null) {
			m.addAttribute("command", p);
			return "producteditform";
		} else {
			return "Error";
		}
	}

	/* It updates model object. */
	@RequestMapping(value = "/editsaveproduct", method = RequestMethod.POST)
	public String editsaveproduct(@ModelAttribute("product") Product product) {
		if (product.getQuantity() <= 0) {
			productDao.deleteproduct(product.getId());
		} else {
			productDao.updateProduct(product);
		}
		return "redirect:/viewproduct";
	}

	@RequestMapping(value = "/deleteproduct/{id}", method = RequestMethod.GET)
	public String deleteproduct(@PathVariable int id) {
		int value = productDao.deleteproduct(id);
		if (value > 0) {
			return "redirect:/viewproduct";
		} else {
			return "Error";
		}
	}
}
