package kr.kernel360.anabada.domain.category.service;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.kernel360.anabada.domain.category.dto.CreateCategoryRequest;
import kr.kernel360.anabada.domain.category.dto.CreateCategoryResponse;
import kr.kernel360.anabada.domain.category.dto.FindAllCategoryResponse;
import kr.kernel360.anabada.domain.category.entity.Category;
import kr.kernel360.anabada.domain.category.repository.CategoryRepository;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.error.code.CategoryErrorCode;
import kr.kernel360.anabada.global.error.exception.BusinessException;

@DisplayName("카테고리 서비스 단위 mock 테스트")
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
	@InjectMocks
	private CategoryService categoryService;
	@Mock
	private CategoryRepository categoryRepository;

	@BeforeEach
	void setUp() {
		new CategoryService(categoryRepository);
	}

	@AfterEach
	void destroy() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	@DisplayName("카테고리 생성")
	void testCreate() throws Exception {
	    //given
		CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest();
		createCategoryRequest.setName("카테고리1");

		when(categoryRepository.save(any(Category.class))).thenReturn(CreateCategoryRequest.toEntity(createCategoryRequest));

	    //when
		CreateCategoryResponse createCategoryResponse = categoryService.create(createCategoryRequest);

		//then
		assertThat(createCategoryResponse.getName()).isEqualTo(createCategoryRequest.getName());
	}

	@Test
	@DisplayName("모든 카테고리 조회")
	void testFindAll() throws Exception {
	    //given
		Category category1 = Category.builder()
			.name("카테고리1")
			.deletedStatus(DeletedStatus.FALSE)
			.build();
		Category category2 = Category.builder()
			.name("카테고리2")
			.deletedStatus(DeletedStatus.TRUE)
			.build();

		when(categoryRepository.findAll()).thenReturn(List.of(category1, category2));

	    //when
		FindAllCategoryResponse findAllCategoryResponse = categoryService.findAll();

		//then
		assertThat(findAllCategoryResponse.getCategories().size()).isEqualTo(2);
		assertThat(findAllCategoryResponse.getCategories().get(0).getName()).isEqualTo(category1.getName());
	}

	@Test
	@DisplayName("모든 카테고리 중 활성화된 카테고리만 조회")
	void testfindByDeletedStatusFalse() throws Exception {
	    //given
		Category category1 = Category.builder()
			.name("카테고리1")
			.deletedStatus(DeletedStatus.FALSE)
			.build();
		Category category2 = Category.builder()
			.name("카테고리2")
			.deletedStatus(DeletedStatus.TRUE)
			.build();

		when(categoryRepository.findAll()).thenReturn(List.of(category1, category2));

	    //when
		FindAllCategoryResponse findAllDeletedStatusFalseCategoryResponse = categoryService.findByDeletedStatusFalse();

		//then
		assertThat(findAllDeletedStatusFalseCategoryResponse.getCategories().size()).isEqualTo(1);
		assertThat(findAllDeletedStatusFalseCategoryResponse.getCategories().get(0).getName()).isEqualTo(category1.getName());
	}

	@Test
	@DisplayName("카테고리 활성화 상태 TRUE에서 FALSE로 변경")
	void testRemove() throws Exception {
	    //given
		Category category1 = Category.builder()
			.name("카테고리1")
			.deletedStatus(DeletedStatus.TRUE)
			.build();

		Optional<Category> optionalCategory = Optional.of(category1);
		when(categoryRepository.findById(anyLong())).thenReturn(optionalCategory);

	    //when
		categoryService.remove(anyLong());

	    //then
		assertThat(category1.getDeletedStatus()).isEqualTo(DeletedStatus.FALSE);
	}

	// @Test
	// @DisplayName("카테고리 활성화 상태 FALSE에서 TRUE로 변경")
	// void testRemove2() throws Exception {
	//     //given
	// 	Category category1 = Category.builder()
	// 		.name("카테고리1")
	// 		.deletedStatus(DeletedStatus.FALSE)
	// 		.build();
	//
	// 	Optional<Category> optionalCategory = Optional.of(category1);
	// 	when(categoryRepository.findById(anyLong())).thenReturn(optionalCategory);
	//
	// 	doAnswer(invocation -> {
	// 		Category categoryToDelete = invocation.getArgument(0);
	// 		categoryToDelete = Category.builder()
	// 			.name("카테고리1")
	// 			.deletedStatus(DeletedStatus.TRUE)
	// 			.build();
	// 		return null;
	// 	}).when(categoryRepository).delete(any(Category.class));
	//
	// 	//when
	// 	categoryService.remove(anyLong());
	//
	//     //then
	// 	assertThat(category1.getDeletedStatus()).isEqualTo(DeletedStatus.TRUE);
	//
	// }

	@Test
	@DisplayName("카테고리 이름 중복 검사")
	void testValidateNameUnique() throws Exception {
		//given
		//when
		when(categoryRepository.existsByName(anyString())).thenReturn(true);

		//then
		BusinessException businessException = assertThrows(BusinessException.class, () -> {
			categoryService.validateNameUnique(anyString());
		});
		assertEquals(CategoryErrorCode.ALREADY_SAVED_CATEGORY, businessException.getErrorCode());

		//verify
		verify(categoryRepository, times(1)).existsByName(anyString());
	}
}
