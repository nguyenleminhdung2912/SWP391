package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.FeedbackEntity;
import com.swp391.webapp.Service.FeedbackService;
import com.swp391.webapp.dto.FeedbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<FeedbackEntity>> getAllFeedbacks() {
        List<FeedbackEntity> feedbackEntities = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbackEntities);
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<FeedbackEntity>> getFeedbacksByService(@PathVariable int serviceId) {
        List<FeedbackEntity> feedbackEntities = feedbackService.getFeedbacksByService(serviceId);
        return ResponseEntity.ok(feedbackEntities);
    }

    @GetMapping("/{FeedbackId}")
    public ResponseEntity<FeedbackEntity> getFeedbackById(@PathVariable int FeedbackId) {
        FeedbackEntity FeedbackEntity = feedbackService.getFeedbackById(FeedbackId);
        return ResponseEntity.ok(FeedbackEntity);
    }

    @PostMapping("/addFeedback/{orderId}")
    public ResponseEntity<FeedbackDTO> saveFeedback(@PathVariable int orderId,@RequestBody FeedbackDTO feedbackDTO) {
        FeedbackDTO savedFeedbackEntity = feedbackService.saveFeedback(orderId, feedbackDTO);
        return ResponseEntity.ok(savedFeedbackEntity);
    }

    @DeleteMapping("/{FeedbackId}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable int FeedbackId) {
        feedbackService.deleteFeedback(FeedbackId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}

