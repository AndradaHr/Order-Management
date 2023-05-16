package org.example.model;

import java.time.LocalDate;

public record Bill(int id, int orderId, double total, LocalDate date) {

}
