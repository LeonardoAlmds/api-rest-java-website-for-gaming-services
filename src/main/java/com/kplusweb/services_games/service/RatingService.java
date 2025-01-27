package com.kplusweb.services_games.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kplusweb.services_games.exceptions.ResourceNotFoundException;
import com.kplusweb.services_games.entity.Product;
import com.kplusweb.services_games.entity.Rating;
import com.kplusweb.services_games.entity.User;
import com.kplusweb.services_games.dtos.RatingDTO;
import com.kplusweb.services_games.repositories.ProductRepository;
import com.kplusweb.services_games.repositories.RatingRepository;
import com.kplusweb.services_games.repositories.UserRepository;

@Service
public class RatingService {
        private final RatingRepository ratingRepository;
        private final ProductRepository productRepository;
        private final UserRepository userRepository;

        public RatingService(RatingRepository ratingRepository, ProductRepository productRepository,
                        UserRepository userRepository) {
                this.ratingRepository = ratingRepository;
                this.productRepository = productRepository;
                this.userRepository = userRepository;
        }

        public List<RatingDTO> getRatings() {
                List<Rating> ratings = ratingRepository.findAll();

                if (ratings.isEmpty()) {
                        throw new ResourceNotFoundException("No ratings found.");
                }

                return ratings.stream()
                                .map(rating -> new RatingDTO(
                                                rating.getId(),
                                                rating.getScore(),
                                                rating.getComment(),
                                                rating.getProduct().getId(),
                                                rating.getUser().getId(),
                                                rating.getCreated_at(),
                                                rating.getUpdated_at()))
                                .collect(Collectors.toList());
        }

        public RatingDTO getRatingById(Long id) {
                Rating rating = ratingRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("No rating found with id: " + id));

                return new RatingDTO(
                                rating.getId(),
                                rating.getScore(),
                                rating.getComment(),
                                rating.getProduct().getId(),
                                rating.getUser().getId(),
                                rating.getCreated_at(),
                                rating.getUpdated_at());
        }

        public String postRating(RatingDTO ratingDTO) {
                Product product = productRepository.findById(ratingDTO.product_id())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "No product found with id: " + ratingDTO.product_id()));

                User user = userRepository.findById(ratingDTO.user_id())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "No user found with id: " + ratingDTO.user_id()));

                Rating rating = new Rating();
                rating.setComment(ratingDTO.comment());
                rating.setScore(ratingDTO.score());
                rating.setProduct(product);
                rating.setUser(user);
                rating.setCreated_at(LocalDateTime.now());
                rating.setUpdated_at(LocalDateTime.now());

                ratingRepository.save(rating);
                return "Rating created successfully.";
        }

        public String updateRating(Long id, String comment) {
                Rating rating = ratingRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("No rating found with id: " + id));

                rating.setComment(comment);
                rating.setUpdated_at(LocalDateTime.now());

                ratingRepository.save(rating);
                return "Rating updated successfully.";
        }

        public String deleteRating(Long id) {
                Rating rating = ratingRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("No rating found with id: " + id));

                ratingRepository.delete(rating);
                return "Rating deleted successfully.";
        }
}