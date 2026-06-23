package com.example.LibraryManagement.dto;

public record BookTransaction(
        Long bookId,
        Integer quantity
) {}