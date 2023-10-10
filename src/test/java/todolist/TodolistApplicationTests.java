package todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import todolist.entities.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TodolistApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	void testCreateTodoSucess() {
		var todo = new Todo("todo 1", "desc todo 1", false, 1);

		webClient
				.post()
				.uri("/api/v1/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].nome").isEqualTo(todo.getNome())
				.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
				.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());

	}

	@Test
	void testCreateTodoFailure() {
		var todo = new Todo("", "", false, 0);

		webClient
				.post()
				.uri("/api/v1/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	void testListTodoSucess() {
		webClient
				.get()
				.uri("/api/v1/todos")
				.exchange()
				.expectStatus().isOk();
	}

	@Test
	void testUpdateTodoSucess() {
		var todo = new Todo("todo 1", "desc todo 1", false, 1);

		webClient
				.put()
				.uri("/api/v1/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].nome").isEqualTo(todo.getNome())
				.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
				.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());

	}

	@Test
	void testUpdateTodoFailure() {
		var todo = new Todo("", "", false, 0);

		webClient
				.put()
				.uri("/api/v1/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	void testDeleteTodoSucess() {

		webClient
				.delete()
				.uri("/api/v1/todos/{id}", 1)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1);
	}

	@Test
	void testDeleteTodoFailure() {

		webClient
				.delete()
				.uri("/api/v1/todos/{id}", 1)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(0);
	}

}
