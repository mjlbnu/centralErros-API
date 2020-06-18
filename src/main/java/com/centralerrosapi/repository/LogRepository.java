package com.centralerrosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centralerrosapi.model.Log;

public interface LogRepository extends JpaRepository<Log, Long>{

}
