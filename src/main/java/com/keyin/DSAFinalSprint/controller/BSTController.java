package com.keyin.DSAFinalSprint.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.keyin.DSAFinalSprint.model.BinarySearchTree;
import com.keyin.DSAFinalSprint.service.BSTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BSTController {

    private final BSTService bstService;
    private final ObjectMapper objectMapper;

    @Autowired
    public BSTController(BSTService bstService, ObjectMapper objectMapper) {
        this.bstService = bstService;
        this.objectMapper = objectMapper;
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @GetMapping("/enter-numbers")
    public String showEnterNumbersPage() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    public ResponseEntity<BinarySearchTree> processNumbers(@RequestParam("numbers") String numbers) {
        BinarySearchTree bst = new BinarySearchTree();
        String[] numberArray = numbers.split(",");
        List<Integer> numberList = new ArrayList<>();

        for (String number : numberArray) {
            int num = Integer.parseInt(number.trim());
            bst.insert(num);
            numberList.add(num);
        }
        bst.setNumbers(numberList);
        bstService.saveTree(bst);

        return ResponseEntity.ok(bst);
    }



    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        Iterable<BinarySearchTree> trees = bstService.getAllTrees();
        model.addAttribute("trees", trees);
        return "previous-trees";
    }

    @GetMapping("/trees/{id}")
    public String viewTree(@PathVariable Long id, Model model) throws JsonProcessingException {
        BinarySearchTree tree = bstService.getTreeById(id);
        if (tree != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String treeJson = objectMapper.writeValueAsString(tree);
            model.addAttribute("treeJson", treeJson);
            return "tree-detail";
        } else {
            return "redirect:/previous-trees";
        }
    }





}
