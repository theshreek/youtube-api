package com.example.practice.favourite;

import com.example.practice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    Collection<Favourite> findAllByUser(User user);

}