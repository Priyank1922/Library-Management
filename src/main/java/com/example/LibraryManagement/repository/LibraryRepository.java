package com.example.LibraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LibraryManagement.entity.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {

}
