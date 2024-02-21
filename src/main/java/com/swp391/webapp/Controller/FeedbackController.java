package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.FeedbackDTO;
import com.swp391.webapp.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService FeedbackDTOService;

    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbackDTOs() {
        List<FeedbackDTO> FeedbackDTOs = FeedbackDTOService.getAllFeedbacks();
        return ResponseEntity.ok(FeedbackDTOs);
    }

    @GetMapping("/{FeedbackDTOId}")
    public ResponseEntity<FeedbackDTO> getFeedbackDTOById(@PathVariable int FeedbackId) {
        FeedbackDTO FeedbackDTO = FeedbackDTOService.getFeedbackById(FeedbackId);
        return ResponseEntity.ok(FeedbackDTO);
    }

    @PostMapping
    public ResponseEntity<FeedbackDTO> saveFeedbackDTO(@RequestBody FeedbackDTO feedbackDTO) {
        FeedbackDTO savedFeedbackDTO = FeedbackDTOService.saveFeedback(feedbackDTO);
        return ResponseEntity.ok(savedFeedbackDTO);
    }

    @DeleteMapping("/{FeedbackDTOId}")
    public ResponseEntity<Void> deleteFeedbackDTO(@PathVariable int FeedbackId) {
        FeedbackDTOService.deleteFeedback(FeedbackId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}

