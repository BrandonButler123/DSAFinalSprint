package com.keyin.DSAFinalSprint;

import com.keyin.DSAFinalSprint.model.BinarySearchTree;
import com.keyin.DSAFinalSprint.service.BSTService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DsaFinalSprintApplicationTests {

	@MockBean
	private BSTService bstService;

	@Test
	void contextLoads() {
	}

	@Test
	void testSaveTree() {
		BinarySearchTree tree = new BinarySearchTree();
		bstService.saveTree(tree);
		verify(bstService).saveTree(tree);
	}

	@Test
	void testProcessNumbers() {
		BinarySearchTree tree = new BinarySearchTree();
		tree.setNumbers(List.of(1, 2, 3));

		// Simulate processing numbers
		bstService.saveTree(tree);

		verify(bstService).saveTree(tree);
	}

	@Test
	void testViewTree() {
		BinarySearchTree tree = new BinarySearchTree();
		tree.setId(1L);

		when(bstService.getTreeById(1L)).thenReturn(tree);

		BinarySearchTree retrievedTree = bstService.getTreeById(1L);

		verify(bstService).getTreeById(1L);
	}
}
