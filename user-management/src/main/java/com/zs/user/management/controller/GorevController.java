package com.zs.user.management.controller;

import com.zs.user.management.model.UserGorev;
import com.zs.user.management.model.UserGorevView;
import com.zs.user.management.service.UserGorevService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequiredArgsConstructor
public class GorevController {
    private final UserGorevService service;

    @GetMapping("/gorevs")
    public ResponseEntity<List<UserGorevView>> getAllMimPersonel() {
        return new ResponseEntity<>(service.getGorev().getAllMimPersonel(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/gorevs")
    public ResponseEntity<UserGorev> postSinav(@RequestBody UserGorev dto) {
        return new ResponseEntity<>(service.getGorev().create(dto), HttpStatus.CREATED);
    }

}
