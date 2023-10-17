package com.wanted.api.recruit.controller;

import com.wanted.api.recruit.dto.RecruitCreateRequest;
import com.wanted.api.recruit.dto.RecruitGetDetailResponse;
import com.wanted.api.recruit.dto.RecruitGetResponse;
import com.wanted.api.recruit.dto.RecruitUpdateRequest;
import com.wanted.api.recruit.service.RecruitService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/recruits")
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitService recruitService;

    @PostMapping
    public ResponseEntity<Void> createRecruit(@RequestBody @Valid RecruitCreateRequest recruitCreateRequest) {
        Long recruitId = recruitService.createRecruit(recruitCreateRequest);
        return ResponseEntity.created(URI.create("/api/recruits/" + recruitId)).build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<RecruitGetResponse>> searchRecruit(@RequestParam String keyword) {
        List<RecruitGetResponse> recruitGetResponses = recruitService.searchRecruit(keyword);
        return ResponseEntity.ok(recruitGetResponses);
    }

    @GetMapping
    public ResponseEntity<List<RecruitGetResponse>> getAllRecruits() {
        List<RecruitGetResponse> recruitGetResponses = recruitService.getAllRecruits();
        return ResponseEntity.ok(recruitGetResponses);
    }

    @GetMapping("/{recruitId}")
    public ResponseEntity<RecruitGetDetailResponse> getRecruit(@PathVariable Long recruitId) {
        RecruitGetDetailResponse recruitGetDetailResponse = recruitService.getRecruit(recruitId);
        return ResponseEntity.ok(recruitGetDetailResponse);
    }

    @PatchMapping("/{recruitId}")
    public ResponseEntity<Void> updateRecruit(@PathVariable Long recruitId,
                                              @RequestBody @Valid RecruitUpdateRequest recruitUpdateRequest) {
        recruitService.updateRecruit(recruitId, recruitUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{recruitId}")
    public ResponseEntity<Void> deleteRecruit(@PathVariable Long recruitId) {
        recruitService.deleteRecruit(recruitId);
        return ResponseEntity.ok().build();
    }
}
