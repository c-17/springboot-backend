package com.example.prueba.Liberty.repositories;

import com.example.prueba.Liberty.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {
}