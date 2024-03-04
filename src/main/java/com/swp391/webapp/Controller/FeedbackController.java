package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.FeedbackEntity;
import com.swp391.webapp.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/Feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService FeedbackDTOService;

    @GetMapping
    public ResponseEntity<List<FeedbackEntity>> getAllFeedbackDTOs() {
        List<FeedbackEntity> feedbackEntities = FeedbackDTOService.getAllFeedbacks();
        return ResponseEntity.ok(feedbackEntities);
    }

    @GetMapping("/{FeedbackDTOId}")
    public ResponseEntity<FeedbackEntity> getFeedbackDTOById(@PathVariable int FeedbackId) {
        FeedbackEntity FeedbackEntity = FeedbackDTOService.getFeedbackById(FeedbackId);
        return ResponseEntity.ok(FeedbackEntity);
    }

    @PostMapping
    public ResponseEntity<FeedbackEntity> saveFeedbackDTO(@RequestBody FeedbackEntity feedbackEntity) {
        FeedbackEntity savedFeedbackEntity = FeedbackDTOService.saveFeedback(feedbackEntity);
        return ResponseEntity.ok(savedFeedbackEntity);
    }

    @DeleteMapping("/{FeedbackDTOId}")
    public ResponseEntity<Void> deleteFeedbackDTO(@PathVariable int FeedbackId) {
        FeedbackDTOService.deleteFeedback(FeedbackId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}

