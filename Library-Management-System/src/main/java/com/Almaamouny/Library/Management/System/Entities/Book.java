package com.Almaamouny.Library.Management.System.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Author is mandatory")
    private String author;

    @NotNull(message = "Publication year is mandatory")
    private Integer publicationYear;

    @NotBlank(message = "ISBN is mandatory")
    @Pattern(regexp = "^[0-9-]*$", message = "ISBN must contain only numbers and hyphens")
    private String isbn;

    private boolean available = true;
}
