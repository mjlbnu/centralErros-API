package com.centralerrosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centralerrosapi.model.Log;
import com.centralerrosapi.repository.log.LogRepositoryQuery;

public interface LogRepository extends JpaRepository<Log, Long>, LogRepositoryQuery{

}
