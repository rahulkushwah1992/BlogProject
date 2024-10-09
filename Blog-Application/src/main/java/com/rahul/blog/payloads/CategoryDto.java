package com.rahul.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private Long id;
	@NotEmpty
	@Size(min = 2, max = 10, message = "category title min 100 character !!")
	private String categoryTitle;
	private String categoryDescription;

}
