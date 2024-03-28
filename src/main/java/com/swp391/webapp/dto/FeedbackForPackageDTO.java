package com.swp391.webapp.dto;

import com.swp391.webapp.Entity.FeedbackEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackForPackageDTO {
    float rating;
    int numberOfFeedback;
}
