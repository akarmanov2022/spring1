package ru.gb.akarmanov.models;

import java.text.DecimalFormat;
import java.util.UUID;

public class Product {
  private UUID id;
  private String title;
  private Double coast;

  public Product(String title, Double coast) {
    this.title = title;
    this.coast = coast;
  }

  public Product() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Double getCoast() {
    return coast;
  }

  public void setCoast(Double coast) {
    this.coast = coast;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", coast=" + DecimalFormat.getInstance().format(coast) +
        '}';
  }
}
