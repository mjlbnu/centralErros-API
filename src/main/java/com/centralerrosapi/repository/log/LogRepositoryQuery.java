package com.centralerrosapi.repository.log;

import java.util.List;

import com.centralerrosapi.model.Log;
import com.centralerrosapi.repository.filter.LogFilter;

public interface LogRepositoryQuery {
	
	public List<Log> filtrar (LogFilter logFilter);

}
