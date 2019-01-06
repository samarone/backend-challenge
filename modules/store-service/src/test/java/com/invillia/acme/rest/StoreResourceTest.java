package com.invillia.acme.rest;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.invillia.acme.domain.Store;
import com.invillia.acme.dto.StoreDTO;
import com.invillia.acme.repository.StoreRepository;

@AutoConfigureRestDocs
@RunWith(SpringRunner.class)
@WebMvcTest(StoreResource.class)
public class StoreResourceTest {
	private static final String NAME_DEFAULT = "Samarone Ferramentas";

	private static final String ADDRESS_DEFAULT = "Rua dos Afazeres 1000";

	private static final String NAME_UPDATE = "Casa de Ferragens";

	private static final String ADDRESS_UPDATE = "Rua dos Trabalhos 2000";

	@MockBean
	StoreRepository repository;

	@Autowired
	MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {
		Store store = Store.builder().name(NAME_DEFAULT).address(ADDRESS_DEFAULT).id(1000L).build();
		
		when(repository.save(Mockito.any(Store.class))).thenReturn(store);
		when(repository.findById(1000L)).thenReturn(Optional.of(store));
		when(repository.findAll(Mockito.any(Specification.class))).thenReturn(Arrays.asList(store));
	}

	@Test
	public void createStore() throws JsonProcessingException, Exception {
		StoreDTO storeDTO = StoreDTO.builder().name(NAME_DEFAULT).address(ADDRESS_DEFAULT).build();
		mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(storeDTO)))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.name", is(equalTo(NAME_DEFAULT))))
				.andExpect(jsonPath("$.address", is(equalTo(ADDRESS_DEFAULT))))
				.andDo(document("{method-name}/", requestFields(fieldWithPath("name").description(NAME_DEFAULT),
						fieldWithPath("address").description(ADDRESS_DEFAULT))));
	}

	@Test
	public void updateStore() throws JsonProcessingException, Exception {
		StoreDTO storeDTO = StoreDTO.builder().name(NAME_UPDATE).address(ADDRESS_UPDATE).build();
		mockMvc.perform(put("/1000").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(storeDTO)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.name", is(equalTo(NAME_UPDATE))))
				.andExpect(jsonPath("$.address", is(equalTo(ADDRESS_UPDATE))))
				.andDo(document("{method-name}/", requestFields(fieldWithPath("name").description(NAME_UPDATE),
						fieldWithPath("address").description(ADDRESS_UPDATE))));
	}
	
	@Test
	public void listStore() throws JsonProcessingException, Exception {
		this.mockMvc.perform(get("/list?name="+NAME_DEFAULT+";address="+ADDRESS_DEFAULT)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("{method-name}/",
						responseFields(
								fieldWithPath("[].name").description(NAME_DEFAULT),
								fieldWithPath("[].address").description(ADDRESS_DEFAULT))));
	}

	@TestConfiguration
	static class CustomizationConfiguration implements RestDocsMockMvcConfigurationCustomizer {
		@Override
		public void customize(MockMvcRestDocumentationConfigurer configurer) {
			configurer.operationPreprocessors().withRequestDefaults(prettyPrint()).withResponseDefaults(prettyPrint());
		}

		@Bean
		public RestDocumentationResultHandler restDocumentation() {
			return MockMvcRestDocumentation.document("{method-name}");
		}
	}
}
