package com.kplusweb.services_games.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kplusweb.services_games.dtos.OrderDTO;
import com.kplusweb.services_games.entity.Order;
import com.kplusweb.services_games.exceptions.ResourceNotFoundException;
import com.kplusweb.services_games.repositories.OrderRepository;
import com.kplusweb.services_games.repositories.ProductRepository;
import com.kplusweb.services_games.repositories.SubProductRepository;
import com.kplusweb.services_games.repositories.UserRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final SubProductRepository subProductRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, SubProductRepository subProductRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.subProductRepository = subProductRepository;
        this.productRepository = productRepository;
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("No orders found.");
        }

        return orders.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Order order) {
        return new OrderDTO(
            order.getId(),
            order.getUser().getId(),
            order.getProduct().getId(),
            order.getSubProduct() != null ? order.getSubProduct().getId() : null,
            order.getOrderDate(),
            order.getStatus().toString()
        );
    }

    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Order with id " + id + " not found."));

        return convertToDTO(order);
    }

    public String postOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        orderRepository.save(order);
        
        return "Order added successfully.";
    }

    public String updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Order with id " + id + " not found."));

        order.setStatus(Order.Status.valueOf(status));
        orderRepository.save(order);
        
        return "Order status updated successfully.";
    }

    public String deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Order with id " + id + " not found."));

        orderRepository.delete(order);
        return "Order deleted successfully.";
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUser(userRepository.findById(orderDTO.userId())
            .orElseThrow(() -> new ResourceNotFoundException("User with id " + orderDTO.userId() + " not found.")));
        order.setProduct(productRepository.findById(orderDTO.productId())
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + orderDTO.productId() + " not found.")));
        order.setSubProduct(subProductRepository.findById(orderDTO.subProductId())
            .orElseThrow(() -> new ResourceNotFoundException("SubProduct with id " + orderDTO.subProductId() + " not found.")));
        order.setOrderDate(orderDTO.orderDate());
        order.setStatus(Order.Status.valueOf(orderDTO.status()));
        return order;
    }
}
