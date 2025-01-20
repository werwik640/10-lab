package org.example;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
                new Order(1, "Иван", LocalDate.of(2024, 12, 1), "PENDING", 300),
                new Order(2, "Мария", LocalDate.of(2024, 12, 5), "SHIPPED", 600),
                new Order(3, "Алексей", LocalDate.of(2024, 12, 10), "DELIVERED", 800),
                new Order(4, "Ольга", LocalDate.of(2024, 12, 15), "SHIPPED", 200),
                new Order(5, "Екатерина", LocalDate.of(2024, 12, 20), "PENDING", 700)
        );

        System.out.println("Сортировка по дате:");
        List<Order> filteredOrderByDate = orders.stream()
                .distinct()
                .filter(n -> n.getOrderDate().isAfter(LocalDate.of(2024, 12, 6)))
                .collect(Collectors.toList());

        for (Order order : filteredOrderByDate) {
            System.out.println(order.toString());
        }
        System.out.println();

        System.out.println("Сортировка по статуту:");
        List<Order> filteredOrderByStatus = orders.stream()
                .distinct()
                .filter(n -> n.getStatus() == "SHIPPED")
                .collect(Collectors.toList());

        for (Order order : filteredOrderByStatus) {
            System.out.println(order.toString());
        }
        System.out.println();

        Map<String, Long> statusCounts = orders.stream()
                .distinct()
                .collect(Collectors.groupingBy(n -> n.getStatus(), Collectors.counting()));
        Long shippedCount = statusCounts.getOrDefault("SHIPPED", 0L);
        Long pendingCount = statusCounts.getOrDefault("PENDING", 0L);
        Long deliveredCount = statusCounts.getOrDefault("DELIVERED", 0L);

        System.out.println("Заказов со статусом \"SHIPPED\": " + shippedCount);
        System.out.println("Заказов со статусом \"PENDING\": " + pendingCount);
        System.out.println("Заказов со статусом \"DELIVERED\": " + deliveredCount);
        System.out.println();

        System.out.println("Сортировка по сумме заказа:");
        List<Order> filteredByAmount = orders.stream()
                .distinct()
                .filter(n -> n.getAmount() >= 500)
                .collect(Collectors.toList());

        for (Order order : filteredByAmount) {
            System.out.println(order.toString());
        }
        System.out.println();

        Order maxOrder = orders.stream()
                .max(Comparator.comparing(n -> n.getAmount()))
                .orElse(null);

        System.out.println("Макасин: " + maxOrder.getCustomerName() + "; Сумма заказа: " + maxOrder.getAmount());
    }
}