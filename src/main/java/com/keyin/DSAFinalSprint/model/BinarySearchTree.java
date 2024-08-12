package com.keyin.DSAFinalSprint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class BinarySearchTree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private TreeNode root;

    @ElementCollection
    private List<Integer> numbers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    // insert node into tree
    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private TreeNode insertRecursive(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode();
            root.setValue(value);
            return root;
        }
        if (value < root.getValue()) {
            root.setLeft(insertRecursive(root.getLeft(), value));
        } else if (value > root.getValue()) {
            root.setRight(insertRecursive(root.getRight(), value));
        }
        return root;
    }

    @Override
    public String toString() {
        return "BinarySearchTree{" +
                "root=" + root +
                ", numbers=" + numbers +
                "}";
    }
}
