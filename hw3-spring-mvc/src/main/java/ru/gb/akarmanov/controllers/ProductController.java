package ru.gb.akarmanov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.akarmanov.dao.ProductRepository;
import ru.gb.akarmanov.models.Product;

import java.util.UUID;

@Controller
@RequestMapping("products")
public class ProductController {
  private final ProductRepository productRepository;

  public ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @GetMapping
  public String products(Model model) {
    model.addAttribute("products", productRepository.findAll());
    return "products";
  }

  @GetMapping("{id}")
  public String productForm(@PathVariable UUID id, Model model) {
    model.addAttribute("product", productRepository.findById(id));
    return "product_form";
  }

  @GetMapping("add")
  public String addProduct(Model model) {
    return "product_form";
  }

  @PostMapping
  public String updateProduct(Product product) {
    productRepository.update(product);
    return "redirect:/products";
  }

  @DeleteMapping("{id}")
  public String deleteProduct(@PathVariable UUID id) {
    productRepository.delete(id);
    return "redirect:/products";
  }
}
