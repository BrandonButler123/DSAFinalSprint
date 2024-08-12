package com.keyin.DSAFinalSprint.service;

import com.keyin.DSAFinalSprint.model.BinarySearchTree;
import com.keyin.DSAFinalSprint.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BSTService {

    private final TreeRepository treeRepository;

    @Autowired
    public BSTService(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    public void saveTree(BinarySearchTree bst) {
        treeRepository.save(bst);
    }

    public Iterable<BinarySearchTree> getAllTrees() {
        return treeRepository.findAll();
    }

    public BinarySearchTree getTreeById(Long id) {
        return treeRepository.findById(id).orElse(null);
    }
}
