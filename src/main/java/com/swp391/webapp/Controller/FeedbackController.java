package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.FeedbackEntity;
import com.swp391.webapp.Service.FeedbackService;
import com.swp391.webapp.dto.FeedbackDTO;
import com.swp391.webapp.dto.FeedbackForPackageDTO;
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

    @GetMapping("/packageAverageRating/{packageId}")
    public FeedbackForPackageDTO getAverageFeedbacksByPackage(@PathVariable int packageId) {
        FeedbackForPackageDTO feedback = feedbackService.getAverageFeedbacksForPackageDetail(packageId);
        return feedback;
    }

    @GetMapping("/package/{packageId}")
    public List<FeedbackEntity> getFeedbacksByPackage(@PathVariable int packageId) {
        List<FeedbackEntity> feedbackList = feedbackService.getFeedbacksForPackageDetail(packageId);
        return feedbackList;
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity<List<FeedbackEntity>> getFeedbacksByHost(@PathVariable int hostId) {
        List<FeedbackEntity> feedbackEntities = feedbackService.getFeedbacksByHost(hostId);
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

