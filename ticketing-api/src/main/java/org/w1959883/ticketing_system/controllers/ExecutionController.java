package org.w1959883.ticketing_system.controllers;

import com.w1959883.models.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w1959883.ticketing_system.services.ExecutionService;

@RestController
@RequestMapping( "/api/execution" )
public class ExecutionController
{
    private final ExecutionService executionService;

    public ExecutionController( ExecutionService executionService )
    {
        this.executionService = executionService;
    }

    @PostMapping
    private void setProjectStatus( Boolean executionRequest )
    {
        executionService.setExecution( executionRequest );
    }

    @PostMapping("/add")
    private void addTicket( Configuration configuration ){
        executionService.addConfiguration(configuration);
    }
}
