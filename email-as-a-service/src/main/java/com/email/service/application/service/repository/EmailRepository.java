package com.email.service.application.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.email.service.application.entity.EmailDetails;

@Repository
public interface EmailRepository extends JpaRepository<EmailDetails, Long> {

}
