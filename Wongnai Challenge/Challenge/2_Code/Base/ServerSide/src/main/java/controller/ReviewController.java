package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {

    @GetMapping("/reviews/{id}")
    public ResponseEntity<String> getReviewByID(@PathVariable long id) {
        return ResponseEntity.ok("Hello World : " + id);
    }

    @GetMapping("/reviews")
    public ResponseEntity<String> searchReviewsByQuery(@RequestParam String query) {
        return ResponseEntity.ok("Hello World : " + query) ;
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<String> editingReview(@RequestBody String body, @PathVariable long id) {
        return ResponseEntity.ok("Hello World : Put\nID = " + id + "\nBody = " + body);
    }
}
