package com.centralerrosapi.repository.log;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.centralerrosapi.model.Log;
import com.centralerrosapi.repository.filter.LogFilter;
import com.centralerrosapi.repository.projection.LogResume;

public interface LogRepositoryQuery {
	
	public Page<Log> filtrar(LogFilter logFilter, Pageable pageable);
	
	public Page<LogResume> resume(LogFilter logFilter, Pageable pageable);

}
