package com.example.otp.generation.repository;

import com.example.otp.generation.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    void deleteByTitle(String title);

    boolean existsByTitle(String questionName);

    Optional<Question> findByTitle(String title);

    @Query("SELECT q.title FROM Question q") // Custom query to fetch only titles
    List<String> findAllTitles();


}
